package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class HeartHoundCard extends EnvironmentCard{

    public HeartHoundCard(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void effect(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        MinionClass aux = row.get(0);
        int nColum = -1, j=-1;
        for(MinionClass i : row) {
            j++;
            if(aux.getHealth() < i.getHealth()) {
                aux = i;
                nColum = j;
            }
        }
        board.removeCard(nRow,nColum);
        switch (nRow) {
            case 0 -> board.addCard(3, aux);
            case 1 -> board.addCard(2, aux);
            case 2 -> board.addCard(1, aux);
            case 3 -> board.addCard(0, aux);
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
        if(player.isPlayerNr()) {
            if(row == 3 && board.getRow(0).size() == 5) {
                return "Cannot steal enemy card since the player's row is full.";
            }
            if(row == 2 && board.getRow(1).size() == 5) {
                return "Cannot steal enemy card since the player's row is full.";
            }
        }
        else {
            if(row == 1 && board.getRow(2).size() == 5) {
                return "Cannot steal enemy card since the player's row is full.";
            }
            if(row == 0 && board.getRow(3).size() == 5) {
                return "Cannot steal enemy card since the player's row is full.";
            }
        }
        player.useMana(getMana());
        effect(board,row);
        return null;
    }
}
