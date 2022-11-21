package resurces.cards.enviroment;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class HeartHoundCard extends EnvironmentCard{

    public HeartHoundCard(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void use(Board board, int nRow) {
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
        if(nRow < 2) {
            board.addCard(nRow + 2, aux);
        }
        else {
            board.addCard(nRow - 2, aux);
        }

    }
}
