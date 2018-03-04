package stax;

import sax.Card;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CardStreamStAXParser {
    private List<Card> cards;
    private Card card;

    CardStreamStAXParser() {
        cards = new ArrayList<>();
    }

    public List<Card> readXML(File file) throws FileNotFoundException, XMLStreamException {
        InputStream stream = new FileInputStream(file);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(stream);

        readCards(reader);

        return cards;
    }

    private void readCards(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            if (reader.next() == XMLEvent.START_ELEMENT) {

                switch (reader.getName().toString()) {
                    case "card":
                        card = new Card();
                        cards.add(card);
                        break;
                    case "name":
                        card.setName(reader.getElementText());
                        break;
                    case "profession":
                        card.setProfession(reader.getElementText());
                        break;
                    case "email":
                        card.setEmail(reader.getElementText());
                        break;
                    case "number":
                        readNumbers(reader.getAttributeCount(), reader.getAttributeValue(0), reader.getElementText());

                }
            }
        }
    }

    private void readNumbers(int count, String value, String text) {
        if (count > 1)
            text += " (primary)";

        switch (value) {
            case "work":
                card.setWorkNumber(text);
                break;
            case "home":
                card.setHomeNumber(text);
                break;
            case "mobile":
                card.setMobileNumber(text);
        }
    }
}
