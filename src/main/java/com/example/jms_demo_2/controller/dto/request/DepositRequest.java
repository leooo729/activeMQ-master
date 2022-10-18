package com.example.jms_demo_2.controller.dto.request;

import com.example.jms_demo_2.controller.dto.response.CashiAccAmt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Validated

public class DepositRequest {
//        private String id;
//        private LocalDateTime time;
//        @NotEmpty
//        @Pattern(regexp = "^$|(1)",message = "請輸入代碼：1(入金)")
//        private String type;
        @NotEmpty
        @Pattern(regexp = "^$|[A-Za-z0-9]{0,7}",message = "代碼長度上限為 7")
        private String cmNo;
        @NotEmpty
        @Pattern(regexp = "^$|[12]",message = "請輸入代碼：1 or 2")
        private String kacType;
        @NotEmpty
        @Pattern(regexp = "^$|[0-9]{3}",message = "代碼長度只能為 3")
        private String bankNo;
        @NotEmpty
        @Pattern(regexp = "^$|(TWD|USD)",message = "請輸入 TWD or USD")
        private String ccy;
        @NotEmpty
        @Pattern(regexp = "^$|[12]",message = "請輸入代碼：1 or 2")
        private String pvType;
        @NotEmpty
        @Pattern(regexp = "^$|[0-9]{0,21}",message = "代碼長度上限為 21")
        private String bicaccNo;
        @NotEmpty
        @Valid
        private List<CashiAccAmt> accAmt;
        //        @NotEmpty
//        @Pattern(regexp = "^$|(1234)",message = "請輸入代碼：1~4")
        //private String iTYPE;
        //private String pReason;
        //private BigDecimal amt;
        @NotEmpty
        @Pattern(regexp = "^$|[A-Za-z\\u4e00-\\u9fa5]{0,120}",message = "名字長度超過範圍")
        private String ctName;

        @NotEmpty
        @Pattern(regexp = "^$|[0-9]{0,30}",message = "代碼長度上限為 21")
        private String ctTel;
        //private String status;
        //private String uTime;
}
