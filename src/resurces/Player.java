package resurces;

import resurces.cards.Card;
import resurces.cards.enviroment.EnvironmentCard;
import resurces.cards.hero.Hero;
import resurces.cards.minions.MinionClass;
import resurces.cards.minions.regular.GoliathCard;
import resurces.cards.minions.regular.WardenClass;
import resurces.cards.minions.special.MirajCard;
import resurces.cards.minions.special.RipperCard;

import java.util.ArrayList;

public class Player {
    private ArrayList<Deck> allDecks;
    private final int nrCardInDeck;
    private Deck deck;
    private Hero hero;
    private ArrayList<Card> hand = new ArrayList<>();
    private int mana = 0;
    private int maxMana;
    private int wins = 0;
    private boolean playerNr = false;
    public Player(int nrCardInDeck) {
        this.nrCardInDeck = nrCardInDeck;
    }
    public Player(Deck deck, Hero hero, int nrCardInDeck) {
        this.deck = deck;
        this.hero = hero;
        this.nrCardInDeck = nrCardInDeck;
    }
    public int getNrCardInDeck() {
        return nrCardInDeck;
    }
    public void setAllDecks(ArrayList<Deck> allDecks) {
        this.allDecks = allDecks;
    }
    public ArrayList<Deck> getAllDecks() {
        return allDecks;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public Deck getDeck() {
        return deck;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
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
    public void setPlayerNr(boolean playerNr) {
        this.playerNr = playerNr;
    }
    public boolean isPlayerNr() {
        return playerNr;
    }

    public void incrementMana() {
        if(maxMana == 10) {
            mana = maxMana;
        }
        maxMana++;
        mana+=maxMana;
        if(mana > 10) {
            mana = 10;
        }
    }
    public void addCardToHand() {
        if(deck.getCards().size() > 0) {
            hand.add(deck.getCards().get(0));
            deck.getCards().remove(0);
        }
    }
    public void incrementWins() {
        wins++;
    }
    public void placeCard(int index, Board board) {
        if(hand.get(index) instanceof EnvironmentCard) {
            System.out.println("Cannot place environment card");
            return;
        }
        Card card = hand.get(index);
        if (mana < card.getMana()) {
            System.out.println("Not enough mana");
            return;
        }
        if (    card instanceof RipperCard ||
                card instanceof MirajCard ||
                card instanceof GoliathCard ||
                card instanceof WardenClass) {
            if (board.getRow(1).size() == 5 || board.getRow(2).size() == 5) {
                System.out.println("Cannot place card on table since row in full");
            }
            if(playerNr) {
                board.getRow(1).add((MinionClass) card);
            }
            else {
                board.getRow(2).add((MinionClass) card);
            }
        }
        else {
            if (board.getRow(0).size() == 5 || board.getRow(3).size() == 5) {
                System.out.println("Cannot place card on table since row in full");
            }
            if(playerNr) {
                board.getRow(0).add((MinionClass) card);
            }
            else {
                board.getRow(3).add((MinionClass) card);
            }
        }
    }
}
