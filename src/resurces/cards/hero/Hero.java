package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.Card;
import resurces.cards.MagicNumbers;
import resurces.output.HeroOut;

import java.util.ArrayList;

public class Hero extends Card {
    private int health;
    private boolean player; // false->P1; true->P2
    private boolean played = false;
    public final void setHealth(final int health) {
        this.health = health;
    }
    public final int getHealth() {
        return health;
    }
    public final void setPlayed(final boolean played) {
        this.played = played;
    }
    public final boolean isPlayed() {
        return played;
    }
    public Hero(final int mana, final String description, final ArrayList<String> colors,
                final String name) {
        super(mana, description, colors, name);
        setHealth(MagicNumbers.MAXHEALTHHERO);
    }

    /**
     * @param board represents the table where cards are placed
     * @param row represents the row affected
     * @param player1 represents the attacker
     * used to be overridden
     */
    public String useAbility(final Board board, final int row, final Player player1) {
        return null;
    }
    public final void setPlayer(final boolean player) {
        this.player = player;
    }
    public final boolean isPlayer() {
        return player;
    }

    /**
     * @return returns a HeroOut class used to print in the json
     */
    public HeroOut convertToOut() {
        return new HeroOut(getMana(), getDescription(), getColors(), getName(), getHealth());
    }
}
