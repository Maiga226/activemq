package com.ibam.soap.domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Util {

    public static <T extends String> T marshall(Object classObject, Class<T> resultClass) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classObject.getClass());
        Marshaller marchaller = context.createMarshaller();
        marchaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marchaller.marshal(classObject, sw);

        return (T) (String) sw.toString();
        // return String.format("<?xml version=\"1.0\"?>");
    }

    public static <T extends Object> T unmarshall(String body, Class<T> resultClass) throws JAXBException {
        String strEntry = body.trim();
        StringReader reader = new StringReader(strEntry.replaceAll("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", ""));
        JAXBContext context = JAXBContext.newInstance(resultClass);
        Unmarshaller unmarchaller = context.createUnmarshaller();
        return (T) (Object) unmarchaller.unmarshal(reader);
    }

}
