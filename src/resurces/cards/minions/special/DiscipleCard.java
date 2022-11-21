package resurces.cards.minions.special;

import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class DiscipleCard extends MinionClass {

    public DiscipleCard(String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    public void godsPlan(MinionClass card) {
        card.setHealth(card.getHealth() + 2);
    }
}
