package com.example.jms_demo_2.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MGNI")
@EntityListeners(AuditingEntityListener.class)
//@XmlAccessorType(XmlAccessType.FIELD) //表明哪些可以被轉化為xml
public class Mgni {
    @Id
    @Column(name = "MGNI_ID")
    private String id;
    @CreatedDate
    @Column(name = "MGNI_TIME")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDateTime time;
    @Column(name = "MGNI_TYPE")
    private String type;
    @Column(name = "MGNI_CM_NO")
    private String cmNo;
    @Column(name = "MGNI_KAC_TYPE")
    private String kacType;
    @Column(name = "MGNI_BANK_NO")
    private String bankNo;
    @Column(name = "MGNI_CCY")
    private String ccy;
    @Column(name = "MGNI_PV_TYPE")
    private String pvType;
    @Column(name = "MGNI_BICACC_NO")
    private String bicaccNo;
    @Column(name = "MGNI_I_TYPE")
    @JsonProperty(value = "iType")
    private String iTYPE;
    @Column(name = "MGNI_P_REASON")
    @JsonProperty(value = "pReason")
    private String pReason;
    @Column(name = "MGNI_AMT")
    private BigDecimal amt;
    @Column(name = "MGNI_CT_NAME")
    private String ctName;
    @Column(name = "MGNI_CT_TEL")
    private String ctTel;
    @Column(name = "MGNI_STATUS")
    private String status;
    @LastModifiedDate
    @Column(name = "MGNI_U_TIME")
    @JsonProperty(value = "uTime")
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDateTime uTime;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "mgniId",fetch = FetchType.EAGER) //指定實體被關聯處理 // 直接載入關聯物件
//    @JoinColumn(name = "CASHI_MGNI_ID")
    private List<Cashi> cashiList;

}
