package game;

import com.fasterxml.jackson.databind.node.ObjectNode;
import resurces.Board;
import resurces.cards.MagicNumbers;
import resurces.output.BoardOut;
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
    public DebugCommands(final ObjectMapper objectMapper, final ArrayNode output) {
        this.objectMapper = objectMapper;
        this.output = output;
    }

    /**
     * Prints to json the players hand
     * @param player player
     */
    public final void getCardsInHand(final Player player) {
        ObjectNode getCardsInHand = objectMapper.createObjectNode();
        getCardsInHand.put("command", "getCardsInHand");
        if (player.isPlayerNr()) {
            getCardsInHand.put("playerIdx", 2);
        } else {
            getCardsInHand.put("playerIdx", 1);
        }
        ArrayList<Card> cardsOut = new ArrayList<>();
        for (Card i : player.getHand()) {
            if (i instanceof MinionClass) {
                cardsOut.add(((MinionClass) i).convertToOut());
            } else {
                cardsOut.add(((EnvironmentCard) i).convertToOut());
            }
        }
        getCardsInHand.putPOJO("output", cardsOut);
        output.add(getCardsInHand);
    }

    /**
     * prints to json the players deck
     * @param player player
     */
    public final void getPlayerDeck(final Player player) {
        ObjectNode getPlayerDeck = objectMapper.createObjectNode();
        getPlayerDeck.put("command", "getPlayerDeck");
        if (player.isPlayerNr()) {
            getPlayerDeck.put("playerIdx", 2);
        } else {
            getPlayerDeck.put("playerIdx", 1);
        }
        ArrayList<Card> cardsOut = new ArrayList<>();
        for (Card i : player.getDeck().getCards()) {
            if (i instanceof MinionClass) {
                cardsOut.add(((MinionClass) i).convertToOut());
            } else {
                cardsOut.add(((EnvironmentCard) i).convertToOut());
            }
        }
        getPlayerDeck.putPOJO("output", cardsOut);
        output.add(getPlayerDeck);
    }

    /**
     * prints to json the cards on the table
     * @param board the table
     */
    public final void getCardsOnTable(final Board board) {
        ObjectNode getCardsOnTable = objectMapper.createObjectNode();
        getCardsOnTable.put("command", "getCardsOnTable");
        ArrayList<ArrayList<Card>> table = new ArrayList<>();
        BoardOut boardOut = board.convertOut();
        getCardsOnTable.putPOJO("output", boardOut.getBoard());
        output.add(getCardsOnTable);
    }

    /**
     * get players turn
     * @param player1 player
     * @return 1 or 2, index of player
     */
    public final int getPlayerTurn(final Player player1) {
        if (player1.isTurn()) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * prints the players hero in json
     * @param player player
     */
    public final void getPlayerHero(final Player player) {
        ObjectNode getPlayerHero = objectMapper.createObjectNode();
        getPlayerHero.put("command", "getPlayerHero");
        if (player.isPlayerNr()) {
            getPlayerHero.put("playerIdx", 2);
        } else {
            getPlayerHero.put("playerIdx", 1);
        }
        HeroOut heroOut = player.getHero().convertToOut();
        getPlayerHero.putPOJO("output", heroOut);
        output.add(getPlayerHero);
    }

    /**
     * prints a card placed on the board
     * @param board board
     * @param x row
     * @param y column
     */
    public final void getCardAtPosition(final Board board, final int x, final int y) {
        ObjectNode getCardAtPosition = objectMapper.createObjectNode();
        getCardAtPosition.put("command", "getCardAtPosition");
        getCardAtPosition.put("x", x);
        getCardAtPosition.put("y", y);
        if (y > board.getRow(x).size() || board.getRow(x).size() == 0) {
            getCardAtPosition.put("output", "No card available at that position.");
            output.add(getCardAtPosition);
            return;
        } else {
            getCardAtPosition.putPOJO("output", board.getRow(x).get(y).convertToOut());
        }
        output.add(getCardAtPosition);
    }

    /**
     * prints players current mana
     * @param player player
     */
    public final void getPlayerMana(final Player player) {
        ObjectNode getPlayerMana = objectMapper.createObjectNode();
        getPlayerMana.put("command", "getPlayerMana");
        if (player.isPlayerNr()) {
            getPlayerMana.put("playerIdx", 2);
        }  else {
            getPlayerMana.put("playerIdx", 1);
        }
        getPlayerMana.put("output", player.getMana());
        output.add(getPlayerMana);
    }

    /**
     * prints the environment cards from hand
     * @param player
     */
    public final void getEnvironmentCards(final Player player) {
        ObjectNode getEnvironmentCardsInHand = objectMapper.createObjectNode();
        getEnvironmentCardsInHand.put("command", "getEnvironmentCardsInHand");
        if (player.isPlayerNr()) {
            getEnvironmentCardsInHand.put("playerIdx", 2);
        } else {
            getEnvironmentCardsInHand.put("playerIdx", 1);
        }
        ArrayList<Card> hand = player.getHand();
        ArrayList<EnvironmentOut> environmentCards = new ArrayList<>();
        for (Card i : hand) {
            if (i instanceof EnvironmentCard) {
                environmentCards.add(((EnvironmentCard) i).convertToOut());
            }
        }
        getEnvironmentCardsInHand.putPOJO("output", environmentCards);
        output.add(getEnvironmentCardsInHand);
    }

    /**
     * get frozen cards from the board
     * @param board the board
     */
    public final void getFrozenCards(final Board board) {
        ObjectNode getFrozenCardsOnTable = objectMapper.createObjectNode();
        getFrozenCardsOnTable.put("command", "getFrozenCardsOnTable");
        ArrayList<MinionClass> frozenCards = new ArrayList<>();
        for (int i = 0; i < MagicNumbers.NRROWS; i++) {
            for (MinionClass j : board.getRow(i)) {
                if (j.isFrozen()) {
                    frozenCards.add(j);
                }
            }
        }
        getFrozenCardsOnTable.putPOJO("output", frozenCards);
        output.add(getFrozenCardsOnTable);
    }
}
