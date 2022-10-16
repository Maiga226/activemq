package com.ibam.soap.web.rest;

import com.ibam.soap.domain.CompteResponse;
import com.ibam.soap.domain.Message;
import com.ibam.soap.service.ConsumerService;
import com.ibam.soap.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/api/message")
public class PublisherController {
    private final ProducerService producerService;
    private final ConsumerService consumerService;

    public PublisherController(ProducerService producerService, ConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Message message) {
        try {
            producerService.send(message);
            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/publish-compte")
    public ResponseEntity<String> publish(@RequestParam("file") File file)  {
        try {
            //String type = file.getContentType();

            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[10];
            StringBuilder sb = new StringBuilder();
            while (fis.read(buffer) != -1) {
                sb.append(new String(buffer));
                buffer = new byte[10];
            }
            fis.close();
            String content = sb.toString();
            System.out.println("document"+content);
            CompteResponse compteResponse =consumerService.getCompteResponse(content);
            System.out.println("compteResponse{}"+compteResponse.toString());
            producerService.sendInfoCompte(compteResponse);

            return new ResponseEntity<>("Sent", HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
