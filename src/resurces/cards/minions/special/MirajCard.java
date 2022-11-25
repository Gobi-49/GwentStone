package resurces.cards.minions.special;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class MirajCard extends MinionClass {

    public MirajCard(final String name, final ArrayList<String> colors, final String description,
                     final int mana, final int attackDamage, final int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    /**
     * Represents the implementation of the Miraj special ability
     * @param card is the attacked card
     */
    public final void skyjack(final MinionClass card) {
        int x = getHealth();
        setHealth(card.getHealth());
        card.setHealth(x);
    }
    /**
     * Implements how the card uses the ability
     * @param board is the table
     * @param xAttacker x position of the attacker
     * @param yAttacker y position of the attacker
     * @param xAttacked x position of the attacked
     * @param yAttacked y position of the attacked
     * @return
     */
    public final String useAbility(final Board board, final int xAttacker, final int yAttacker,
                             final int xAttacked, final int yAttacked) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if (xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2) {
            return "Attacked card does not belong to the enemy.";
        }
        if (isPlayed()) {
            return "Attacker card has already attacked this turn.";
        }
        if (isFrozen()) {
            return "Attacker card is frozen.";
        }
        ArrayList<MinionClass> frontRow;
        if (!board.getRow(xAttacked).get(yAttacked).isTank()) {
            if (player && !board.getRow(xAttacked).get(yAttacked).isTank()) {
                frontRow = board.getRow(2);
            } else {
                frontRow = board.getRow(1);
            }
            for (MinionClass i :frontRow) {
                if (i.isTank()) {
                    return "Attacked card is not of type 'Tank'.";
                }
            }
        }
        MinionClass attacked = board.getRow(xAttacked).get(yAttacked);
        skyjack(attacked);
        setPlayed(true);
        return null;
    }

}
