package resurces;

import resurces.cards.MagicNumbers;
import resurces.cards.minions.MinionClass;
import resurces.output.BoardOut;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<MinionClass>> board = new ArrayList<>();
    public Board() {
        for (int i = 0; i < MagicNumbers.NRROWS; i++) {
            board.add(new ArrayList<>(MagicNumbers.NRCOLUMNS));
        }
    }

    /**
     * Adds a card on the board
     * @param x row
     * @param card card to add to board
     */
    public final void addCard(final int x, final MinionClass card) {
        board.get(x).add(card);
    }

    /**
     * Removes cards from the deck
     * @param x row
     * @param y column
     */
    public final void removeCard(final int x, final int y) {
        board.get(x).remove(y);
    }

    /**
     * returns the x row
     * @param x row number
     * @return Array of cards
     */
    public final ArrayList<MinionClass> getRow(final int x) {
        return board.get(x);
    }
    public final ArrayList<ArrayList<MinionClass>> getBoard() {
        return board;
    }

    /**
     * Used to create a BoardOut object used to print to the json file
     * @return BoardOut object
     */
    public final BoardOut convertOut() {
        BoardOut out = new BoardOut();
        for (int i = 0; i < MagicNumbers.NRROWS; i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                out.insertOutCart(board.get(i).get(j).convertToOut(), i);
            }
        }
        return out;
    }

    /**
     * Used to remove all the cards from the board after a game
     */
    public final void resetBoard() {
        for (ArrayList<MinionClass> i : board) {
            while (i.size() > 0) {
                i.remove(0);
            }
        }
    }
}
