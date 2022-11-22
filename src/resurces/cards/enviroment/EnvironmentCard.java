package resurces.cards.enviroment;

import resurces.Board;
import resurces.cards.Card;

import java.util.ArrayList;

public class EnvironmentCard extends Card {

    public EnvironmentCard(int mana, String description, ArrayList<String> colors, String name) {
        super(mana, description, colors, name);
    }
    public void useCard(Board board, int currentMana, int row, boolean player) {}
}
