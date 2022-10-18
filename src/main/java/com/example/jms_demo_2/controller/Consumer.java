package com.example.jms_demo_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class Consumer {

//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    @Autowired
//    private Queue queue;

    @JmsListener(destination = "test.queue")
    public void consumeMessage(String message) {
        System.out.println("============================================\n");
        System.out.println("Message received from activemq queue---\n" + message);
    }
}

