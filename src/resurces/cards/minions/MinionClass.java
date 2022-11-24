package resurces.cards.minions;

import resurces.Board;
import resurces.cards.Card;
import resurces.cards.hero.Hero;
import resurces.output.MinionOut;

import java.util.ArrayList;

public abstract class MinionClass extends Card {
    private int health;
    private int attackDamage;
    private boolean played = false;
    private boolean frozen = false;
    private boolean tank = false;
    public MinionClass (String name, ArrayList<String> colors, String description, int mana, int attackDamage, int health) {
        super(mana, description, colors, name);
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
    public void setTank(boolean tank) {
        this.tank = tank;
    }
    public boolean isPlayed() {
        return played;
    }
    public boolean isFrozen() {
        return frozen;
    }
    public boolean isTank() {
        return tank;
    }
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }

    public String  attackCard(Board board, int xAttacker, int yAttacker, int xAttacked, int yAttacked) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if(xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2) {
            return "Attacked card does not belong to the enemy.";
        }
        if(played) {
            return "Attacker card has already attacked this turn.";
        }
        if(frozen) {
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
        attacked.setHealth(attacked.getHealth() - attackDamage);

        if(attacked.getHealth() <= 0) {
            board.removeCard(xAttacked,yAttacked);
        }
        setPlayed(true);
        return null;
    }
    public String useAbility(Board board, int xAttacker, int yAttacker, int xAttacked, int yAttacked) {
        return null;
    }
    public String attackHero(Board board, int xAttacker, int yAttacker, Hero hero) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if(played) {
            return "Attacker card has already attacked this turn.";
        }
        if(frozen) {
            return "Attacker card is frozen.";
        }
        ArrayList<MinionClass> frontRow;
        if(player) {
            frontRow = board.getRow(2);
        } else {
            frontRow = board.getRow(1);
        }
        for(MinionClass i :frontRow) {
            if(i.isTank()) {
                return "Attacked card is not of type 'Tank'.";
            }
        }
        MinionClass attacker = board.getRow(xAttacker).get(yAttacker);
        hero.setHealth(hero.getHealth() - attacker.getAttackDamage());
        if (hero.getHealth() <= 0) {
            if(player)
                return "Player two killed the enemy hero.";
            else
                return "Player one killed the enemy hero.";
        }
        setPlayed(true);
        return null;
    }
    public MinionOut convertToOut() {
        return new MinionOut(getMana(),attackDamage,health,getDescription(),getColors(),getName());
    }
}
