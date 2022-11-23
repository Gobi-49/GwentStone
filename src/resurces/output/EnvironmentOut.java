package resurces.output;

import resurces.cards.Card;

import java.util.ArrayList;

public class EnvironmentOut extends Card {
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public EnvironmentOut(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }
}
