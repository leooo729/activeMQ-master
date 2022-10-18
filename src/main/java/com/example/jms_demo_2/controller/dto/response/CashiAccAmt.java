package com.example.jms_demo_2.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CashiAccAmt {
    @NotEmpty
    private String acc;
    @NotNull
    @DecimalMin(value = "0",inclusive = false,message = "輸入數值需大於 0")
    @Digits(integer = 20,fraction = 4,message = "長度錯誤")
    private BigDecimal amt;
}
