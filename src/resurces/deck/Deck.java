package resurces.deck;

import fileio.CardInput;
import resurces.cards.Card;

import java.util.ArrayList;

public class Deck {
    private final int nrCardInDeck;
    ArrayList<Card> deck;

    public Deck(int nrCardInDeck) {
        this.nrCardInDeck=nrCardInDeck;
        deck = new ArrayList<>(nrCardInDeck);
    }

    public void setDeck(ArrayList<CardInput> deck) {
        for (int i=0; i < nrCardInDeck; i++) {
            Card auxCard = new Card(deck.get(i).getMana(),
                                    deck.get(i).getDescription(),
                                    deck.get(i).getColors(),
                                    deck.get(i).getName());
            this.deck.add(i,auxCard);
        }
    }

    public int getNrCardInDeck() {
        return nrCardInDeck;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
