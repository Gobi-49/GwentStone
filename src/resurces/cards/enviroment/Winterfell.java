package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard {

    public Winterfell(final int mana, final String description,
                      final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Applies the effect of the Winterfell card
     * @param board is the table
     * @param nRow is the affected row
     */
    public final void effect(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (MinionClass i : row) {
            i.setFrozen(true);
        }
    }

    @Override
    public final String useCard(final Board board, final Player player, final int row) {
        if (player.getMana() < getMana()) {
            return "Not enough mana to use environment card.";
        }
        if (player.isPlayerNr() && row < 2) {
            return "Chosen row does not belong to the enemy.";
        } else if (!player.isPlayerNr() && row >= 2) {
            return "Chosen row does not belong to the enemy.";
        }
        player.useMana(getMana());
        effect(board, row);
        return null;
    }
}
