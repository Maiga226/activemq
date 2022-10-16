package com.ibam.soap.service;

import com.ibam.soap.domain.ftp.SFTPRoute;
import com.ibam.soap.web.rest.PublisherController;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SFTPService {
    private final Logger log= LoggerFactory.getLogger(SFTPService.class);

    private final PublisherController publisherController;
    @Value("${ibam.xml.file}")
    private String filePath;

    public SFTPService(PublisherController publisherController) {
        this.publisherController = publisherController;
    }


    public void downloadFile() throws Exception {
        CamelContext context;
        context=new DefaultCamelContext();
        try {
            context.addRoutes(new SFTPRoute(filePath,publisherController));
            context.start();

        } catch (Exception ex) {
            log.debug(" echec du route" +ex.getMessage());
        }finally{
            Thread.sleep(10000); // pour avoir le temps d’exécuter la route
            context.stop();
        }


    }

    @Scheduled(cron = "*/2 * * * * *")
    public void executeChargementFichier() throws Exception {
        log.debug("Starte*-----------------");
        downloadFile();
    }


}
