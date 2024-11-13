package com.bacsystem.grpc.components.enums;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <b>Operator</b>
 * <p>
 * This class specifies the requirements for the {@link Operator} component,
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

@Getter
public enum Operator {
    EQUALS("EQUALS", "=");
    private final String name;
    private final String symbol;

    private static final Map<String, Operator> OPERATOR_MAP = new HashMap<>();

    Operator(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    static {
        for (Operator operator : Operator.values()) {
            OPERATOR_MAP.put(operator.name, operator);
        }
    }

    public static Operator getOperator(String name) {
        if (StringUtils.isEmpty(name)) {
            return EQUALS;
        }

        var op = OPERATOR_MAP.get(name);
        if (Objects.isNull(op)) {
            throw new IllegalArgumentException("Invalid operator: " + name);
        }
        return op;
    }
}
