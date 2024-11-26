package com.bacsystem.upload.repositories.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * <b>FileDataEntity</b>
 * <p>
 * This class specifies the requirements for the {@link FileDataEntity} component,
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
@Table(name = "DATA_FILE")
public class FileDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DF_ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "DF_UUID", nullable = false, unique = true,length = 36)
    private String uuId;

    @Column(name = "DF_NAME", nullable = false, unique = true,length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "DF_TYPE", nullable = false)
    private FileTypeEntity type;

    @ManyToOne
    @JoinColumn(name = "DF_STATUS", nullable = false)
    private FileStatusEntity status;

    @Column(name = "DF_SIZE")
    private Integer size;

    @Column(name = "DF_CREATED")
    private Instant created;
}
