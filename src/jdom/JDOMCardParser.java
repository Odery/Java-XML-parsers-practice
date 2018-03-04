package jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import sax.Card;

import java.io.File;
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
}
