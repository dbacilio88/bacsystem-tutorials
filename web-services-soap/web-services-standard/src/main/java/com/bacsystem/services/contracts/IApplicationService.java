package com.bacsystem.services.contracts;


import com.bacsystem.components.interfaces.ICrud;
import com.bacsystem.dtos.ApplicationDto;

/**
 * IApplicationService
 * <p>
 * IApplicationService interface.
 * <p>
 * THIS COMPONENT WAS BUILT ACCORDING TO THE DEVELOPMENT STANDARDS
 * AND THE BACSYSTEM APPLICATION DEVELOPMENT PROCEDURE AND IS PROTECTED
 * BY THE LAWS OF INTELLECTUAL PROPERTY AND COPYRIGHT...
 *
 * @author cbaciliod
 * @author dbacilio88@outllok.es
 * @since 25/09/2024
 */

public interface IApplicationService extends ICrud<ApplicationDto, Long> {
    Integer calculate(int a, int b);
}
