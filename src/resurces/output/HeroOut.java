package resurces.output;

import resurces.Board;
import resurces.cards.Card;

import java.util.ArrayList;

public class HeroOut extends Card {
    private int health;
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public HeroOut(int mana, String description, ArrayList<String> colors, String name, int health) {
        super(mana, description, colors, name);
        this.health = health;
    }
    public void useAbility(Board board, int row, int currentMana) {}
}
