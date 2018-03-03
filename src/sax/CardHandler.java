package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardHandler extends DefaultHandler {
    private List<Card> cards;
    private StringBuilder builder;
    private Card card;
    private File file;
    private boolean isPrimary;
    private String mobileType;

    CardHandler(File file){
        this.file = file;
        cards = new ArrayList<Card>();
    }

    public List<Card> startParsing() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser parser = factory.newSAXParser();
        builder = new StringBuilder();
        parser.parse(file,this);
        return cards;
    }

    @Override
    public void startElement (String uri, String localName, String qName, Attributes attributes){
        if (qName.equals("card"))
            card = new Card();
        else if (qName.equals("number")){
            if (attributes.getLength() ==2)
                isPrimary = true;
            switch (attributes.getValue(0)) {
                case "work":
                    mobileType = "work";
                    break;
                case "home":
                    mobileType = "home";
                    break;
                case "mobile":
                    mobileType = "mobile";
                    break;
            }
        }
    }

    @Override
    public void endElement (String uri, String localName, String qName){
        switch (qName){
            case "card":
                cards.add(card);
                break;
            case "name":
                card.setName(builder.toString());
                break;
            case "profession":
                card.setProfession(builder.toString());
                break;
            case "email":
                card.setEmail(builder.toString());
                break;
            case "number":
                if (isPrimary) {
                    builder.append(" (primary)");
                    isPrimary = false;
                }
                switch (mobileType){
                    case "work":
                        card.setWorkNumber(builder.toString());
                        break;
                    case "home":
                        card.setHomeNumber(builder.toString());
                        break;
                    case "mobile":
                        card.setMobileNumber(builder.toString());
                        break;
                }
        }
    }

    @Override
    public void characters(char[] chars,int start,int length){
        builder.delete(0,builder.length());
        builder.append(chars,start,length);
    }
}
