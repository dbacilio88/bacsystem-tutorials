package com.bacsystem.upload.repositories.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * <b>DataTypeEntity</b>
 * <p>
 * This class specifies the requirements for the {@link DataTypeEntity} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the bacsystem-tutorials.
 * </p>
 * <p>
 * <b>Usage:</b>
 * description here!
 * </p>
 *
 * @author christian
 * @author dbacilio88@outllok.es
 * @since 25/11/2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DATA_TYPE")
public class DataTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DT_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "DT_UUID", nullable = false, unique = true,length = 36)
    private String uuId;

    @Column(name = "DT_NAME", nullable = false, unique = true,length = 100)
    private String name;

    @Column(name = "DT_DESCRIPTION", nullable = false, unique = true,length = 100)
    private String description;

    @Column(name = "DT_CREATED")
    private Instant created;
}
