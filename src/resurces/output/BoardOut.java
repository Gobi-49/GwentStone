package resurces.output;

import resurces.cards.MagicNumbers;

import java.util.ArrayList;

public class BoardOut {
    private ArrayList<ArrayList<MinionOut>> board = new ArrayList<>();
    public BoardOut() {
        for (int i = 0; i < MagicNumbers.NRROWS; i++) {
            board.add(new ArrayList<MinionOut>());
        }
    }

    /**
     * Inserts MinionOut cards for output
     * @param card MinionOut card
     * @param row where to insert card
     */
    public final void insertOutCart(final MinionOut card, final int row) {
        board.get(row).add(card);
    }

    public final ArrayList<ArrayList<MinionOut>> getBoard() {
        return board;
    }
}
