package resurces.cards.hero;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class LordRoyce extends Hero{

    public LordRoyce(int mana, String description, ArrayList<String> colors, String name, boolean player) {
        super(mana, description, colors, name, player);
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
    public void useAbility(Board board, int row, int currentMana) {
        if(getMana() > currentMana) {
            System.out.println("Not enough mana");
            return;
        }
        if(isPlayer()) {
            if(row < 2) {
                System.out.println("Row not belong enemy");
                return;
            }
        } else {
            if(row >= 2) {
                System.out.println("Row not belong enemy");
                return;
            }
        }
        subZero(board,row);
    }
}
