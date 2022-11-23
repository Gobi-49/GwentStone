package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard{

    public Winterfell(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void effect(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for(MinionClass i : row) {
            i.setFrozen(true);
        }
    }

    @Override
    public String useCard(Board board, Player player, int row) {
        if(player.getMana() < getMana()) {
            return "Not enough mana to use environment card.";
        }
        if(player.isPlayerNr() && row < 2) {
            return "Chosen row does not belong to the enemy.";
        } else if(!player.isPlayerNr() && row >= 2) {
            return "Chosen row does not belong to the enemy.";
        }
        player.useMana(getMana());
        effect(board,row);
        return null;
    }
}
