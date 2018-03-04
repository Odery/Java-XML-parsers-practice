package simple;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "cards")
public class Cards {
    @ElementList(name = "card", type = CardSXS.class, inline = true)
    private List<CardSXS> cards;

    public Cards() {
    }

    public List<CardSXS> getCards() {
        return cards;
    }

    public void setCards(List<CardSXS> cards) {
        this.cards = cards;
    }
}
