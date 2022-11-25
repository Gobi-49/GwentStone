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
    public MinionClass(final String name, final ArrayList<String> colors, final String description,
                        final int mana, final int attackDamage, final int health) {
        super(mana, description, colors, name);
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public final void setPlayed(final boolean played) {
        this.played = played;
    }
    public final void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }
    public final void setTank(final boolean tank) {
        this.tank = tank;
    }
    public final boolean isPlayed() {
        return played;
    }
    public final boolean isFrozen() {
        return frozen;
    }
    public final boolean isTank() {
        return tank;
    }
    public final void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }
    public final int getAttackDamage() {
        return attackDamage;
    }
    public final void setHealth(final int health) {
        this.health = health;
    }
    public final int getHealth() {
        return health;
    }

    /**
     * Implemets the attack of the card on another card
     * @param board is the table
     * @param xAttacker x position of the attacker
     * @param yAttacker y position of the attacker
     * @param xAttacked x position of the attacked
     * @param yAttacked y position of the attacked
     * @return string of the error
     */
    public final String attackCard(final Board board, final int xAttacker, final int yAttacker,
                                   final int xAttacked, final int yAttacked) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if (xAttacker < 2 && xAttacked < 2 || xAttacker >= 2 && xAttacked >= 2) {
            return "Attacked card does not belong to the enemy.";
        }
        if (played) {
            return "Attacker card has already attacked this turn.";
        }
        if (frozen) {
            return "Attacker card is frozen.";
        }
        ArrayList<MinionClass> frontRow;
        if (!board.getRow(xAttacked).get(yAttacked).isTank()) {
            if (player && !board.getRow(xAttacked).get(yAttacked).isTank()) {
                frontRow = board.getRow(2);
            } else {
                frontRow = board.getRow(1);
            }
            for (MinionClass i :frontRow) {
                if (i.isTank()) {
                    return "Attacked card is not of type 'Tank'.";
                }
            }
        }
        MinionClass attacked = board.getRow(xAttacked).get(yAttacked);
        attacked.setHealth(attacked.getHealth() - attackDamage);

        if (attacked.getHealth() <= 0) {
            board.removeCard(xAttacked, yAttacked);
        }
        setPlayed(true);
        return null;
    }
    /**
     * Used to be subclassed for each special hero special ability
     * @param board is the table
     * @param xAttacker x position of the attacker
     * @param yAttacker y position of the attacker
     * @param xAttacked x position of the attacked
     * @param yAttacked y position of the attacked
     * @return string of the error
     */
    public String useAbility(final Board board, final int xAttacker, final int yAttacker,
                             final int xAttacked, final int yAttacked) {
        return null;
    }

    /**
     * Implements the attack on the enemy hero
     * @param board is the table
     * @param xAttacker x position of the attacker
     * @param yAttacker y position of the attacker
     * @param hero the hero that is attacked
     * @return string of the error
     */
    public final String attackHero(final Board board, final int xAttacker,
                                   final int yAttacker, final Hero hero) {
        boolean player = (xAttacker < 2); // true -> playerTwo ; false -> playerOne
        if (played) {
            return "Attacker card has already attacked this turn.";
        }
        if (frozen) {
            return "Attacker card is frozen.";
        }
        ArrayList<MinionClass> frontRow;
        if (player) {
            frontRow = board.getRow(2);
        } else {
            frontRow = board.getRow(1);
        }
        for (MinionClass i :frontRow) {
            if (i.isTank()) {
                return "Attacked card is not of type 'Tank'.";
            }
        }
        MinionClass attacker = board.getRow(xAttacker).get(yAttacker);
        hero.setHealth(hero.getHealth() - attacker.getAttackDamage());
        if (hero.getHealth() <= 0) {
            if (player) {
                return "Player two killed the enemy hero.";
            } else {
                return "Player one killed the enemy hero.";
            }
        }
        setPlayed(true);
        return null;
    }

    /**
     * used to return a MinionOut class that is used for printing in the json file
     * @return MinionOut class
     */
    public final MinionOut convertToOut() {
        return new MinionOut(getMana(), attackDamage, health, getDescription(),
                getColors(), getName());
    }
}
