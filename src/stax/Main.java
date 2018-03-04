package stax;

import sax.Card;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardEventStAXParser parser = new CardEventStAXParser();
        try {
            List<Card> cards = parser.readXML(new File("BusinessCard.xml"));
            for (Card card : cards)
                card.show();
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
