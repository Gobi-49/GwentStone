package resurces.cards.minions.regular;

import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class BerserkerCard extends MinionClass {

    public BerserkerCard(final String name, final ArrayList<String> colors,
                         final String description, final int mana, final int attackDamage,
                         final int health) {
        super(name, colors, description, mana, attackDamage, health);
    }
}
