package game;

import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import resurces.Board;
import resurces.BoardOut;
import resurces.Player;
import resurces.cards.Card;
import resurces.cards.enviroment.EnvironmentCard;
import resurces.cards.minions.MinionClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import resurces.output.EnvironmentOut;
import resurces.output.HeroOut;

import java.util.ArrayList;

public class DebugCommands {
    private ObjectMapper objectMapper;
    private ArrayNode output;
    public DebugCommands(ObjectMapper objectMapper, ArrayNode output) {
        this.objectMapper = objectMapper;
        this. output = output;
    }
    public void getCardsInHand(Player player) {
        ObjectNode getCardsInHand = objectMapper.createObjectNode();
        getCardsInHand.put("command", "getCardsInHand");
        if(player.isPlayerNr())
            getCardsInHand.put("playerIdx", 2);
        else
            getCardsInHand.put("playerIdx", 1);
        ArrayList<Card> cardsOut = new ArrayList<>();
        for(Card i : player.getHand()) {
            if (i instanceof MinionClass)
                cardsOut.add(((MinionClass) i).convertToOut());
            else
                cardsOut.add(((EnvironmentCard) i).convertToOut());
        }
        getCardsInHand.putPOJO("output", cardsOut);
        output.add(getCardsInHand) ;
    }
    public void getPlayerDeck(Player player) {
        ObjectNode getPlayerDeck = objectMapper.createObjectNode();
        getPlayerDeck.put("command", "getPlayerDeck");
        if(player.isPlayerNr())
            getPlayerDeck.put("playerIdx", 2);
        else
            getPlayerDeck.put("playerIdx", 1);
        ArrayList<Card> cardsOut = new ArrayList<>();
        for(Card i : player.getDeck().getCards()) {
            if (i instanceof MinionClass)
                cardsOut.add(((MinionClass) i).convertToOut());
            else
                cardsOut.add(((EnvironmentCard) i).convertToOut());
        }
        getPlayerDeck.putPOJO("output", cardsOut);
        output.add(getPlayerDeck) ;
    }
    public void getCardsOnTable(Board board) {
        ObjectNode getCardsOnTable = objectMapper.createObjectNode();
        getCardsOnTable.put("command", "getCardsOnTable");
        ArrayList<ArrayList<Card>> table = new ArrayList<>();
        BoardOut boardOut = board.convertOut();
        getCardsOnTable.putPOJO("output", boardOut.getBoard());
        output.add(getCardsOnTable) ;
    }
    public int getPlayerTurn(Player player1) {
        if(player1.isTurn()) {
            return 1;
        }
        else
            return 2;
    }
    public void getPlayerHero(Player player) {
        ObjectNode getPlayerHero = objectMapper.createObjectNode();
        getPlayerHero.put("command", "getPlayerHero");
        if(player.isPlayerNr())
            getPlayerHero.put("playerIdx", 2);
        else
            getPlayerHero.put("playerIdx", 1);
        HeroOut heroOut = player.getHero().convertToOut();
        getPlayerHero.putPOJO("output", heroOut);
        output.add(getPlayerHero) ;
    }
    public void getCardAtPosition(Board board, int x, int y) {
        ObjectNode getCardAtPosition = objectMapper.createObjectNode();
        getCardAtPosition.put("command", "getCardAtPosition");
        getCardAtPosition.put("x", x);
        getCardAtPosition.put("y", y);
        if(y > board.getRow(x).size()) {
            getCardAtPosition.put("output", "No card available at that position.");
            return;
        } else {
            getCardAtPosition.putPOJO("output", board.getRow(x).get(y).convertToOut());
        }
        output.add(getCardAtPosition) ;
    }
    public void getPlayerMana(Player player) {
        ObjectNode getPlayerMana = objectMapper.createObjectNode();
        getPlayerMana.put("command", "getPlayerMana");
        if(player.isPlayerNr())
            getPlayerMana.put("playerIdx", 2);
        else
            getPlayerMana.put("playerIdx", 1);
        getPlayerMana.put("output", player.getMana());
        output.add(getPlayerMana) ;
    }
    public void getEnvironmentCards(Player player) {
        ObjectNode getEnvironmentCardsInHand = objectMapper.createObjectNode();
        getEnvironmentCardsInHand.put("command", "getEnvironmentCardsInHand");
        if(player.isPlayerNr())
            getEnvironmentCardsInHand.put("playerIdx", 2);
        else
            getEnvironmentCardsInHand.put("playerIdx", 1);
        ArrayList<Card> hand = player.getHand();
        ArrayList<EnvironmentOut> environmentCards = new ArrayList<>();
        for(Card i : hand) {
            if (i instanceof EnvironmentCard) {
                environmentCards.add(((EnvironmentCard) i).convertToOut());
            }
        }
        getEnvironmentCardsInHand.putPOJO("output", environmentCards);
        output.add(getEnvironmentCardsInHand) ;
    }
    public void getFrozenCards(Board board) {
        ObjectNode getFrozenCardsOnTable = objectMapper.createObjectNode();
        getFrozenCardsOnTable.put("command", "getFrozenCardsOnTable");
        ArrayList<MinionClass> frozenCards = new ArrayList<>();
        for(int i=0; i < 4; i++) {
            for(MinionClass j : board.getRow(i)) {
                if(j.isFrozen()) {
                    frozenCards.add(j);
                }
            }
        }
        getFrozenCardsOnTable.putPOJO("output", frozenCards);
        output.add(getFrozenCardsOnTable) ;
    }
}
