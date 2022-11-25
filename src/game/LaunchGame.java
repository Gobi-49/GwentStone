package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.GameInput;
import fileio.Input;
import fileio.StartGameInput;
import resurces.Board;
import resurces.Player;
import resurces.Deck;
import resurces.cards.MagicNumbers;
import resurces.cards.enviroment.EnvironmentCard;
import resurces.cards.hero.Hero;
import resurces.cards.hero.GeneralKocioraw;
import resurces.cards.hero.KingMudface;
import resurces.cards.hero.EmpressThorina;
import resurces.cards.hero.LordRoyce;
import resurces.cards.minions.MinionClass;

import java.util.ArrayList;

public final class LaunchGame {
    private LaunchGame() {

    }

    /**
     * This  is where the game starts to run
     * @param input the input type
     * @param output hte output type
     * @param objectMapper the object mapper
     */
    public static void game(final Input input, final ArrayNode output,
                            final ObjectMapper objectMapper) {
        Player p1 = new Player(input.getPlayerOneDecks().getNrCardsInDeck());
        Player p2 = new Player(input.getPlayerTwoDecks().getNrCardsInDeck());
        p2.setPlayerNr(true);
        Board board = new Board();
        preparePlayers(p1, p2, input);
        for (int i = 0; i < input.getGames().size(); i++) {
            prepareGame(p1, p2, input, i);
            runGame(p1, p2, input.getGames().get(i), board, output, objectMapper);
            p1.resetPlayer();
            p2.resetPlayer();
            board.resetBoard();
        }

    }

    /**
     * generates the decks of player one
     * @param input input type
     * @param nrCardsInDeck nr cards in deck
     * @return returns the array of decks
     */
    public static ArrayList<Deck> generateDeckPlayerOne(final Input input,
                                                        final int nrCardsInDeck) {
        ArrayList<Deck> decksPlayer = new ArrayList<>();
        for (int i = 0; i < input.getPlayerOneDecks().getNrDecks(); i++) {
            decksPlayer.add(i, new Deck());
            decksPlayer.get(i).insertCardsIoDeck(input.getPlayerOneDecks().getDecks().get(i),
                    nrCardsInDeck);
        }
        return decksPlayer;
    }
    /**
     * generates the decks of player two
     * @param input input type
     * @param nrCardsInDeck nr cards in deck
     * @return returns the array of decks
     */
    public static ArrayList<Deck> generateDeckPlayerTwo(final Input input,
                                                        final int nrCardsInDeck) {
        ArrayList<Deck> decksPlayer = new ArrayList<>();
        for (int i = 0; i < input.getPlayerTwoDecks().getNrDecks(); i++) {
            decksPlayer.add(i, new Deck());
            decksPlayer.get(i).insertCardsIoDeck(input.getPlayerTwoDecks().getDecks().get(i),
                    nrCardsInDeck);
        }
        return decksPlayer;
    }

    /**
     * prepares players for the game assigning them the decks
     * @param p1 player one
     * @param p2 player two
     * @param input input type
     */
    public static void preparePlayers(final Player p1, final Player p2, final Input input) {
        p1.setAllDecks(generateDeckPlayerOne(input, p1.getNrCardInDeck()));
        p2.setAllDecks(generateDeckPlayerTwo(input, p2.getNrCardInDeck()));
    }

