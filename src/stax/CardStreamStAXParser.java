package stax;

import javanet.staxutils.IndentingXMLStreamWriter;
import sax.Card;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CardStreamStAXParser {
    private IndentingXMLStreamWriter writer;
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

    public void toXML(List<Card> cards, File fileName) throws FileNotFoundException, XMLStreamException {
        this.cards = cards;
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter w = factory.createXMLStreamWriter(new FileOutputStream(fileName));
        writer = new IndentingXMLStreamWriter(w);
        writeCards();
        writer.flush();
        writer.close();
    }

    private void writeCards() throws XMLStreamException {
        writer.writeStartDocument();
        writer.writeStartElement("cards");

        for (Card card : cards) {
            writer.writeStartElement("card");

            writeElement("name", card.getName());
            writeElement("profession", card.getProfession());
            writeElement("email", card.getEmail());
            writeNumbers(card);
            writeElement("email", card.getEmail());

            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();
    }

    private void writeElement(String name, String data) throws XMLStreamException {
        writer.writeStartElement(name);
        writer.writeCharacters(data);
        writer.writeEndElement();

    }

    private void writeNumbers(Card card) throws XMLStreamException {
        boolean primary = false;
        String data;
        if (card.getWorkNumber() != null) {
            if (card.getWorkNumber().contains("(primary)")) {
                primary = true;
                data = (card.getWorkNumber().replace(" (primary)", ""));
            } else
                data = card.getWorkNumber();
            writeNumber(data, "work", primary);
            primary = false;
        }
        if (card.getHomeNumber() != null) {
            if (card.getHomeNumber().contains("(primary)")) {
                primary = true;
                data = (card.getHomeNumber().replace(" (primary)", ""));
            } else
                data = card.getHomeNumber();
            writeNumber(data, "home", primary);
            primary = false;
        }
        if (card.getMobileNumber() != null) {
            if (card.getMobileNumber().contains("(primary)")) {
                primary = true;
                data = (card.getMobileNumber().replace(" (primary)", ""));
            } else
                data = card.getMobileNumber();
            writeNumber(data, "mobile", primary);
        }
    }

    private void writeNumber(String data, String type, boolean primary) throws XMLStreamException {
        writer.writeStartElement("number");
        writer.writeAttribute("type", type);
        if (primary)
            writer.writeAttribute("primary", "primary");
        writer.writeCharacters(data);
        writer.writeEndElement();

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
