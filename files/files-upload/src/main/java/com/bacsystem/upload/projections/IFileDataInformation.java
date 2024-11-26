package com.bacsystem.upload.projections;


/**
 * <b>IFileDataInformation</b>
 * <p>
 * This class specifies the requirements for the {@link IFileDataInformation} component,
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

public interface IFileDataInformation {
    Long getId();
    String getUuId();
    String getName();
    String getType();
    String getStatus();
    int getSize();
    String getCreated();
}
