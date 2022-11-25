package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class LordRoyce extends Hero {

    public LordRoyce(final int mana, final String description,
                     final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Ability of Lord Royce
     * @param board is the table
     * @param nRow is the affected row
     */
    public void subZero(final Board board, final int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        if (row.size() < nRow) {
            return;
        }
        MinionClass aux = row.get(0);
        for (MinionClass i : row) {
            if (aux.getAttackDamage() < i.getAttackDamage()) {
                aux = i;
            }
        }
        aux.setFrozen(true);
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
        subZero(board, row);
        setPlayed(true);
        return null;
    }
}
