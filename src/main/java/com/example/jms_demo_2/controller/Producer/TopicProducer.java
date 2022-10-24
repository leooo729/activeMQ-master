package com.example.jms_demo_2.controller.Producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Topic;

@RestController
@RequestMapping("/topic")
public class TopicProducer {
    @Autowired
    private Topic topic;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @GetMapping
    public void send() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString("Hello world");
        jmsMessagingTemplate.convertAndSend(topic, json);
    }

    public void sendMsg(String msg) throws Exception { //test
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(msg);

        jmsMessagingTemplate.convertAndSend(topic, json);
    }

}
