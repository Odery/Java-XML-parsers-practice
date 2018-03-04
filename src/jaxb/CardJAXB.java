package jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "card")
public class CardJAXB {
    @XmlElement
    private String name;
    @XmlElement
    private String profession;
    @XmlElement(name = "number", type = Number.class)
    private List<Number> numbers;
    @XmlElement
    private String email;

    public CardJAXB() {
    }

    public void show() {
        System.out.println("Name: " + name);
        System.out.println("Profession: " + profession);
        numbersToString();
        System.out.println("Email: " + email);
        System.out.println("-------------------------------");
    }

    private void numbersToString() {
        for (Number number : numbers)
            number.show();
    }
}
