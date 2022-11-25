package resurces.cards.minions.special;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class DiscipleCard extends MinionClass {

    public DiscipleCard(final String name, final ArrayList<String> colors, final String description,
                        final int mana, final int attackDamage, final int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    /**
     * Represents the special ability of the Disciple
     * @param card the target
     */
    public final void godsPlan(final MinionClass card) {
        card.setHealth(card.getHealth() + 2);
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
        if (!(xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2)) {
            return "Attacked card does not belong to the current player.";
        }
        if (isPlayed()) {
            return "Attacker card has already attacked this turn.";
        }
        if (isFrozen()) {
            System.out.println("Card frozen");
            return "Attacker card is frozen.";
        }
        MinionClass attacked = board.getRow(xAttacked).get(yAttacked);
        godsPlan(attacked);
        setPlayed(true);
        return null;
    }
}
