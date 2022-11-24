package resurces.cards.hero;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class KingMudface extends Hero {

    public KingMudface(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void earthBorn(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (MinionClass i : row) {
            i.setHealth(i.getHealth() + 1);
        }
    }

    @Override
    public String useAbility(Board board, int row, Player player) {
        if(getMana() > player.getMana()) {
            return "Not enough mana to use hero's ability.";
        }
        if(isPlayed()) {
            return "Hero has already attacked this turn.";
        }
        if(row >= 2 && player.isPlayerNr()) {
            return "Selected row does not belong to the current player.";
        }
        if(row < 2 && !player.isPlayerNr()) {
            return "Selected row does not belong to the current player.";
        }
        player.useMana(getMana());
        earthBorn(board,row);
        setPlayed(true);
        return null;
    }
}
