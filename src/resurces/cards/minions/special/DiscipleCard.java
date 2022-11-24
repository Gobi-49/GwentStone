package resurces.cards.minions.special;

import resurces.Board;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class DiscipleCard extends MinionClass {

    public DiscipleCard(String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(name, colors, description, mana, attackDamage, health);
    }

    public void godsPlan(MinionClass card) {
        card.setHealth(card.getHealth() + 2);
    }
    public String useAbility(Board board, int xAttacker, int yAttacker, int xAttacked, int yAttacked) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if(!(xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2)) {
            return "Attacked card does not belong to the current player.";
        }
        if(isPlayed()) {
            return "Attacker card has already attacked this turn.";
        }
        if(isFrozen()) {
            System.out.println("Card frozen");
            return "Attacker card is frozen.";
        }
        MinionClass attacked = board.getRow(xAttacked).get(yAttacked);
        godsPlan(attacked);
        setPlayed(true);
        return null;
    }
}
