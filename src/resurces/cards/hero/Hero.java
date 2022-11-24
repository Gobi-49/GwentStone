package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.Card;
import resurces.output.HeroOut;
import resurces.output.MinionOut;

import java.util.ArrayList;

public class Hero extends Card {
    private int health;
    private boolean player; // false->P1; true->P2
    private boolean played = false;
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public void setPlayed(boolean played) {
        this.played = played;
    }
    public boolean isPlayed() {
        return played;
    }
    public Hero(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
        setHealth(30);
    }
    public String useAbility(Board board, int row, Player player) {
        return null;
    }
    public void setPlayer(boolean player) {
        this.player = player;
    }
    public boolean isPlayer() {
        return player;
    }
    public HeroOut convertToOut() {
        return new HeroOut(getMana(),getDescription(),getColors(),getName(),getHealth());
    }
}
