package com.example.jms_demo_2.activeMQ;

import com.example.jms_demo_2.controller.PTPProducer;
import com.example.jms_demo_2.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebAppConfiguration//@ExtendWith(MockitoExtension.class)
class PTPProducerTest {
    // 利用 mock 技術模擬出對資料庫連結的物件
//    @Mock
//    private TransferService transferService;
    @Autowired
    private MockMvc mockMvc; //提供了请求和响应的模拟测试接口


    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    void getAllMgni() throws Exception {

        ResultActions resultActions =
                // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
                mockMvc.perform(
                                get("/produce").contentType(MediaType.APPLICATION_JSON))
                        // 輸出整個回應結果訊息
                        .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void searchTargetMgni() throws Exception {
        String response = "[{\"id\":\"MGI20221017012143744\",\"time\":\"2022-10-17T00:00:00\",\"type\":\"1\",\"cmNo\":\"3\",\"kacType\":\"1\",\"bankNo\":\"003\",\"ccy\":\"TWD\",\"pvType\":\"1\",\"bicaccNo\":\"0000000\",\"amt\":1700.0000,\"ctName\":\"Leo\",\"ctTel\":\"26262626\",\"status\":\"1\",\"cashiList\":[{\"mgniId\":\"MGI20221017012143744\",\"accNo\":\"1\",\"ccy\":\"TWD\",\"amt\":1000.0000000},{\"mgniId\":\"MGI20221017012143744\",\"accNo\":\"2\",\"ccy\":\"TWD\",\"amt\":700.0000000}],\"iType\":\"1\",\"pReason\":\"1\",\"uTime\":\"2022-10-17T00:00:00\"}]";

//        ObjectMapper om = new ObjectMapper();
//        List<Mgni>b=om.readValue(response, List.class);

//        when(this.transferService.searchTargetMgni(any())).thenReturn(b);
//
//        System.out.println(b);
//        String expectedStr = om.writeValueAsString(b);

        String requestBoby = "{\"id\":\"MGI20221017012143744\"}";
        ResultActions resultActions =
                // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
                mockMvc.perform(
                                post("/produce/search").content(requestBoby).contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        // 輸出整個回應結果訊息
                        .andExpect(status().isOk()).andDo(print());
        // 取得回傳物件
        String actual = resultActions.andReturn().getResponse().getContentAsString();

        Assert.assertEquals(response,actual);
    }
//==============================================================================
//@Test
//@DisplayName("測試 searchTargetMgni()")
//void searchTargetMgni() throws Exception {
//
//    String response = "[{\"id\":\"MGI20221013094256109\",\"time\":\"2022-10-13 09:42:56\",\"type\":\"1\",\"cmNo\":\"9\",\"kacType\":\"1\",\"bankNo\":\"999\",\"ccy\":\"TWD\",\"pvType\":\"1\",\"bicaccNo\":\"0000000\",\"amt\":30.0000,\"ctName\":\"Joey\",\"ctTel\":\"1234578\",\"status\":\"0\",\"cashiList\":[{\"id\":\"MGI20221013094256109\",\"accNo\":\"1\",\"ccy\":\"TWD\",\"amt\":10.0000},{\"id\":\"MGI20221013094256109\",\"accNo\":\"2\",\"ccy\":\"TWD\",\"amt\":20.0000}],\"preason\":\"money\",\"iType\":\"1\",\"uTime\":\"2022-10-13 09:42:56\"}]";
//
//    String requestBoby = "{\"id\":\"MGI20221013094256109\"}";
//    ResultActions resultActions =
//            // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
//            mockMvc.perform(
//                            post("/api/mgn/search/Mgni/1").content(requestBoby).contentType(MediaType.APPLICATION_JSON)
//                                    .accept(MediaType.APPLICATION_JSON)
//                    )
//                    // 輸出整個回應結果訊息
//                    .andDo(print());
//    // 取得回傳物件
//    String actual = resultActions.andReturn().getResponse().getContentAsString();
//
//    //  驗證結果
//    Assert.assertEquals(response, actual);
//}
}
