package com.ibam.soap.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibam.soap.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    @Value("${spring.artemis.embedded.queues}")
    private String queueDestination;

    public Producer(JmsTemplate jmsTemplate, ObjectMapper mapper) {
        this.jmsTemplate = jmsTemplate;
        this.mapper = mapper;
    }

    public void send(Message message) throws JsonProcessingException {
        jmsTemplate.convertAndSend(queueDestination, mapper.writeValueAsString(message));
    }
}
