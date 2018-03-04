package jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Cards.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Cards cards = (Cards) unmarshaller.unmarshal(new File("BusinessCard.xml"));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(cards, new File("myJAXB.xml"));

        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
