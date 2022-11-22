package resurces.cards.hero;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class KingMudface extends Hero{

    public KingMudface(int mana, String description, ArrayList<String> colors, String name, boolean player) {
        super(mana, description, colors, name, player);
    }

    public void earthBorn(Board board, int nRow) {
        ArrayList<MinionClass> row = board.getRow(nRow);
        for (MinionClass i : row) {
            i.setHealth(i.getHealth() + 1);
        }
    }
    public void useAbility(Board board, int row, int currentMana) {
        if(getMana() > currentMana) {
            System.out.println("Not enough mana");
            return;
        }
        if(isPlayer()) {
            if(row >= 2) {
                System.out.println("Row not belong to player");
                return;
            }
        } else {
            if(row < 2) {
                System.out.println("Row not belong to player");
                return;
            }
        }
        earthBorn(board,row);
    }
}
