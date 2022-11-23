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

    public MinionOut(int mana, int attackDamage, int health, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
        this.attackDamage = attackDamage;
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
    public int getHealth() {
        return health;
    }
}
