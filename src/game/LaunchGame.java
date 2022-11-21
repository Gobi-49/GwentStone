package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Input;
import resurces.deck.Deck;

import java.io.DataOutput;
import java.util.ArrayList;

public class LaunchGame {
    public static void game(final Input input, final ArrayNode output) {

        ArrayList<Deck> decksPlayerOne = generateDeckPlayerOne(input);
        ArrayList<Deck> decksPlayerTwo = generateDeckPlayerTwo(input);
        System.out.println(decksPlayerOne.get(0).getDeck().get(0).getColors());
        System.out.println(decksPlayerTwo.get(0).getDeck().get(0).getColors());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode PlayerOne = mapper.createObjectNode();
        PlayerOne.put("nrCardsInDeck", decksPlayerOne.get(0).getNrCardInDeck());
        PlayerOne.put("nrDecks", decksPlayerOne.size());
        output.add(PlayerOne);
    }
    public static ArrayList<Deck> generateDeckPlayerOne(Input input) {
        ArrayList<Deck> decksPlayer = new ArrayList<>();
        for (int i = 0; i < input.getPlayerOneDecks().getNrDecks(); i++) {
            decksPlayer.add(i, new Deck(input.getPlayerOneDecks().getNrCardsInDeck()));
            decksPlayer.get(i).setDeck(input.getPlayerOneDecks().getDecks().get(i));
        }
        return decksPlayer;
    }

    public static ArrayList<Deck> generateDeckPlayerTwo(Input input) {
        ArrayList<Deck> decksPlayer = new ArrayList<>();
        for (int i = 0; i < input.getPlayerTwoDecks().getNrDecks(); i++) {
            decksPlayer.add(i, new Deck(input.getPlayerTwoDecks().getNrCardsInDeck()));
            decksPlayer.get(i).setDeck(input.getPlayerTwoDecks().getDecks().get(i));
        }
        return decksPlayer;
    }
}
