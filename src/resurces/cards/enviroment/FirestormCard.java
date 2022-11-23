package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.Card;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class FirestormCard extends EnvironmentCard{

    public FirestormCard(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }
    public void effect(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for(int i=0; i<row.size(); i++) {
            row.get(i).setHealth( row.get(i).getHealth() - 1);
            if(row.get(i).getHealth() == 0) {
                board.removeCard(nRow, i);
                i--;
            }
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
