package com.example.jms_demo_2.controller.Producer;


import com.example.jms_demo_2.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class PTPProducer {
    @Autowired
    private TransferService transferService;

    private static ObjectMapper om = new ObjectMapper();
    @JmsListener(destination = "request.queue", containerFactory = "queueConnectionFactory")
    @SendTo("response.queue")
    public String getRequest(String request) throws Exception {
        String response=transferService.getResponse(request);
        return response;
    }
}