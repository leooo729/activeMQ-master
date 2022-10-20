package com.example.jms_demo_2.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.backoff.FixedBackOff;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms//創建一個配置類並使用@EnableJms註釋啟用 JMS 並配置ConnectionFactory以連接到我們的 ActiveMQ 實例
public class JmsConfig {
    //  配置Bean 如同spring框架下的applicationcontext.xml
    private String brokerUrl = "tcp://localhost:61616";

    //伺服端必須設定好ConnectionFactory以及Destination，JMS端點取得ConnectionFactory，
    // 使用其與伺服端建立連線，連線以Connection介面定義。
    @Bean //消息的發送接收
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
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
//-----------------------------------------------------------------------------------------------------
    @Bean
    public JmsListenerContainerFactory<?> queueConnectionFactory(ConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configure) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configure.configure(factory, connectionFactory);
        factory.setBackOff(new FixedBackOff(5000,5));//activeMQ配置監聽器容器重連服務器策略
        factory.setPubSubDomain(false);

        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> topicConnectionFactory(ConnectionFactory connectionFactory,
                                                                 DefaultJmsListenerContainerFactoryConfigurer configure) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configure.configure(factory, connectionFactory);
        factory.setBackOff(new FixedBackOff(5000,5));
        factory.setPubSubDomain(true);
        return factory;
    }

//    @Bean
//    public DynamicDestinationResolver destinationResolver() {
//        return new DynamicDestinationResolver() {
//            @Override
//            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException, JMSException {
//                if (destinationName.endsWith(".topic")) {
//                    pubSubDomain = true;
//                }
//                return super.resolveDestinationName(session, destinationName, pubSubDomain);
//            }
//        };
//    }

}
