package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.MagicNumbers;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class FirestormCard extends EnvironmentCard {

    public FirestormCard(final int mana, final String description,
                         final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Applies the effects of the Firestorm card
     * @param board is the table
     * @param nRow is the affected row
     */
    public final void effect(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (int i = 0; i < row.size(); i++) {
            row.get(i).setHealth(row.get(i).getHealth() - 1);
            if (row.get(i).getHealth() == 0) {
                board.removeCard(nRow, i);
                i--;
            }
        }
    }

    @Override
    public final String useCard(final Board board, final Player player, final int row) {
        if (player.getMana() < getMana()) {
            return "Not enough mana to use environment card.";
        }
        if (player.isPlayerNr() && row < MagicNumbers.ROW2) {
            return "Chosen row does not belong to the enemy.";
        } else if (!player.isPlayerNr() && row >= MagicNumbers.ROW2) {
            return "Chosen row does not belong to the enemy.";
        }
        player.useMana(getMana());
        effect(board, row);
        return null;
    }
}
