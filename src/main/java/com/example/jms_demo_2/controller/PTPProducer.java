package com.example.jms_demo_2.controller;


import com.example.jms_demo_2.controller.dto.request.DepositRequest;
import com.example.jms_demo_2.controller.dto.request.SearchMgniRequest;
import com.example.jms_demo_2.service.TransferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class PTPProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Queue requestQueue;
    @Autowired
    private Queue responseQueue;
    @Autowired
    private TransferService transferService;

    private static ObjectMapper om = new ObjectMapper();
    @JmsListener(destination = "request.queue", containerFactory = "queueConnectionFactory")
    @SendTo("response.queue")
    public String getRequest(String request) throws Exception {

        JSONObject jsonObject = new JSONObject(request);

        String requestType = jsonObject.getString("requestType");

        JSONObject requestBodyJson = jsonObject.getJSONObject("request");
        String requestBody=JSONObject.valueToString(requestBodyJson);

        String response="";

        switch (requestType) {
//            case "0": {
//                bw.write(DatabaseCRUD.getTargetCashi(request.getString("id")));
//                break;
//            }
            case "1": {//bankService.deposit(objectMapper.readValue(requestBody, DepositRequest.class))
                response = json(transferService.searchTargetMgni(om.readValue(requestBody, SearchMgniRequest.class)));
                System.out.println(response);
                break;
            }
//            case "2": {
//                bw.write(DatabaseCRUD.dynamicQueryMgni(request));
//                break;
//            }
            case "3": {
                response = json(transferService.createMgni(om.readValue(requestBody, DepositRequest.class)));
                System.out.println(response);
                break;
            }
            case "4": {
                response = json(transferService.updateMgni(jsonObject.getString("id"),om.readValue(requestBody, DepositRequest.class)));
                System.out.println(response);
                break;
            }
            case "5": {
                response = json(transferService.deleteMgni(requestBodyJson.getString("id")));
                System.out.println(response);
                break;
            }
            default: {
                response="請輸入有效查詢資料";
                break;
            }
        }
        return response;
    }




    private static String json(Object object) throws Exception {
        om.findAndRegisterModules();
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    private void jmsTemplate(Queue queue, String response) {
        jmsTemplate.convertAndSend(queue, response);
    }

}