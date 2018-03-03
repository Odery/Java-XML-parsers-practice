package sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CardHandler cardHandler = new CardHandler(new File("BusinessCard.xml"));
        List<Card> cards = null;
        try {
            cards = cardHandler.startParsing();
        }catch (IOException | ParserConfigurationException | SAXException ex){
            ex.printStackTrace();
        }

        for (Card card: cards)
            card.show();
    }
}
