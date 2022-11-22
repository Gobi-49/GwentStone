package resurces.cards.enviroment;

import resurces.Board;
import resurces.cards.Card;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class FirestormCard extends EnvironmentCard{

    public FirestormCard(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }
    public void effect(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for(MinionClass i : row) {
            i.setHealth(i.getHealth() - 1);
        }
    }

    @Override
    public void useCard(Board board, int currentMana, int row, boolean player) {
        if(currentMana < getMana()) {
            System.out.println("not enough mana");
            return;
        }
        if(player && row < 2) {
            System.out.println("Does not belong to enemy");
            return;
        } else if(!player && row >= 2) {
            System.out.println("Does not belong to enemy");
            return;
        }
        effect(board,row);
    }
}