    /**
     * Used to prepare the game, assigns each player his deck, shuffles it, assigns his hero
     * Sets each player booleans
     * @param p1 player one
     * @param p2 player two
     * @param input input type
     * @param gameNr game index
     */
    public static void prepareGame(final Player p1, final Player p2, final Input input,
                                   final int gameNr) {
        int p1Deck = input.getGames().get(gameNr).getStartGame().getPlayerOneDeckIdx();
        int p2Deck = input.getGames().get(gameNr).getStartGame().getPlayerTwoDeckIdx();
        int shuffleSeed = input.getGames().get(gameNr).getStartGame().getShuffleSeed();
        p1.setDeck(new Deck());
        p2.setDeck(new Deck());
        p1.getDeck().copyCardsFromDecks(p1.getAllDecks().get(p1Deck).getCards(),
                p1.getNrCardInDeck());
        p2.getDeck().copyCardsFromDecks(p2.getAllDecks().get(p2Deck).getCards(),
                p2.getNrCardInDeck());
        p1.getDeck().shuffleCards(shuffleSeed);
        p2.getDeck().shuffleCards(shuffleSeed);
        String heroName = input.getGames().get(gameNr).getStartGame().getPlayerOneHero().getName();
        Hero p1Hero = null, p2Hero = null;
        StartGameInput aux = input.getGames().get(gameNr).getStartGame();
        switch (heroName) {
            case "Lord Royce" ->
                    p1Hero = new LordRoyce(aux.getPlayerOneHero().getMana(),
                            aux.getPlayerOneHero().getDescription(),
                            aux.getPlayerOneHero().getColors(), aux.getPlayerOneHero().getName());
            case "Empress Thorina" ->
                    p1Hero = new EmpressThorina(aux.getPlayerOneHero().getMana(),
                            aux.getPlayerOneHero().getDescription(),
                            aux.getPlayerOneHero().getColors(),
                            aux.getPlayerOneHero().getName());
            case "King Mudface" ->
                    p1Hero = new
                            KingMudface(aux.getPlayerOneHero().getMana(),
                            aux.getPlayerOneHero().getDescription(),
                            aux.getPlayerOneHero().getColors(),
                            aux.getPlayerOneHero().getName());
            case "General Kocioraw" ->
                    p1Hero = new GeneralKocioraw(aux.getPlayerOneHero().getMana(),
                            aux.getPlayerOneHero().getDescription(),
                            aux.getPlayerOneHero().getColors(),
                            aux.getPlayerOneHero().getName());
        }
        heroName = aux.getPlayerTwoHero().getName();
        switch (heroName) {
            case "Lord Royce" ->
                    p2Hero = new LordRoyce(aux.getPlayerTwoHero().getMana(),
                            aux.getPlayerTwoHero().getDescription(),
                            aux.getPlayerTwoHero().getColors(),
                            aux.getPlayerTwoHero().getName());
            case "Empress Thorina" ->
                    p2Hero = new EmpressThorina(aux.getPlayerTwoHero().getMana(),
                            aux.getPlayerTwoHero().getDescription(),
                            aux.getPlayerTwoHero().getColors(),
                            aux.getPlayerTwoHero().getName());
            case "King Mudface" ->
                    p2Hero = new KingMudface(aux.getPlayerTwoHero().getMana(),
                            aux.getPlayerTwoHero().getDescription(),
                            aux.getPlayerTwoHero().getColors(),
                            aux.getPlayerTwoHero().getName());
            case "General Kocioraw" ->
                    p2Hero = new GeneralKocioraw(aux.getPlayerTwoHero().getMana(),
                            aux.getPlayerTwoHero().getDescription(),
                            aux.getPlayerTwoHero().getColors(),
                            aux.getPlayerTwoHero().getName());
        }
        p1.setHero(p1Hero);
        p2.setHero(p2Hero);
        p2.setPlayerNr(true);
        if (aux.getStartingPlayer() == 1) {
            p1.setTurn(true);
        } else {
            p2.setTurn(true);
        }
    }

    /**
     * Starts the round
     * @param p1 player one
     * @param p2 player two
     */
    public static void startRound(final Player p1, final Player p2) {
        p1.addCardToHand();
        p1.incrementMana();
        p2.addCardToHand();
        p2.incrementMana();
    }

