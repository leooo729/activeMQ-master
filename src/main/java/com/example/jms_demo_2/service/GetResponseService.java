package com.example.jms_demo_2.service;

import com.example.jms_demo_2.controller.dto.request.DepositRequest;
import com.example.jms_demo_2.controller.dto.request.SearchMgniRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetResponseService {
    private final TransferService transferService;
    private static ObjectMapper om = new ObjectMapper();

    public String getResponse(String request) throws Exception {

        JSONObject jsonObject = new JSONObject(request);

        String requestType = jsonObject.getString("requestType");

        JSONObject requestBodyJson = jsonObject.getJSONObject("request");
        String requestBody=JSONObject.valueToString(requestBodyJson);

        String response="";


        switch (requestType) {

            case "search": {
                response = toString(transferService.searchTargetMgni(om.readValue(requestBody, SearchMgniRequest.class)));
                System.out.println(response);
                break;
            }

            case "create": {
                response = toString(transferService.createMgni(om.readValue(requestBody, DepositRequest.class)));
                System.out.println(response);
                break;
            }
            case "update": {
                response = toString(transferService.updateMgni(jsonObject.getString("id"),om.readValue(requestBody, DepositRequest.class)));
                System.out.println(response);
                break;
            }
            case "delete": {
                response = toString(transferService.deleteMgni(requestBodyJson.getString("id")));
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
    private static String toString(Object object) throws Exception {
        om.findAndRegisterModules();
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
