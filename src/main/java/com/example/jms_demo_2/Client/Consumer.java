package com.example.jms_demo_2.Client;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    @Autowired
//    private Queue queue;

    @JmsListener(destination = "test.queue", containerFactory = "queueConnectionFactory")
    public void consumeMessage(String message) {
        System.out.println("============================================\n");
        System.out.println("Message received from activemq queue---\n" + message);

    }

    @JmsListener(destination = "test.topic", containerFactory = "topicConnectionFactory")
    public void readActiveQueue(String message) {
        System.out.println("============================================\n");
        System.out.println("Message received from activemq queue---\n" + message);
    }
}