    /**
     * Runs a game
     * @param p1 player one
     * @param p2 player two
     * @param input input type
     * @param board the board
     * @param output output type
     * @param objectMapper the object mapper
     */
    public static void runGame(final Player p1, final Player p2, final GameInput input,
                               final Board board, final ArrayNode output,
                               final ObjectMapper objectMapper) {
        startRound(p1, p2);
        int nrActions = input.getActions().size();
        String action;
        DebugCommands debug = new DebugCommands(objectMapper, output);
        StatsCommands stats = new StatsCommands();
        boolean nextTurnIsRound = false;
        int x, y, affectedRow;
        int handIdx;
        String error;
        MinionClass attacker;
        Hero hero;
        for (int i = 0; i < nrActions; i++) {
            action = input.getActions().get(i).getCommand();
            if (action.equals("endPlayerTurn")) {
                if (nextTurnIsRound) {
                    startRound(p1, p2);
                    nextTurnIsRound = false;
                } else {
                    nextTurnIsRound = true;
                }
                endTurn(p1, p2, board);
            }
            switch (action) {
                case "getPlayerDeck":
                    if (input.getActions().get(i).getPlayerIdx() == 1) {
                        debug.getPlayerDeck(p1);
                    } else {
                        debug.getPlayerDeck(p2);
                    }
                    break;
                case "getPlayerHero":
                    if (input.getActions().get(i).getPlayerIdx() == 1) {
                        debug.getPlayerHero(p1);
                    } else {
                        debug.getPlayerHero(p2);
                    }
                    break;
                case "getPlayerTurn":
                    ObjectNode getPlayerTurn = objectMapper.createObjectNode();
                    getPlayerTurn.put("command", "getPlayerTurn");
                    getPlayerTurn.put("output",  debug.getPlayerTurn(p1));
                    output.add(getPlayerTurn);
                    break;
                case "getCardsInHand":
                    if (input.getActions().get(i).getPlayerIdx() == 1) {
                        debug.getCardsInHand(p1);
                    } else {
                        debug.getCardsInHand(p2);
                    }
                    break;
                case "getCardsOnTable":
                    debug.getCardsOnTable(board);
                    break;
                case "getCardAtPosition":
                    x = input.getActions().get(i).getX();
                    y = input.getActions().get(i).getY();
                    debug.getCardAtPosition(board, x, y);
                    break;
                case "getPlayerMana":
                    if (input.getActions().get(i).getPlayerIdx() == 1) {
                        debug.getPlayerMana(p1);
                    } else {
                        debug.getPlayerMana(p2);
                    }
                    break;
                case "getEnvironmentCardsInHand":
                    if (input.getActions().get(i).getPlayerIdx() == 1) {
                        debug.getEnvironmentCards(p1);
                    } else {
                        debug.getEnvironmentCards(p2);
                    }
                    break;
                case "getFrozenCardsOnTable":
                    debug.getFrozenCards(board);
                    break;
                case "getTotalGamesPlayed":
                    ObjectNode getTotalGamesPlayed = objectMapper.createObjectNode();
                    getTotalGamesPlayed.put("command", "getTotalGamesPlayed");
                    getTotalGamesPlayed.put("output", stats.getTotalGamesPlayed(p1, p2));
                    output.add(getTotalGamesPlayed);
                    break;
                case "getPlayerOneWins":
                    ObjectNode getPlayerOneWins = objectMapper.createObjectNode();
                    getPlayerOneWins.put("command", "getPlayerOneWins");
                    getPlayerOneWins.put("output", stats.getPlayerWins(p1));
                    output.add(getPlayerOneWins);
                    break;
                case "getPlayerTwoWins":
                    ObjectNode getPlayerTwoWins = objectMapper.createObjectNode();
                    getPlayerTwoWins.put("command", "getPlayerTwoWins");
                    getPlayerTwoWins.put("output", stats.getPlayerWins(p2));
                    output.add(getPlayerTwoWins);
                    break;
                case "placeCard":
                    handIdx = input.getActions().get(i).getHandIdx();
                    error = null;
                    if (p1.isTurn()) {
                        error = p1.placeCard(handIdx, board);
                    } else {
                        error = p2.placeCard(handIdx, board);
                    }
                    if (error != null) {
                        ObjectNode placeCard = objectMapper.createObjectNode();
                        placeCard.put("command", "placeCard");
                        placeCard.put("handIdx", handIdx);
                        placeCard.put("error", error);
                        output.add(placeCard);
                    }
                    break;
                case "useEnvironmentCard":
                    handIdx = input.getActions().get(i).getHandIdx();
                    affectedRow = input.getActions().get(i).getAffectedRow();
                    EnvironmentCard card = null;
                    error = null;
                    if (p1.isTurn()) {
                        if (p1.getHand().get(handIdx) instanceof EnvironmentCard) {
                            card = (EnvironmentCard) p1.getHand().get(handIdx);
                        } else {
                            error = "Chosen card is not of type environment.";
                        }
                    } else {
                        if (p2.getHand().get(handIdx) instanceof EnvironmentCard) {
                            card = (EnvironmentCard) p2.getHand().get(handIdx);
                        } else {
                            error = "Chosen card is not of type environment.";
                        }
                    }
                    if (card != null) {
                        if (p1.isTurn()) {
                            error = card.useCard(board, p1, affectedRow);
                        } else {
                            error = card.useCard(board, p2, affectedRow);
                        }
                    }

                    if (error != null) {
                        ObjectNode useEnvironmentCard = objectMapper.createObjectNode();
                        useEnvironmentCard.put("command", "useEnvironmentCard");
                        useEnvironmentCard.put("handIdx", handIdx);
                        useEnvironmentCard.put("affectedRow", affectedRow);
                        useEnvironmentCard.put("error", error);
                        output.add(useEnvironmentCard);
                    }
                    if (error == null) {
                        if (p1.isTurn()) {
                            p1.getHand().remove(handIdx);
                        } else {
                            p2.getHand().remove(handIdx);
                        }
                    }
                    break;
                case "cardUsesAttack":
                    x = input.getActions().get(i).getCardAttacker().getX();
                    y = input.getActions().get(i).getCardAttacker().getY();
                    attacker = board.getRow(x).get(y);
                    error = null;
                    error = attacker.attackCard(board, x, y, input.getActions().get(i).getCardAttacked().getX(),
                            input.getActions().get(i).getCardAttacked().getY());
                    if (error != null) {
                        ObjectNode cardUsesAttack = objectMapper.createObjectNode();
                        cardUsesAttack.put("command", "cardUsesAttack");
                        cardUsesAttack.putPOJO("cardAttacker",
                                input.getActions().get(i).getCardAttacker());
                        cardUsesAttack.putPOJO("cardAttacked",
                                input.getActions().get(i).getCardAttacked());
                        cardUsesAttack.put("error", error);
                        output.add(cardUsesAttack);
                    }
                    break;
                case "cardUsesAbility":
                    x = input.getActions().get(i).getCardAttacker().getX();
                    y = input.getActions().get(i).getCardAttacker().getY();
                    attacker = board.getRow(x).get(y);
                    error = attacker.useAbility(board, x, y, input.getActions().get(i).getCardAttacked().getX(),
                            input.getActions().get(i).getCardAttacked().getY());
                    if (error != null) {
                        ObjectNode cardUsesAttack = objectMapper.createObjectNode();
                        cardUsesAttack.put("command", "cardUsesAbility");
                        cardUsesAttack.putPOJO("cardAttacker",
                                input.getActions().get(i).getCardAttacker());
                        cardUsesAttack.putPOJO("cardAttacked",
                                input.getActions().get(i).getCardAttacked());
                        cardUsesAttack.put("error", error);
                        output.add(cardUsesAttack);
                    }
                    break;
                case "useAttackHero":
                    x = input.getActions().get(i).getCardAttacker().getX();
                    y = input.getActions().get(i).getCardAttacker().getY();
                    error = null;
                    attacker = board.getRow(x).get(y);
                    if (p1.isTurn()) {
                        error = attacker.attackHero(board, x, y, p2.getHero());
                    } else {
                        error = attacker.attackHero(board, x, y, p1.getHero());
                    }
                    if (error != null) {
                        ObjectNode useAttackHero = objectMapper.createObjectNode();
                        if (error.equals("Player two killed the enemy hero.")) {
                            useAttackHero.put("gameEnded", error);
                            output.add(useAttackHero);
                            p2.incrementWins();
                        } else
                            if (error.equals("Player one killed the enemy hero.")) {
                                useAttackHero.put("gameEnded", error);
                                output.add(useAttackHero);
                                p1.incrementWins();
                            } else {
                            useAttackHero.put("command", "useAttackHero");
                            useAttackHero.putPOJO("cardAttacker", input.getActions().get(i).getCardAttacker());
                            useAttackHero.put("error", error);
                            output.add(useAttackHero);
                        }
                    }
                    break;
                case "useHeroAbility":
                    affectedRow = input.getActions().get(i).getAffectedRow();
                    if (p1.isTurn()) {
                        hero = p1.getHero();
                        error = hero.useAbility(board, affectedRow, p1);
                    } else {
                        hero = p2.getHero();
                        error = hero.useAbility(board, affectedRow, p2);
                    }
                    if (error != null) {
                        ObjectNode cardUsesAttack = objectMapper.createObjectNode();
                        cardUsesAttack.put("command", "useHeroAbility");
                        cardUsesAttack.putPOJO("affectedRow", affectedRow);
                        cardUsesAttack.put("error", error);
                        output.add(cardUsesAttack);
                    }
                    break;
            }

        }
    }

    /**
     * ends the turn, resets turn, remove debuff
     * @param p1 player one
     * @param p2 player two
     * @param board board
     */
    public static void endTurn(final Player p1, final Player p2, final Board board) {
        if (p1.isTurn()) {
            for (MinionClass j : board.getRow(MagicNumbers.ROW2)) {
                j.setFrozen(false);
            }
            for (MinionClass j : board.getRow(MagicNumbers.ROW3)) {
                j.setFrozen(false);
            }
        } else {
            for (MinionClass j : board.getRow(MagicNumbers.ROW0)) {
                j.setFrozen(false);
            }
            for (MinionClass j : board.getRow(MagicNumbers.ROW1)) {
                j.setFrozen(false);
            }
        }
        p1.setTurn(!p1.isTurn());
        p2.setTurn(!p2.isTurn());
        p1.getHero().setPlayed(false);
        p2.getHero().setPlayed(false);
        for (ArrayList<MinionClass> i : board.getBoard()) {
            for (MinionClass j : i) {
                j.setPlayed(false);
            }
        }
    }
}
