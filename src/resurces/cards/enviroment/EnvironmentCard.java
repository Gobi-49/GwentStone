package resurces.cards.enviroment;

import resurces.Board;
import resurces.Player;
import resurces.cards.Card;
import resurces.output.EnvironmentOut;

import java.util.ArrayList;

public class EnvironmentCard extends Card {

    public EnvironmentCard(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }
    public String useCard(Board board, Player player, int row) {
        return null;
    }
    public EnvironmentOut convertToOut() {
        return new EnvironmentOut(getMana(),getDescription(),getColors(),getName());
    }
}
