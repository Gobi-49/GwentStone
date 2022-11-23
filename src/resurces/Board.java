package resurces;

import resurces.cards.Card;
import resurces.cards.enviroment.EnvironmentCard;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<MinionClass>> board = new ArrayList<>(4);
    public Board() {
        for(int i = 0; i < 4; i++) {
            board.add(new ArrayList<>(5));
        }
    }
    public void addCard(int x, MinionClass card) {
        board.get(x).add(card);
    }
    public void removeCard(int x, int y) {
        board.get(x).remove(y);
    }
    public ArrayList<MinionClass> getRow(int x) {
        return board.get(x);
    }
    public ArrayList<ArrayList<MinionClass>> getBoard() {
        return board;
    }
    public BoardOut convertOut() {
        BoardOut out = new BoardOut();
        for(int i=0; i<4; i++) {
            for(int j=0; j<board.get(i).size(); j++) {
                out.insertOutCart(board.get(i).get(j).convertToOut(),i);
            }
        }
        return out;
    }
}
