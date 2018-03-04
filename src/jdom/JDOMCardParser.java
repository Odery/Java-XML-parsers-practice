package jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import sax.Card;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JDOMCardParser {
    private List<Card> cards;
    private Document document;

    JDOMCardParser() {
        cards = new ArrayList<>();
    }

    public List<Card> readXML(File file) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        document = builder.build(file);
        fillList();

        return cards;
    }

    public void toXML(List<Card> cards, String fileName) throws IOException {
        this.cards = cards;
        document = new Document();
        document.setRootElement(new Element("cards"));
        setElements(document.getRootElement());
        writeOut(new File(fileName));
    }

    private void fillList() {
        Element root = document.getRootElement();
        List<Element> list = root.getChildren("card");

        for (Element element : list) {
            cards.add(createCard(element));
        }
    }

    private Card createCard(Element element) {
        Card card = new Card();
        card.setName(element.getChildText("name"));
        card.setProfession(element.getChildText("profession"));
        card.setEmail(element.getChildText("email"));
        setNumbers(card, element.getChildren("number"));

        return card;
    }

    private void setNumbers(Card card, List<Element> numbers) {
        String result;
        for (Element element : numbers) {
            result = element.getText();
            if (element.getAttributes().size() > 1)
                result += " (primary)";

            switch (element.getAttribute("type").getValue()) {
                case "work":
                    card.setWorkNumber(result);
                    break;
                case "home":
                    card.setHomeNumber(result);
                    break;
                case "mobile":
                    card.setMobileNumber(result);
                    break;
            }
        }
    }

    //Orders matter!
    private void setElements(Element root) {
        Element parent;
        for (Card card : cards) {
            parent = new Element("card");
            parent.addContent(new Element("name").setText(card.getName()));
            parent.addContent(new Element("profession").setText(card.getProfession()));
            addNumbers(parent, card);
            parent.addContent(new Element("email").setText(card.getEmail()));

            root.addContent(parent);
        }
    }

    private void addNumbers(Element parent, Card card) {
        if (card.getWorkNumber() != null) {
            Element number = new Element("number").setAttribute("type", "work");
            if (card.getWorkNumber().contains("(primary)")) {
                number.setAttribute("primary", "primary");
                parent.addContent(number.setText(card.getWorkNumber().replace(" (primary)", "")));
            } else
                parent.addContent(number.setText(card.getWorkNumber()));
        }
        if (card.getHomeNumber() != null) {
            Element number = new Element("number").setAttribute("type", "home");
            if (card.getHomeNumber().contains("(primary)")) {
                number.setAttribute("primary", "primary");
                parent.addContent(number.setText(card.getHomeNumber().replace(" (primary)", "")));
            } else
                parent.addContent(number.setText(card.getHomeNumber()));
        }
        if (card.getMobileNumber() != null) {
            Element number = new Element("number").setAttribute("type", "mobile");
            if (card.getMobileNumber().contains("(primary)")) {
                number.setAttribute("primary", "primary");
                parent.addContent(number.setText(card.getMobileNumber().replace(" (primary)", "")));
            } else
                parent.addContent(number.setText(card.getMobileNumber()));
        }
    }

    private void writeOut(File file) throws IOException {
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        FileWriter writer = new FileWriter(file);

        outputter.output(document, writer);
    }
}
