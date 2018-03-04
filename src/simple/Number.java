package simple;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "number")
public class Number {
    @Text
    private String number;
    @Attribute
    private String type;
    @Attribute(required = false)
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
