package resurces.cards.hero;

import resurces.Board;
import resurces.cards.Card;

import java.util.ArrayList;

public class Hero extends Card {
    private int health;
    private boolean player; // false->P1; true->P2
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public Hero(int mana, String description, ArrayList<String> colors, String name, boolean player) {
        super(mana, description, colors, name);
        setHealth(30);
        this.player = player;
    }
    public void setPlayer(boolean player) {
        this.player = player;
    }
    public boolean isPlayer() {
        return player;
    }

    public void useAbility(Board board, int row, int currentMana) {}

}
