package com.example.jms_demo_2.activeMQ;

import com.example.jms_demo_2.controller.Producer;
import com.example.jms_demo_2.model.entity.Mgni;
import com.example.jms_demo_2.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ProducerTest {
    // 利用 mock 技術模擬出對資料庫連結的物件
    @Mock
    private TransferService transferService;
//    private TransferService transferService = Mockito.mock(TransferService.class);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Producer producer;


    @Autowired
    private WebApplicationContext wac;



    @Before
    public void setup() throws Exception {
        // standaloneSetup 表示通過參數指定一組控制器，這樣就不需要從上下文獲取
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();//webAppContextSetup
        mockMvc = MockMvcBuilders.standaloneSetup(producer).build();//webAppContextSetup

    }

//    @Test
//    @DisplayName("測試 getAllMgniJsonAndXml()")
//    void getAllMgni() throws Exception {
//
//        // 构造get请求
//        RequestBuilder request = get("/produce");
//        // 执行get请求
//        mockMvc.perform(request)
//                .andExpect(status().isOk());  // 对请求结果进行期望，响应的状态为200
//
//        System.out.println("測試 Mgni Find All !");
//    }

    @Test
    void getAllMgni() throws Exception {

        ResultActions resultActions =
                // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
                mockMvc.perform(
                                get("/produce").contentType(MediaType.APPLICATION_JSON))
                        // 輸出整個回應結果訊息
                        .andDo(print());


        String actual = resultActions.andReturn().getResponse().getContentAsString();
        // Assert (驗證結果)
        // 驗證回傳的 http 狀態和 response body 的 json 格式中的 name 欄位是否正確
//        String actual = resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");

        System.out.println("result : " + actual);

    }

    @Test
    void searchTargetMgni() throws Exception {
        String response = "[{\"id\":\"MGI20221017012143744\",\"time\":\"2022-10-17T00:00:00\",\"type\":\"1\",\"cmNo\":\"3\",\"kacType\":\"1\",\"bankNo\":\"003\",\"ccy\":\"TWD\",\"pvType\":\"1\",\"bicaccNo\":\"0000000\",\"amt\":1700.0000,\"ctName\":\"Leo\",\"ctTel\":\"26262626\",\"status\":\"1\",\"cashiList\":[{\"mgniId\":\"MGI20221017012143744\",\"accNo\":\"1\",\"ccy\":\"TWD\",\"amt\":1000.0000000},{\"mgniId\":\"MGI20221017012143744\",\"accNo\":\"2\",\"ccy\":\"TWD\",\"amt\":700.0000000}],\"utime\":\"2022-10-17T00:00:00\",\"preason\":\"\",\"itype\":\"\"}]";

        ObjectMapper om = new ObjectMapper();
//        List<Mgni>b=om.readValue(response, List.class);
//        Mgni b= om.readValue(response, Mgni.class);

//        when(this.transferService.searchTargetMgni(any())).thenReturn(b);
//
//        System.out.println(b);
//        String expectedStr = om.writeValueAsString(b);

        String requestBoby = "{\"id\":\"MGI20221017012143744\"}";
        ResultActions resultActions =
                // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
                mockMvc.perform(
                                get("/produce/search").content(requestBoby).contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON)
                        )
                        // 輸出整個回應結果訊息
                        .andDo(print());
        // 取得回傳物件
        String actual = resultActions.andReturn().getResponse().getContentAsString();
//        List<Mgni> act= om.readValue(response,List.class);
//
//        System.out.println(b);
//        System.out.println(expectedStr);
//        System.out.println(actual);
//
        Assert.assertEquals(response,actual);
    }
}
