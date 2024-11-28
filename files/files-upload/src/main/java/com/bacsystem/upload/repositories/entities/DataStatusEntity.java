package com.bacsystem.upload.repositories.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * <b>DataStatusEntity</b>
 * <p>
 * This class specifies the requirements for the {@link DataStatusEntity} component,
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
 * @since 24/11/2024
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DATA_STATUS")
public class DataStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DS_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "DS_UUID", nullable = false, unique = true,length = 36)
    private String uuId;

    @Column(name = "DS_NAME", nullable = false, unique = true,length = 100)
    private String name;

    @Column(name = "DS_DESCRIPTION", nullable = false, unique = true,length = 100)
    private String description;

    @Column(name = "DS_CREATED")
    private Instant created;
}
