package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sax.Card;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

    public void cardToXML(List<Card> cards, String file) throws ParserConfigurationException, TransformerException {
        if (cards == null || cards.isEmpty())
            throw new NullPointerException("List<Card> = null or empty");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("cards");
        Element cardNode, name, profession, email;

        for (Card card : cards) {
            cardNode = doc.createElement("card");

            name = doc.createElement("name");
            name.setTextContent(card.getName());
            cardNode.appendChild(name);

            profession = doc.createElement("profession");
            profession.setTextContent(card.getProfession());
            cardNode.appendChild(profession);

            setNumbers(card, cardNode, doc);

            email = doc.createElement("email");
            email.setTextContent(card.getEmail());
            cardNode.appendChild(email);
            root.appendChild(cardNode);
        }
        doc.appendChild(root);
        write(doc, new File(file));
    }

    private void setNumbers(Card card, Element cardNode, Document doc) {
        Element number;

        if (card.getHomeNumber() != null) {
            number = doc.createElement("number");
            if (card.getHomeNumber().contains("(primary)")) {
                number.setTextContent((card.getHomeNumber().replace(" (primary)", "")));
                number.setAttribute("type", "home");
                number.setAttribute("primary", "primary");
            } else {
                number.setTextContent(card.getHomeNumber());
                number.setAttribute("type", "home");
            }
            cardNode.appendChild(number);
        }

        if (card.getWorkNumber() != null) {
            number = doc.createElement("number");
            if (card.getWorkNumber().contains("(primary)")) {
                number.setTextContent((card.getWorkNumber().replace(" (primary)", "")));
                number.setAttribute("type", "work");
                number.setAttribute("primary", "primary");
            } else {
                number.setTextContent(card.getWorkNumber());
                number.setAttribute("type", "work");
            }
            cardNode.appendChild(number);
        }

        if (card.getMobileNumber() != null) {
            number = doc.createElement("number");
            if (card.getMobileNumber().contains("(primary)")) {
                number.setTextContent((card.getMobileNumber().replace(" (primary)", "")));
                number.setAttribute("type", "mobile");
                number.setAttribute("primary", "primary");
            } else {
                number.setTextContent(card.getMobileNumber());
                number.setAttribute("type", "mobile");
            }
            cardNode.appendChild(number);
        }
    }

    private void write(Document doc, File file) throws TransformerException {
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        transformer.transform(source, result);
    }
}
