package resurces;

import resurces.cards.Card;
import resurces.cards.hero.Hero;
import resurces.deck.Deck;

import java.util.ArrayList;

public class Player {
    private Deck deck;
    private Hero hero;
    private ArrayList<Card> hand;
    private int mana = 0;
    private int maxMana;
    private int wins = 0;
    public Player(Deck deck, Hero hero) {
        this.deck = deck;
        this.hero = hero;
    }
    public Deck getDeck() {
        return deck;
    }
    public Hero getHero() {
        return hero;
    }
    public ArrayList<Card> getHand() {
        return hand;
    }
    public int getMana() {
        return mana;
    }
    public int getWins() {
        return wins;
    }

    public void incrementMana() {
        if(maxMana == 10) {
            mana = maxMana;
        }
        maxMana++;
        mana=maxMana;
    }
    public void incrementWins() {
        wins++;
    }
}
