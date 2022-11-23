package resurces;

import resurces.cards.minions.MinionClass;
import resurces.output.MinionOut;

import java.util.ArrayList;

public class BoardOut {
    private ArrayList<ArrayList<MinionOut>> board = new ArrayList<>(4);
    public BoardOut() {
        for(int i=0; i<4; i++) {
            board.add(new ArrayList<MinionOut>());
        }
    }
    public void insertOutCart(MinionOut card, int row) {
        board.get(row).add(card);
    }

    public ArrayList<ArrayList<MinionOut>> getBoard() {
        return board;
    }
}
