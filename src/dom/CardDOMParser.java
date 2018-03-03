package dom;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sax.Card;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardDOMParser {
    private Card card;
    private List<Card> cards;
    private Document document;

    CardDOMParser(File file) throws IOException, SAXException, ParserConfigurationException {
        cards = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(file);
    }

    public List<Card> getCards() {
        NodeList nodes = document.getElementsByTagName("card");
        for (int i = 0; i < nodes.getLength(); i++) {
            card = parseCard(nodes.item(i).getChildNodes());
            cards.add(card);
        }
        return cards;
    }

    private Card parseCard(NodeList nodes) {
        card = new Card();
        card.setName(nodes.item(1).getTextContent());
        card.setProfession(nodes.item(3).getTextContent());
        card.setEmail(nodes.item(nodes.getLength() - 2).getTextContent());
        parseNumbers(nodes, card);
        return card;
    }

    // number must be after second element and before last one
    private void parseNumbers(NodeList nodes, Card card) {
        String primary;
        for (int i = 5; i < nodes.getLength() - 2; i += 2) {
            NamedNodeMap attributes = nodes.item(i).getAttributes();
            if (attributes.getLength() == 2)
                primary = " (primary)";
            else
                primary = "";
            switch (attributes.item(attributes.getLength() - 1).getTextContent()) {
                case "work":
                    card.setWorkNumber((nodes.item(i).getTextContent() + primary));
                    break;
                case "home":
                    card.setHomeNumber((nodes.item(i).getTextContent() + primary));
                    break;
                case "mobile":
                    card.setMobileNumber((nodes.item(i).getTextContent() + primary));
                    break;
            }
        }
    }
}
