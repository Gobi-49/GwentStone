package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class KingMudface extends Hero {

    public KingMudface(final int mana, final String description,
                       final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Ability of King Mudface
     * @param board is the table
     * @param nRow is the affected row
     */
    public void earthBorn(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (MinionClass i : row) {
            i.setHealth(i.getHealth() + 1);
        }
    }

    @Override
    public final String useAbility(final Board board, final int row, final Player player) {
        if (getMana() > player.getMana()) {
            return "Not enough mana to use hero's ability.";
        }
        if (isPlayed()) {
            return "Hero has already attacked this turn.";
        }
        if (row >= 2 && player.isPlayerNr()) {
            return "Selected row does not belong to the current player.";
        }
        if (row < 2 && !player.isPlayerNr()) {
            return "Selected row does not belong to the current player.";
        }
        player.useMana(getMana());
        earthBorn(board, row);
        setPlayed(true);
        return null;
    }
}
