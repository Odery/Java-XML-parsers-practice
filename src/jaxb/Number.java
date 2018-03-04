package jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "number")
@XmlAccessorType(XmlAccessType.FIELD)
public class Number {
    @XmlValue
    private String number;
    @XmlAttribute
    private String type;
    @XmlAttribute
    private String primary;

    public Number() {
    }

    public void show() {
        System.out.println(type + " number: " + number + " primary: " + primary);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }
}
