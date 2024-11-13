package com.bacsystem.grpc.components.utils;


import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * <b>StringUtility</b>
 * <p>
 * This class specifies the requirements for the {@link StringUtility} component,
 * developed in accordance with the development standards established by bxcode.
 * Collaboration is encouraged for the enhancement and expansion of the bacsystem-tutorials.
 * </p>
 * <p>
 * <b>Usage:</b>
 * description here!
 * </p>
 *
 * @author bxcode
 * @author dbacilio88@outllok.es
 * @since 10/11/2024
 */

@Log4j2
@UtilityClass
public class StringUtility {

    public String replaceSql(final String sql) {
        return StringUtils.replace(sql, StringUtils.SPACE, StringUtils.EMPTY);
    }
}
