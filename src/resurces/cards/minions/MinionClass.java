package resurces.cards.minions;

import resurces.Board;
import resurces.cards.Card;
import resurces.cards.hero.Hero;

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
    public boolean isFrozen() {
        return frozen;
    }
    public boolean isPlayed() {
        return played;
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
    public void setTank(boolean tank) {
        this.tank = tank;
    }
    public boolean isTank() {
        return tank;
    }

    public void attackCard(Board board, int xAttacker, int yAttacker, int xAttacked, int yAttacked) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if(xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2) {
            System.out.println("Does not belong to enemy");
            return;
        }
        if(played) {
            System.out.println("Card attacked");
            return;
        }
        if(frozen) {
            System.out.println("Card frozen");
            return;
        }
        ArrayList<MinionClass> frontRow;
        if(player) {
            frontRow = board.getRow(2);
        } else {
            frontRow = board.getRow(1);
        }
        for(MinionClass i :frontRow) {
            if(i.isTank()) {
                System.out.println("Card not Tank");
                return;
            }
        }
        MinionClass attacker = board.getRow(xAttacker).get(yAttacker);
        MinionClass attacked = board.getRow(xAttacked).get(yAttacked);
        attacked.setHealth(getHealth() - attacker.attackDamage);
        if(attacked.getHealth() <= 0) {
            board.removeCard(xAttacked,yAttacked);
        }
    }
    public void useAbility(Board board, int xAttacker, int yAttacker, int xAttacked, int yAttacked) {}
    public void attackHero(Board board, int xAttacker, int yAttacker, Hero hero) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if(played) {
            System.out.println("Card attacked");
            return;
        }
        if(frozen) {
            System.out.println("Card frozen");
            return;
        }
        ArrayList<MinionClass> frontRow;
        if(player) {
            frontRow = board.getRow(2);
        } else {
            frontRow = board.getRow(1);
        }
        for(MinionClass i :frontRow) {
            if(i.isTank()) {
                System.out.println("Card not Tank");
                return;
            }
        }
        MinionClass attacker = board.getRow(xAttacker).get(yAttacker);
        hero.setHealth(hero.getHealth() - attacker.getAttackDamage());
        if (hero.getHealth() <= 0) {
            System.out.println("Player killed enemy hero");
        }
    }
}
