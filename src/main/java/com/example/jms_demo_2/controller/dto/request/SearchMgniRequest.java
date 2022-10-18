package com.example.jms_demo_2.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchMgniRequest {
    @Pattern(regexp = "^$|(MGI[0-9]{17})",message = "ID格式請輸入：GMI + 17位數字")
    private String id;
    @Pattern(regexp = "^$|[12]",message = "請輸入代碼：1 or 2")
    private String kacType;
    @Pattern(regexp = "^$|(TWD|USD)",message = "請輸入 TWD or USD")
    private String ccy;
}
