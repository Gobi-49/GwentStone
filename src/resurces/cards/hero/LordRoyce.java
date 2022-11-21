package resurces.cards.hero;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class LordRoyce extends Hero{

    public LordRoyce(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }

    public void subZero(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        MinionClass aux = row.get(0);
        for (MinionClass i : row) {
            if (aux.getAttackDamage() < i.getAttackDamage()) {
                aux = i;
            }
        }
        aux.setFrozen(true);
    }
}