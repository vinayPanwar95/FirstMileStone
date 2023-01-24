package com.icici.bank.model;

import com.sun.istack.NotNull;

import java.util.Arrays;
import java.util.Optional;

public enum ExceptionType {
    CASH,
    CUSTODY,
    SINGLE_SECURITY,
    SECURITY_CAP,
    CURRENCY;

    private static Optional<ExceptionType> fromString (@NotNull String exceptionType){
    return Arrays.stream(values()).filter(lvxExceptionType->lvxExceptionType.name().equals(exceptionType)).findAny();
    }
}

