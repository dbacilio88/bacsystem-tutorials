package com.bacsystem.upload.projections;


/**
 * <b>IFileTypeInformation</b>
 * <p>
 * This class specifies the requirements for the {@link IFileTypeInformation} component,
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

public interface IFileTypeInformation {
    Long getId();
    String getUuId();
    String getName();
    String getDescription();
    String getCreated();
}
