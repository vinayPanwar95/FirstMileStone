package com.icici.bank.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Data
public class ICICIRequestDTO {
    private UUID id;
    private int version;
//    private ExceptionType exceptionType;
//    private ExceptionSubType exceptionSubType;
    private BigDecimal lvPercentage;

    public static void main(String[] args) {
        System.err.println(UUID.randomUUID());
    }
}
