package com.example.jms_demo_2.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.region.policy.RedeliveryPolicyMap;
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
    //  配置Bean 如同spring框架下的applicationcontext.xml
    private String brokerUrl = "tcp://localhost:61616";

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


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        //建立ConnectionFactory工廠，需要填入名稱、密码、連接地址
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();

        //ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD,
        //failover:(tcp://localhost:61616)?initialReconnectDelay=1000&maxReconnectDelay=30000"
        //initialReconnectDelay 第一次嘗試重連之前等待的時間 //maxReconnectDelay 兩次重連之間的最大時間間隔

        activeMQConnectionFactory.setBrokerURL(brokerUrl);

        //若消息达到尝试次数消费失败或者超时等，会进入死信队列ActiveMQ.DLQ
        RedeliveryPolicy queuePolicy = new RedeliveryPolicy();
        queuePolicy.setInitialRedeliveryDelay(0); //初始重發延遲時間
        queuePolicy.setRedeliveryDelay(1000);//重發延遲時間
        queuePolicy.setUseExponentialBackOff(false); //是否在每次失敗重發時，增長等待時間
        queuePolicy.setMaximumRedeliveries(5);// 最大重傳次数

//        RedeliveryPolicyMap map = activeMQConnectionFactory.getRedeliveryPolicyMap();
//        map.put(new ActiveMQQueue("test.queue"), queuePolicy);
        activeMQConnectionFactory.setRedeliveryPolicy(queuePolicy);

        return activeMQConnectionFactory;
    }

    @Bean //消息的發送接收
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
    }

}
