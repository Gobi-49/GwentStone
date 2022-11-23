package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.GameInput;
import fileio.Input;
import resurces.Board;
import resurces.Player;
import resurces.Deck;
import resurces.cards.enviroment.EnvironmentCard;
import resurces.cards.hero.Hero;
import resurces.cards.minions.regular.SenitnelCard;

import java.util.ArrayList;

public class LaunchGame {
    public static void game(final Input input, final ArrayNode output, ObjectMapper objectMapper) {
        Player p1 = new Player(input.getPlayerOneDecks().getNrCardsInDeck());
        Player p2 = new Player(input.getPlayerTwoDecks().getNrCardsInDeck());
        p2.setPlayerNr(true);
        Board board = new Board();
        preparePlayers(p1,p2,input);
        prepareGame(p1,p2,input,0);
        runGame(p1,p2,input.getGames().get(0),board, output, objectMapper);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    public static ArrayList<Deck> generateDeckPlayerOne(Input input, int nrCardsInDeck) {
        ArrayList<Deck> decksPlayer = new ArrayList<>();
        for (int i = 0; i < input.getPlayerOneDecks().getNrDecks(); i++) {
            decksPlayer.add(i, new Deck());
            decksPlayer.get(i).insertCardsIoDeck(input.getPlayerOneDecks().getDecks().get(i), nrCardsInDeck);
        }
        return decksPlayer;
    }
    public static ArrayList<Deck> generateDeckPlayerTwo(Input input, int nrCardsInDeck) {
        ArrayList<Deck> decksPlayer = new ArrayList<>();
        for (int i = 0; i < input.getPlayerTwoDecks().getNrDecks(); i++) {
            decksPlayer.add(i, new Deck());
            decksPlayer.get(i).insertCardsIoDeck(input.getPlayerTwoDecks().getDecks().get(i), nrCardsInDeck);
        }
        return decksPlayer;
    }
    public static void preparePlayers(Player p1, Player p2, Input input) {
        p1.setAllDecks(generateDeckPlayerOne(input, p1.getNrCardInDeck()));
        p2.setAllDecks(generateDeckPlayerTwo(input, p2.getNrCardInDeck()));
    }
    public static void prepareGame(Player p1, Player p2, Input input, int gameNr) {
        int p1Deck = input.getGames().get(gameNr).getStartGame().getPlayerOneDeckIdx();
        int p2Deck = input.getGames().get(gameNr).getStartGame().getPlayerTwoDeckIdx();
        int shuffleSeed = input.getGames().get(gameNr).getStartGame().getShuffleSeed();
        p1.setDeck(new Deck(p1.getAllDecks().get(p1Deck).getCards() ));
        p2.setDeck(new Deck(p2.getAllDecks().get(p2Deck).getCards()));
        p1.getDeck().shuffleCards(shuffleSeed);
        p2.getDeck().shuffleCards(shuffleSeed);
        Hero p1Hero = new Hero(input.getGames().get(gameNr).getStartGame().getPlayerOneHero().getMana(),
                input.getGames().get(gameNr).getStartGame().getPlayerOneHero().getDescription(),
                input.getGames().get(gameNr).getStartGame().getPlayerOneHero().getColors(),
                input.getGames().get(gameNr).getStartGame().getPlayerOneHero().getName());
        Hero p2Hero = new Hero(input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getMana(),
                input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getDescription(),
                input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getColors(),
                input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getName());
        p1.setHero(p1Hero);
        p2.setHero(p2Hero);
        p2.setPlayerNr(true);
        if(input.getGames().get(gameNr).getStartGame().getStartingPlayer() == 1) {
            p1.setTurn(true);
        }
        else {
            p2.setTurn(true);
        }
    }
    public static void startRound(Player p1, Player p2) {
        p1.addCardToHand();
        p1.incrementMana();
        p2.addCardToHand();
        p2.incrementMana();
    }
    public static void runGame(Player p1, Player p2, GameInput input, Board board, ArrayNode output, ObjectMapper objectMapper) {
        startRound(p1, p2);
        int nrActions = input.getActions().size();
        String action;
        DebugCommands debug = new DebugCommands(objectMapper, output);
        StatsCommands stats = new StatsCommands();
        boolean nextTurnIsRound =false;
        int x, y, affectedRow;
        int handIdx;
        String error;
        for(int i=0 ; i < nrActions; i++) {
            action = input.getActions().get(i).getCommand();
            if (action.equals("endPlayerTurn")) {
                if(nextTurnIsRound) {
                    startRound(p1, p2);
                    nextTurnIsRound = false;
                } else {
                    nextTurnIsRound = true;
                }
                endTurn(p1, p2);
            }
            switch (action) {
                case "getPlayerDeck":
                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getPlayerDeck(p1);
                    else
                        debug.getPlayerDeck(p2);
                    break;
                case "getPlayerHero":
                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getPlayerHero(p1);
                    else
                        debug.getPlayerHero(p2);
                    break;
                case "getPlayerTurn":
                    ObjectNode getPlayerTurn = objectMapper.createObjectNode();
                    getPlayerTurn.put("command", "getPlayerTurn");
                    getPlayerTurn.put("output",  debug.getPlayerTurn(p1));
                    output.add(getPlayerTurn) ;
                    break;
                case "getCardsInHand":
                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getCardsInHand(p1);
                    else
                        debug.getCardsInHand(p2);
                    break;
                case "getCardsOnTable":
                    debug.getCardsOnTable(board);
                    break;
                case "getCardAtPosition":
                    x = input.getActions().get(i).getX();
                    y = input.getActions().get(i).getY();
                    debug.getCardAtPosition(board,x,y);
                    break;
                case "getPlayerMana":
                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getPlayerMana(p1);
                    else
                        debug.getPlayerMana(p2);
                    break;
                case "getEnvironmentCardsInHand":
                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getEnvironmentCards(p1);
                    else
                        debug.getEnvironmentCards(p2);
                    break;
                case "getFrozenCardsOnTable":
                    debug.getFrozenCards(board);
                    break;
                case "getTotalGamesPlayed":
                    ObjectNode getTotalGamesPlayed = objectMapper.createObjectNode();
                    getTotalGamesPlayed.put("command", "getTotalGamesPlayed");
                    output.add(getTotalGamesPlayed) ;

                    stats.getTotalGamesPlayed(p1,p2);
                    break;
                case "getPlayerOneWins":
                    ObjectNode getPlayerOneWins = objectMapper.createObjectNode();
                    getPlayerOneWins.put("command", "getPlayerOneWins");
                    output.add(getPlayerOneWins) ;

                    stats.getPlayerWins(p1);
                    break;
                case "getPlayerTwoWins":
                    ObjectNode getPlayerTwoWins = objectMapper.createObjectNode();
                    getPlayerTwoWins.put("command", "getPlayerTwoWins");
                    output.add(getPlayerTwoWins) ;

                    stats.getPlayerWins(p2);
                    break;
                case "placeCard":
                    handIdx = input.getActions().get(i).getHandIdx();
                    error = null;
                    if(p1.isTurn()) {
                        error = p1.placeCard(handIdx, board);
                    } else {
                        error = p2.placeCard(handIdx, board);
                    }
                    if(error != null) {
                        ObjectNode placeCard = objectMapper.createObjectNode();
                        placeCard.put("command", "placeCard");
                        placeCard.put("handIdx", handIdx);
                        placeCard.put("error", error);
                        output.add(placeCard) ;
                    }
                    break;
                case "useEnvironmentCard":
                    handIdx = input.getActions().get(i).getHandIdx();
                    affectedRow = input.getActions().get(i).getAffectedRow();
                    EnvironmentCard card = null;
                    error = null;
                    if(p1.isTurn()) {
                        if(p1.getHand().get(handIdx) instanceof EnvironmentCard)
                            card = (EnvironmentCard) p1.getHand().get(handIdx);
                        else
                            error = "Chosen card is not of type environment.";

                    } else {
                        if(p2.getHand().get(handIdx) instanceof EnvironmentCard)
                            card = (EnvironmentCard) p2.getHand().get(handIdx);
                        else
                            error = "Chosen card is not of type environment.";
                    }
                    if(card != null) {
                        if(p1.isTurn()) {
                            error = card.useCard(board,p1,affectedRow);
                        }
                        else
                            error = card.useCard(board,p2,affectedRow);
                    }

                    if(error != null) {
                        ObjectNode useEnvironmentCard = objectMapper.createObjectNode();
                        useEnvironmentCard.put("command", "useEnvironmentCard");
                        useEnvironmentCard.put("handIdx", handIdx);
                        useEnvironmentCard.put("affectedRow", affectedRow);
                        useEnvironmentCard.put("error", error);
                        output.add(useEnvironmentCard) ;
                    }
                    if(error == null) {
                        if(p1.isTurn()) {
                            p1.getHand().remove(handIdx);
                        }
                        else {
                            p2.getHand().remove(handIdx);
                        }
                    }
                    break;
                case "attackCard":
            }

        }
    }
    public static void endTurn(Player p1, Player p2) {
        p1.setTurn(!p1.isTurn());
        p2.setTurn(!p2.isTurn());
    }
}
