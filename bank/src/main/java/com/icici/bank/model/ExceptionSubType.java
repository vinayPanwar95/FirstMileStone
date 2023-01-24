package com.icici.bank.model;

import com.sun.istack.NotNull;

import java.util.Arrays;
import java.util.Optional;

public enum ExceptionSubType {
    ABSOLUTE,
    DEVIATION;

    private static Optional<ExceptionSubType> fromString (@NotNull String exceptionSubType){
        return Arrays.stream(values()).filter(lvxExceptionSubType->lvxExceptionSubType.name().equals(exceptionSubType)).findAny();
    }
}
