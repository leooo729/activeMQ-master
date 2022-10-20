package com.example.jms_demo_2.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.util.backoff.FixedBackOff;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

@Configuration
@EnableJms

//創建一個配置類並使用@EnableJms註釋啟用 JMS 並配置ConnectionFactory以連接到我們的 ActiveMQ 實例
public class JmsConfig {
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
