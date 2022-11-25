package resurces.output;

import resurces.cards.Card;

import java.util.ArrayList;

public class MinionOut extends Card {
    private int mana;
    private int attackDamage;
    private int health;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public MinionOut(final int mana, final int attackDamage, final int health,
                     final String description, final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
        this.attackDamage = attackDamage;
        this.health = health;
    }

    public final int getAttackDamage() {
        return attackDamage;
    }
    public final int getHealth() {
        return health;
    }
}
