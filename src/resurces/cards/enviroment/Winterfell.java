package resurces.cards.enviroment;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class Winterfell extends EnvironmentCard{

    public Winterfell(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void use(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for(MinionClass i : row) {
            i.setFrozen(true);
        }
    }
}
