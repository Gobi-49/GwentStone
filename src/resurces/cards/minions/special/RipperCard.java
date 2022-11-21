package resurces.cards.minions.special;

import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class RipperCard extends MinionClass {

    public RipperCard(String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(name, colors, description, mana, attackDamage, health);
    }
    public void WeakKnees(MinionClass card) {
        card.setAttackDamage(card.getAttackDamage() - 2);
    }
}
