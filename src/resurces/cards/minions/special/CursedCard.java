package resurces.cards.minions.special;

import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class CursedCard extends MinionClass {

    public CursedCard(String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    public void shapeshift(MinionClass card) {
        int x = card.getHealth();
        card.setHealth(card.getAttackDamage());
        card.setAttackDamage(x);
    }
}
