package com.icici.bank.entity;

import com.icici.bank.model.ExceptionSubType;
import com.icici.bank.model.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "LVX")
@Accessors(fluent = true)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ICICIEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private UUID id;
    private int version;
//    private ExceptionType exceptionType;
//    private ExceptionSubType exceptionSubType;
    private BigDecimal lvPercentage;

}
