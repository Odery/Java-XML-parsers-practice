package simple;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "card")
public class CardSXS {
    @Element
    private String name;
    @Element
    private String profession;
    @ElementList(name = "number", type = Number.class, inline = true)
    private List<Number> numbers;
    @Element
    private String email;

    public CardSXS() {
    }

    public void show() {
        System.out.println("Name: " + name);
        System.out.println("Profession: " + profession);
        numbersToString();
        System.out.println("Email: " + email);
        System.out.println("------------------------------");
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
