package com.example.jms_demo_2.controller.Producer;


import com.example.jms_demo_2.service.GetResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class PTPProducer {
        @Autowired
    private GetResponseService getResponseService;
    @Autowired
    JmsTemplate jmsTemplate;
    @JmsListener(destination = "request.queue")
    public void receive(final Message jsonMessage) throws Exception {
        String messageData = null;
        System.out.println("uid " + jsonMessage.getStringProperty("uid"));
        if(jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)jsonMessage;
            messageData = textMessage.getText();
            System.out.println("receive request:"+messageData);
            String response= getResponseService.getResponse(messageData);

            jmsTemplate.send("response.queue", messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(response);
                message.setStringProperty("uid", jsonMessage.getStringProperty("uid"));
                //message.setStringProperty("uid", "111");
                return message;
            });
            System.out.println("send response data");
        }
    }

//    @Autowired
//    private GetResponseService getResponseService;
//    @JmsListener(destination = "request.queue", containerFactory = "queueConnectionFactory")
//    @SendTo("response.queue")
//    public String getRequest(String request) throws Exception {
//        String response= getResponseService.getResponse(request);
//        return response;
//    }
}