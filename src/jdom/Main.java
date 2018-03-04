package jdom;

import org.jdom2.JDOMException;
import sax.Card;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JDOMCardParser parser = new JDOMCardParser();
        try {
            List<Card> cards = parser.readXML(new File("BusinessCard.xml"));
            for (Card card : cards)
                card.show();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
    }
}
