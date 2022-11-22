package resurces.cards.enviroment;

import resurces.Board;
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
    public void useCard(Board board, int currentMana, int row, boolean player) {
        if(currentMana < getMana()) {
            System.out.println("not enought mana");
            return;
        }
        if(player && row < 2) {
            System.out.println("Does not belong to enemy");
            return;
        } else if(!player && row >= 2) {
            System.out.println("Does not belong to enemy");
            return;
        }
        if(player) {
            if(row == 3 && board.getRow(0).size() == 5) {
                System.out.println("Row full");
                return;
            }
            if(row == 2 && board.getRow(1).size() == 5) {
                System.out.println("Row full");
                return;
            }
        }
        else {
            if(row == 1 && board.getRow(2).size() == 5) {
                System.out.println("Row full");
                return;
            }
            if(row == 0 && board.getRow(3).size() == 5) {
                System.out.println("Row full");
                return;
            }
        }
        effect(board,row);
    }
}
