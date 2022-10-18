package com.example.jms_demo_2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cashi")
@IdClass(CashiRelationPK.class)
public class Cashi implements Serializable
{
    @Id
    @Column(name = "CASHI_MGNI_ID")
    private String mgniId;
    @Id
    @Column(name = "CASHI_ACC_NO")
    private String accNo;
    @Id
    @Column(name = "CASHI_CCY")
    private String ccy;
    @Column(name = "CASHI_AMT")
    private BigDecimal amt;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "mgni_id")
//    private Mgni mgni;
}
