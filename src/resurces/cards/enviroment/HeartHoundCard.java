package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.MagicNumbers;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class HeartHoundCard extends EnvironmentCard {

    public HeartHoundCard(final int mana, final String description,
                          final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Appliez the effect of the HeartHoundCard card
     * @param board is the table
     * @param nRow is the affected row
     */
    public final void effect(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        MinionClass aux = row.get(0);
        int nColum = -1, j = -1;
        for (MinionClass i : row) {
            j++;
            if (aux.getHealth() < i.getHealth()) {
                aux = i;
                nColum = j;
            }
        }
        board.removeCard(nRow, nColum);
        switch (nRow) {
            case MagicNumbers.ROW0 -> board.addCard(MagicNumbers.ROW3, aux);
            case MagicNumbers.ROW1 -> board.addCard(MagicNumbers.ROW2, aux);
            case MagicNumbers.ROW2 -> board.addCard(MagicNumbers.ROW1, aux);
            case MagicNumbers.ROW3 -> board.addCard(MagicNumbers.ROW0, aux);
            default -> { }
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
        if (player.isPlayerNr()) {
            if (row == MagicNumbers.ROW3
                    && board.getRow(MagicNumbers.ROW0).size() == MagicNumbers.NRCOLUMNS) {
                return "Cannot steal enemy card since the player's row is full.";
            }
            if (row == MagicNumbers.ROW2
                    && board.getRow(MagicNumbers.ROW1).size() == MagicNumbers.NRCOLUMNS) {
                return "Cannot steal enemy card since the player's row is full.";
            }
        } else {
            if (row == MagicNumbers.ROW1
                    && board.getRow(MagicNumbers.ROW2).size() == MagicNumbers.NRCOLUMNS) {
                return "Cannot steal enemy card since the player's row is full.";
            }
            if (row == MagicNumbers.ROW0
                    && board.getRow(MagicNumbers.ROW3).size() == MagicNumbers.NRCOLUMNS) {
                return "Cannot steal enemy card since the player's row is full.";
            }
        }
        player.useMana(getMana());
        effect(board, row);
        return null;
    }
}
