package com.ibam.soap.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibam.soap.domain.CompteResponse;
import com.ibam.soap.domain.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    @Value("${spring.artemis.embedded.queues}")
    private String queueDestination;


    public ProducerService(JmsTemplate jmsTemplate, ObjectMapper mapper) {
        this.jmsTemplate = jmsTemplate;
        this.mapper = mapper;
    }

    public void send(Message message) throws JsonProcessingException {
        jmsTemplate.convertAndSend(queueDestination, mapper.writeValueAsString(message));
    }

    public void sendInfoCompte(CompteResponse compteResponse) throws JsonProcessingException {
        jmsTemplate.convertAndSend(queueDestination, mapper.writeValueAsString(compteResponse));
    }
}
