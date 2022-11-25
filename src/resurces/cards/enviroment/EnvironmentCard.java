package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.Card;
import resurces.output.EnvironmentOut;

import java.util.ArrayList;

public class EnvironmentCard extends Card {

    public EnvironmentCard(final int mana, final String description,
                           final ArrayList<String> colors, final String name) {
        super(mana, description, colors, name);
    }

    /**
     * Designed for extension
     * @param board is the table
     * @param player is the player that uses the card
     * @param row is the affected row
     * @return a string that contains the error
     */
    public String useCard(final Board board, final Player player, final int row) {
        return null;
    }

    /**
     * Used to return an EnvironmentOut object, used to print to the json file
     * @return EnvironmentOut object
     */
    public final EnvironmentOut convertToOut() {
        return new EnvironmentOut(getMana(), getDescription(), getColors(), getName());
    }
}
