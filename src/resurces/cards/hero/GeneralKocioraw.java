package resurces.cards.hero;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero{

    public GeneralKocioraw(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void subZero(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (MinionClass i : row) {
            i.setAttackDamage(i.getAttackDamage() + 1);
        }
    }
}
