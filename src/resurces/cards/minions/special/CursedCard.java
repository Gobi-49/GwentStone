package resurces.cards.minions.special;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class CursedCard extends MinionClass {

    public CursedCard(final String name, final ArrayList<String> colors, final String description,
                      final int mana, final int attackDamage, final int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    /**
     * Implements the shapeshift ability of The Cursed One
     * @param card is the target
     */
    public final void shapeshift(final MinionClass card) {
        int x = card.getHealth();
        card.setHealth(card.getAttackDamage());
        card.setAttackDamage(x);
    }

    @Override
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
        shapeshift(attacked);
        if (attacked.getHealth() == 0) {
            board.removeCard(xAttacked, yAttacked);
        }
        setPlayed(true);
        return null;
    }
}
