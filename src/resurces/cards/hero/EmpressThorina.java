package resurces.cards.hero;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class EmpressThorina extends Hero{

    public EmpressThorina(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }
    public void lowBlow(Board board, int nRow) {
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
    public void useAbility(Board board, int row, int currentMana) {
        if(getMana() > currentMana) {
            System.out.println("Not enough mana");
            return;

        } else {
            if(row >= 2) {
                System.out.println("Row not belong enemy");
                return;
            }
        }
        lowBlow(board,row);
        //Todo
    }
}
