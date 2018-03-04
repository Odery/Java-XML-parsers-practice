package simple;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
        Serializer serializer = new Persister();

        try {
            FileReader reader = new FileReader(new File("BusinessCard.xml"));
            Cards cards = serializer.read(Cards.class, reader);

            FileWriter writer = new FileWriter(new File("mySimple.xml"));
            serializer.write(cards, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
