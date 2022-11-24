package resurces.cards.minions.special;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class CursedCard extends MinionClass {

    public CursedCard(String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(name, colors, description, mana, attackDamage, health);
    }
    public void shapeshift(MinionClass card) {
        int x = card.getHealth();
        card.setHealth(card.getAttackDamage());
        card.setAttackDamage(x);
    }

    @Override
    public String useAbility(Board board, int xAttacker, int yAttacker, int xAttacked, int yAttacked) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if(xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2) {
            return "Attacked card does not belong to the enemy.";
        }
        if(isPlayed()) {
            return "Attacker card has already attacked this turn.";
        }
        if(isFrozen()) {
            return "Attacker card is frozen.";
        }
        ArrayList<MinionClass> frontRow;
        if(!board.getRow(xAttacked).get(yAttacked).isTank()) {
            if(player && !board.getRow(xAttacked).get(yAttacked).isTank()) {
                frontRow = board.getRow(2);
            } else {
                frontRow = board.getRow(1);
            }
            for(MinionClass i :frontRow) {
                if(i.isTank()) {
                    return "Attacked card is not of type 'Tank'.";
                }
            }
        }
        MinionClass attacked = board.getRow(xAttacked).get(yAttacked);
        shapeshift(attacked);
        if (attacked.getHealth() == 0) {
            board.removeCard(xAttacked,yAttacked);
        }
        setPlayed(true);
        return null;
    }
}
