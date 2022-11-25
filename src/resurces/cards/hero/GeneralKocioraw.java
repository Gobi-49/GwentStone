package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero {

    public GeneralKocioraw(final int mana, final String description,
                           final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     *
     * @param board represents the reference to the table board
     * @param nRow represents the row on which the ability is used
     */
    public void bloodThirst(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (MinionClass i : row) {
            i.setAttackDamage(i.getAttackDamage() + 1);
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
        bloodThirst(board, row);
        setPlayed(true);
        return null;
    }
}
