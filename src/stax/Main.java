package stax;

import sax.Card;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardStreamStAXParser parser = new CardStreamStAXParser();
        try {
            List<Card> cards = parser.readXML(new File("BusinessCard.xml"));
            parser.toXML(cards, new File("myStAX.xml"));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
