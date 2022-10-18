package com.example.jms_demo_2.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CashiRelationPK implements Serializable {
    private String mgniId;
    private String accNo;
    private String ccy;
}
