package resurces.output;


import resurces.cards.Card;

import java.util.ArrayList;

public class HeroOut extends Card {
    private int health;
    public final void setHealth(final int health) {
        this.health = health;
    }
    public final int getHealth() {
        return health;
    }
    public HeroOut(final int mana, final String description, final ArrayList<String> colors,
                   final String name, final int health) {
        super(mana, description, colors, name);
        this.health = health;
    }
}
