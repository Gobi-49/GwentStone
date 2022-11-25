package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class EmpressThorina extends Hero {

    public EmpressThorina(final int mana, final String description,
                          final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Ability of Empress Thorina
     * @param board is the table
     * @param nRow is the affected row
     */
    public void lowBlow(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        MinionClass aux = row.get(0);
        int nColum = 0, j = -1;
        for (MinionClass i : row) {
            j++;
            if (aux.getHealth() < i.getHealth()) {
                aux = i;
                nColum = j;
            }
        }
        board.removeCard(nRow, nColum);
    }

    @Override
    public final String useAbility(final Board board, final int row, final Player player) {
        if (getMana() > player.getMana()) {
            return "Not enough mana to use hero's ability.";
        }
        if (isPlayed()) {
            return "Hero has already attacked this turn.";
        }
        if (row >= 2 && !player.isPlayerNr()) {
            return "Selected row does not belong to the enemy.";
        }
        if (row < 2 && player.isPlayerNr()) {
            return "Selected row does not belong to the enemy.";
        }
        player.useMana(getMana());
        lowBlow(board, row);
        setPlayed(true);
        return null;
    }
}
