package com.ibam.soap.service;

import com.ibam.soap.domain.Compte;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);
    private final ConsumerService consumerService;

    public MessageConsumer(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @JmsListener(destination = "${spring.artemis.embedded.queues}")
    public void messageListener(String message) throws JSONException {
        LOGGER.info("Message received, {}",message);
        Compte compte=transformeMessage(message);
        compte=consumerService.saveCompte(compte);
        System.out.println("compte"+compte);
    }


    public Compte transformeMessage(String message) throws JSONException {
        JSONObject obj = new JSONObject(message);
        String nom=obj.getString("nomClient");
        String prenom=obj.getString("prenomClient");
        String numero=obj.getString("numerCompte");
        Double solde=obj.getDouble("solde");
        String date=obj.getString("dateSolde");
        System.out.println("dateSolde"+obj.getString("dateSolde"));
        String[] newDate=date.split("-");
        Compte compte=new Compte();
        compte.setNomClient(nom);
        compte.setPrenomClient(prenom);
        compte.setNumeroCompte(numero);
        compte.setSoldeClient(new BigDecimal(solde));
        System.out.println("newDate"+newDate[0]);
        String mDate=newDate[2]+"-"+newDate[1]+"-"+newDate[0];
        System.out.println("mDate"+mDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateF = LocalDate .parse(mDate,formatter);
        compte.setDateSolde(dateF);
        return compte;
    }


}
