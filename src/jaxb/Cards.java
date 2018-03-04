package jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cards")
public class Cards {
    @XmlElement(name = "card", type = CardJAXB.class)
    private List<CardJAXB> cards;

    public Cards() {
    }

    public List<CardJAXB> getList() {
        return cards;
    }
}
