package com.bacsystem.components.mappers;

import com.bacsystem.dtos.ApplicationDto;
import com.bacsystem.repositories.entities.BsApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

/**
 * IApplicationMapper
 * <p>
 * IApplicationMapper interface.
 * <p>
 * THIS COMPONENT WAS BUILT ACCORDING TO THE DEVELOPMENT STANDARDS
 * AND THE BACSYSTEM APPLICATION DEVELOPMENT PROCEDURE AND IS PROTECTED
 * BY THE LAWS OF INTELLECTUAL PROPERTY AND COPYRIGHT...
 *
 * @author cbaciliod
 * @author dbacilio88@outllok.es
 * @since 25/09/2024
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface IApplicationMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuId", source = "uuId")
    @Mapping(target = "apDescription", source = "description")
    BsApplicationEntity toEntity(ApplicationDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "uuId", source = "uuId")
    @Mapping(target = "description", source = "apDescription")
    ApplicationDto toDto(BsApplicationEntity entity);

    List<ApplicationDto> toList(Iterable<BsApplicationEntity> entities);

    default Page<ApplicationDto> toPage(Page<BsApplicationEntity> entities) {
        List<ApplicationDto> list = entities.getContent()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(list, entities.getPageable(), entities.getTotalElements());
    }

}
