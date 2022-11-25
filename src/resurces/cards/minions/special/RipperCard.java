package resurces.cards.minions.special;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class RipperCard extends MinionClass {

    public RipperCard(final String name, final ArrayList<String> colors, final String description,
                      final int mana, final int attackDamage, final int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    /**
     * The special ability of The Ripper
     * @param card target
     */
    public final void weakKnees(final MinionClass card) {
        card.setAttackDamage(card.getAttackDamage() - 2);
        if (card.getAttackDamage() < 0) {
            card.setAttackDamage(0);
        }
    }

    /**
     * Implements how the ability is used
     * @param board is the table
     * @param xAttacker x position of the attacker
     * @param yAttacker y position of the attacker
     * @param xAttacked x position of the attacked
     * @param yAttacked y position of the attacked
     * @return error string
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
        weakKnees(attacked);
        setPlayed(true);
        return null;
    }
}

