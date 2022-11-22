package game;

import com.fasterxml.jackson.databind.node.ObjectNode;
import resurces.Board;
import resurces.Player;
import resurces.cards.Card;
import resurces.cards.enviroment.EnvironmentCard;
import resurces.cards.minions.MinionClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class DebugCommands {
    private ObjectMapper objectMapper;
    private ArrayNode output;
    public DebugCommands(ObjectMapper objectMapper, ArrayNode output) {
        this.objectMapper = objectMapper;
        this. output = output;
    }
    public void getCardsInHand(Player player) {
        System.out.println(player.getHand().toString());
    }
    public void getPlayerDeck(Player player) {
        System.out.println(player.getDeck().toString());
    }
    public void getCardsOnTable(Board board) {
        System.out.println(board.toString());
    }
    public void getPlayerTurn(Player player1) {
        if(player1.isPlayerNr()) {
            System.out.println("1");
        }
        else
            System.out.println("2");
    }
    public void getPlayerHero(Player player) {
        System.out.println(player.getHero().toString());
    }
    public void getCardAtPosition(Board board, int x, int y) {
        if(y > board.getRow(x).size()) {
            System.out.println("No card at position");
            return;
        }
        System.out.println(board.getRow(x).get(y).toString());
    }
    public void getPlayerMana(Player player) {
        System.out.println(player.getMana());
    }
    public void getEnvironmentCards(Player player) {
        ArrayList<Card> hand = player.getHand();
        ArrayList<EnvironmentCard> environmentCards = new ArrayList<>();
        for(Card i : hand) {
            if (i instanceof EnvironmentCard) {
                environmentCards.add((EnvironmentCard) i);
            }
        }
        System.out.println(environmentCards.toString());
    }
    public void getFrozenCards(Board board) {
        ArrayList<MinionClass> frozenCards = new ArrayList<>();
        for(int i=0; i < 4; i++) {
            for(MinionClass j : board.getRow(i)) {
                if(j.isFrozen()) {
                    frozenCards.add(j);
                }
            }
        }
        System.out.println(frozenCards.toString());
    }
}
