package com.example.jms_demo_2.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms //開啟jms註解
public class SpringActiveMQConfig {
    @Value("${queue}")
    private String testQueue;
    @Value("${topic}")
    private String testTopic;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(testQueue);
    }
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(testTopic);
    }

}
