package com.bacsystem.repositories.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * BsApplicationEntity
 * <p>
 * BsApplicationEntity class.
 * <p>
 * THIS COMPONENT WAS BUILT ACCORDING TO THE DEVELOPMENT STANDARDS
 * AND THE BACSYSTEM APPLICATION DEVELOPMENT PROCEDURE AND IS PROTECTED
 * BY THE LAWS OF INTELLECTUAL PROPERTY AND COPYRIGHT...
 *
 * @author cbaciliod
 * @author dbacilio88@outllok.es
 * @since 25/09/2024
 */

@Getter
@Setter
@Entity
@Table(name = "BS_APPLICATION", schema = "PUBLIC", uniqueConstraints = {
        @UniqueConstraint(name = "BS_APPLICATION_UN", columnNames = {"AP_UUID"})
})
public class BsApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BS_APPLICATION_SEQ")
    @SequenceGenerator(name = "BS_APPLICATION_SEQ", sequenceName = "BS_APPLICATION_SEQ", allocationSize = 1)
    @Column(name = "AP_ID", nullable = false)
    private Long id;

    @Size(max = 36)
    @javax.validation.constraints.NotNull
    @Column(name = "AP_UUID", nullable = false, length = 36)
    private String uuId;

    @Size(max = 50)
    @Column(name = "AP_DESCRIPTION", length = 50)
    private String apDescription;

}