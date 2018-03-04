package jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "profession", "numbers", "email"})
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
