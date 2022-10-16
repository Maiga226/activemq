package com.ibam.soap.jms;


import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@EnableJms
public class MessageConfiguration {

   /* @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        return connectionFactory;
    }*/
   @Bean
   public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
       DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();

       jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
       jmsListenerContainerFactory.setConcurrency("2-4");

       return jmsListenerContainerFactory;
   }


    /*
     * Optionally you can use cached connection factory if performance is a big concern.
     *//*
    @Bean
    public ConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(connectionFactory());
        connectionFactory.setSessionCacheSize(10);
        return connectionFactory;
    }
    */
    /*
     * Message listener container, used for invoking messageReceiver.onMessage on message reception.
     *//*
    @Bean
    public MessageListenerContainer getContainer(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        return container;
    }
    */
    /*
     * Used for Sending Messages.
     *//*
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }
    @Bean
    MessageConverter converter(){
        return new SimpleMessageConverter();
    }
}*/
}

