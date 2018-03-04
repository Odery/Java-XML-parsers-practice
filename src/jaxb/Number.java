package jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "number")
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
}
