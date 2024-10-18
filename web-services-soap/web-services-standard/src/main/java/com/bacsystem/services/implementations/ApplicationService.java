package com.bacsystem.services.implementations;


import com.bacsystem.components.mappers.IApplicationMapper;
import com.bacsystem.connectors.wsdl.calculator.Add;
import com.bacsystem.connectors.wsdl.calculator.AddResponse;
import com.bacsystem.dtos.ApplicationDto;
import com.bacsystem.repositories.IBsApplicationRepository;
import com.bacsystem.repositories.entities.BsApplicationEntity;
import com.bacsystem.services.contracts.IApplicationService;
import com.bacsystem.utils.components.factories.abstracts.contracts.ISoapStandardDefinition;
import com.bacsystem.utils.components.factories.beans.ISoapStandardDefinitionFactory;
import com.bacsystem.utils.providers.ISoapStandardProvider;
import com.bacsystem.utils.providers.request.RequestProvider;
import com.bacsystem.utils.providers.response.ResponseProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * ApplicationService
 * <p>
 * ApplicationService class.
 * <p>
 * THIS COMPONENT WAS BUILT ACCORDING TO THE DEVELOPMENT STANDARDS
 * AND THE BACSYSTEM APPLICATION DEVELOPMENT PROCEDURE AND IS PROTECTED
 * BY THE LAWS OF INTELLECTUAL PROPERTY AND COPYRIGHT...
 *
 * @author cbaciliod
 * @author dbacilio88@outllok.es
 * @since 25/09/2024
 */

@Service
@Log4j2
public class ApplicationService implements IApplicationService {

    private final IBsApplicationRepository applicationRepository;
    private final IApplicationMapper applicationMapper;
    private final ISoapStandardDefinitionFactory soapStandardDefinitionFactory;
    private final ISoapStandardProvider standardProvider;

    public ApplicationService(IBsApplicationRepository applicationRepository,
                              IApplicationMapper applicationMapper,
                              ISoapStandardDefinitionFactory soapStandardDefinitionFactory,
                              ISoapStandardProvider standardProvider) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.soapStandardDefinitionFactory = soapStandardDefinitionFactory;
        this.standardProvider = standardProvider;
    }


    @Override
    public long delete(Long id) {
        Optional<BsApplicationEntity> result = this.applicationRepository.findById(id);
        if (result.isEmpty()) {
            //throw new RuntimeException("not found");
            // TODO lógica para controlar error pendiente.
            return 0;
        }

        this.applicationRepository.delete(result.get());
        return id;
    }

    @Override
    public List<ApplicationDto> findAll() {
        return this.applicationMapper.toList(this.applicationRepository.findAll());
    }

    @Override
    public Page<ApplicationDto> findAll(String page, String size) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(size));
        return this.applicationMapper.toPage(this.applicationRepository.findAll(pageable));
    }


    @Override
    public ApplicationDto findById(Long id) {
        return this.applicationMapper.toDto(this.applicationRepository.findById(id).orElse(null));
    }

    @Override
    public ApplicationDto save(ApplicationDto applicationDto) {
        applicationDto.setUuId(UUID.randomUUID().toString());
        BsApplicationEntity entity = this.applicationRepository.save(this.applicationMapper.toEntity(applicationDto));
        return this.applicationMapper.toDto(entity);
    }

    @Override
    public ApplicationDto update(ApplicationDto applicationDto) {
        Optional<BsApplicationEntity> result = this.applicationRepository.findById(applicationDto.getId());
        if (result.isEmpty()) {
            //throw new RuntimeException("not found");
            // TODO lógica para controlar error pendiente.
            return applicationDto;
        }
        BsApplicationEntity entity = result.get();
        entity.setApDescription(applicationDto.getDescription());
        return this.applicationMapper.toDto(this.applicationRepository.save(entity));
    }

    @Override
    public Integer calculate(int a, int b) {
        final RequestProvider<Add, AddResponse> requestProvider = new RequestProvider<>();
        Add add = new Add();
        add.setIntA(a);
        add.setIntB(b);
        log.info("request provider to soap web service: [a={} + b={}]", add.getIntA(), add.getIntB());
        requestProvider.setRequest(add);
        requestProvider.setDefinition("SOAP_STANDARD_DEFINITION_CALCULATOR");
        final ISoapStandardDefinition standardDefinition = this.soapStandardDefinitionFactory.factory(requestProvider.getDefinition());

        final ResponseProvider<AddResponse> responseProvider = this.standardProvider.callService(requestProvider, standardDefinition);
        log.info("response provider from soap web service: [result={}]", responseProvider.getResponse().getAddResult());
        //soapResponse.getResponse().getAddResult();
        return responseProvider.getResponse().getAddResult();
    }
}
