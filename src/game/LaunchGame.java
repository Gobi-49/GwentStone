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
import resurces.cards.hero.Hero;

import java.util.ArrayList;

public class LaunchGame {
    public static void game(final Input input, final ArrayNode output, ObjectMapper objectMapper) {
        Player p1 = new Player(input.getPlayerOneDecks().getNrCardsInDeck());
        Player p2 = new Player(input.getPlayerTwoDecks().getNrCardsInDeck());
        Board board = new Board();
        preparePlayers(p1,p2,input);
        prepareGame(p1,p2,input,0);
        runRound(p1,p2,input.getGames().get(0),board, output, objectMapper);
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
                input.getGames().get(gameNr).getStartGame().getPlayerOneHero().getName(), false);
        Hero p2Hero = new Hero(input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getMana(),
                input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getDescription(),
                input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getColors(),
                input.getGames().get(gameNr).getStartGame().getPlayerTwoHero().getName(), true);
        p1.setHero(p1Hero);
        p2.setHero(p2Hero);
        int x = input.getGames().get(gameNr).getStartGame().getStartingPlayer();
        if(x != 1)
            p2.getHero().setPlayer(true);
        else
            p1.getHero().setPlayer(true);
    }
    public static void startRound(Player p1, Player p2) {
        p1.addCardToHand();
        p1.incrementMana();
        p2.addCardToHand();
        p2.incrementMana();
    }
    public static void runRound(Player p1, Player p2, GameInput input, Board board, ArrayNode output, ObjectMapper objectMapper) {
        startRound(p1, p2);
        int nrActions = input.getActions().size();
        String action;
        DebugCommands debug = new DebugCommands(objectMapper, output);
        StatsCommands stats = new StatsCommands();
        for(int i=0 ; i < nrActions; i++) {
            action = input.getActions().get(i).getCommand();
            switch (action) {
                case "getPlayerDeck":
                    ObjectNode getPlayerDeck = objectMapper.createObjectNode();
                    getPlayerDeck.put("command", "getPlayerDeck");
                    getPlayerDeck.put("playerIdx", input.getActions().get(i).getPlayerIdx());
                    output.add(getPlayerDeck) ;

                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getPlayerDeck(p1);
                    else
                        debug.getPlayerDeck(p2);
                    break;
                case "getPlayerHero":
                    ObjectNode getPlayerHero = objectMapper.createObjectNode();
                    getPlayerHero.put("command", "getPlayerHero");
                    getPlayerHero.put("playerIdx", input.getActions().get(i).getPlayerIdx());
                    output.add(getPlayerHero) ;

                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getPlayerHero(p1);
                    else
                        debug.getPlayerHero(p2);
                    break;
                case "getPlayerTurn":
                    ObjectNode getPlayerTurn = objectMapper.createObjectNode();
                    getPlayerTurn.put("command", "getPlayerTurn");
                    output.add(getPlayerTurn) ;

                    debug.getPlayerTurn(p1);
                    break;
                case "getCardsInHand":
                    ObjectNode getCardsInHand = objectMapper.createObjectNode();
                    getCardsInHand.put("command", "getCardsInHand");
                    getCardsInHand.put("playerIdx", input.getActions().get(i).getPlayerIdx());
                    output.add(getCardsInHand) ;

                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getCardsInHand(p1);
                    else
                        debug.getCardsInHand(p2);
                    break;
                case "getCardsOnTable":
                    ObjectNode getCardsOnTable = objectMapper.createObjectNode();
                    getCardsOnTable.put("command", "getCardsOnTable");
                    output.add(getCardsOnTable) ;

                    debug.getCardsOnTable(board);
                    break;
                case "getCardAtPosition":
                    // Todo
                    ObjectNode getCardAtPosition = objectMapper.createObjectNode();
                    getCardAtPosition.put("command", "getCardAtPosition");
                    output.add(getCardAtPosition) ;

                    break;
                case "getPlayerMana":
                    ObjectNode getPlayerMana = objectMapper.createObjectNode();
                    getPlayerMana.put("command", "getPlayerMana");
                    getPlayerMana.put("playerIdx", input.getActions().get(i).getPlayerIdx());
                    output.add(getPlayerMana) ;

                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getPlayerMana(p1);
                    else
                        debug.getPlayerMana(p2);
                    break;
                case "getEnvironmentCardsInHand":
                    ObjectNode getEnvironmentCardsInHand = objectMapper.createObjectNode();
                    getEnvironmentCardsInHand.put("command", "getEnvironmentCardsInHand");
                    getEnvironmentCardsInHand.put("playerIdx", input.getActions().get(i).getPlayerIdx());
                    output.add(getEnvironmentCardsInHand) ;

                    if (input.getActions().get(i).getPlayerIdx() == 1)
                        debug.getEnvironmentCards(p1);
                    else
                        debug.getEnvironmentCards(p2);
                    break;
                case "getFrozenCardsOnTable":
                    ObjectNode getFrozenCardsOnTable = objectMapper.createObjectNode();
                    getFrozenCardsOnTable.put("command", "getFrozenCardsOnTable");
                    output.add(getFrozenCardsOnTable) ;

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
            }
        }
    }
}
