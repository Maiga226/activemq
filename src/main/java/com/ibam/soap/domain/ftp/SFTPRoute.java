package com.ibam.soap.domain.ftp;

import com.ibam.soap.web.rest.PublisherController;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

public class SFTPRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(SFTPRoute.class);
    private final String filePath;
    private final PublisherController publisherController;
    public SFTPRoute(String filePath, PublisherController publisherController) {
        this.filePath=filePath;
        this.publisherController = publisherController;
    }
    @Override
    public void configure() throws Exception {
        log.debug("Entrer de la route---------------"+filePath);
        from("file:"+"D:\\xml\\file\\"+"?move=done")
            .log(LoggingLevel.INFO, logger, "Download file ${file:name} complete.")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    String name = exchange.getIn().toString();
                    exchange.getOut().setBody(name);
                    System.out.println("-----------**-------"+exchange);
                    System.out.println("-----------**-------"+name);
                    System.out.println("-----------**-------");
                    if(name.contains(".xml")){
                        log.debug("Strat service");
                        //exchange.getIn().getHeader("CamelBeanMultiParameterArray");
                        File file = exchange.getIn().getBody(File.class);
                        FileInputStream input = new FileInputStream(file);
                        //MultipartFile multipartFile = new MockMultipartFile("file",file.getName(), "text/xml", IOUtils.toByteArray(input));
                        publisherController.publish(file);
                        input.close();
                    }else {
                        String zipFile=filePath+"/"+name;
                    }

                }
            });

    }

}
