package dom;

import org.xml.sax.SAXException;
import sax.Card;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CardDOMParser parser = new CardDOMParser(new File("BusinessCard.xml"));
            List<Card> cards = parser.getCards();
            for (Card card : cards)
                card.show();
            parser.cardToXML(cards, "myCustom.xml");
        } catch (IOException | SAXException | ParserConfigurationException | NullPointerException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
