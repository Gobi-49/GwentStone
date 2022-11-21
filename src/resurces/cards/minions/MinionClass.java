package resurces.cards.minions;

import resurces.cards.Card;

import java.util.ArrayList;

public abstract class MinionClass extends Card {
    private int health;
    private int attackDamage;
    private boolean played = false;
    private boolean frozen = false;
    public MinionClass (String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(mana, description, colors, name);
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
    public boolean isFrozen() {
        return frozen;
    }
    public boolean isPlayed() {
        return played;
    }
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

}
