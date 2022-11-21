package resurces.cards.minions.special;

import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class MirajCard extends MinionClass {

    public MirajCard(String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    public void skyjack(MinionClass card) {
        int x = getHealth();
        setHealth(card.getHealth());
        card.setHealth(x);
    }
}
