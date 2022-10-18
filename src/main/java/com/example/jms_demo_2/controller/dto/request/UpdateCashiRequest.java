package com.example.jms_demo_2.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCashiRequest {
    @NotEmpty
    @Pattern(regexp = "^$|(MGI[0-9]{17})",message = "ID格式請輸入：GMI + 17位數字")
    private String mgniId;
    @NotEmpty
    private String accNo;
    @NotEmpty
    @Pattern(regexp = "^$|(TWD|USD)",message = "請輸入 TWD or USD")
    private String ccy;
    @NotNull
    @DecimalMin(value = "0",inclusive = false,message = "輸入數值需大於 0")
    @Digits(integer = 20,fraction = 4,message = "長度錯誤")
    private BigDecimal amt;
}
