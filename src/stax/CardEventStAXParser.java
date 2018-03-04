package stax;

import sax.Card;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//This Parser have no write methods because they pretty the same
public class CardEventStAXParser {
    private List<Card> cards;
    private Card card;

    CardEventStAXParser() {
        cards = new ArrayList<>();
    }

    public List<Card> readXML(File file) throws FileNotFoundException, XMLStreamException {
        InputStream stream = new FileInputStream(file);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader reader = factory.createXMLEventReader(stream);

        readCards(reader);

        return cards;
    }

    private void readCards(XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement element = event.asStartElement();

                switch (element.getName().toString()) {
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
                        readNumbers(element.getAttributes(),
                                element.getAttributeByName(new QName("type")).getValue(),
                                reader.getElementText());

                }
            }
        }
    }

    private void readNumbers(Iterator<Attribute> iterator, String value, String text) {
        //checking how many attributes in number (1 or 2)
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            i++;
        }
        if (i > 1)
            text += " (primary)";

        switch (value) {
            case "home":
                card.setHomeNumber(text);
                break;
            case "work":
                card.setWorkNumber(text);
                break;
            case "mobile":
                card.setMobileNumber(text);
        }
    }
}
