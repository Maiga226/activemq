package com.ibam.soap.service;


import com.ibam.soap.domain.Compte;
import com.ibam.soap.domain.CompteResponse;
import com.ibam.soap.domain.Util;
import com.ibam.soap.repository.CompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class ConsumerService {
    private final Logger log = LoggerFactory.getLogger(ConsumerService.class);
    private CompteRepository compteRepository;
    public ConsumerService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public CompteResponse getCompteResponse(String fileCompte) throws JAXBException {
        CompteResponse compteResponse=new CompteResponse();
        log.debug("-----------------------------DEBUT UNMARSHALL------------------------------");
        String reponse=fileCompte.replace("xmlns:ns2=\"bf.univ.ibam\" id=\"100\"","");
        reponse=reponse.replace("ns2:","");
        System.out.println("reponse"+reponse);

        compteResponse= Util.unmarshall(reponse,CompteResponse.class);

        return compteResponse;

    }

    public Compte saveCompte(Compte compte){
        if(compte.getId()!=null){
            //Updated compte
        }else {
            compteRepository.save(compte);
        }
        return compte;
    }
}
