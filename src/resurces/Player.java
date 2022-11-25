package resurces;

import resurces.cards.Card;
import resurces.cards.MagicNumbers;
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
    private boolean turn = false;
    public Player(final int nrCardInDeck) {
        this.nrCardInDeck = nrCardInDeck;
    }
    public Player(final Deck deck, final Hero hero, final int nrCardInDeck) {
        this.deck = deck;
        this.hero = hero;
        this.nrCardInDeck = nrCardInDeck;
    }
    public final int getNrCardInDeck() {
        return nrCardInDeck;
    }
    public final void setAllDecks(final ArrayList<Deck> allDecks) {
        this.allDecks = allDecks;
    }
    public final ArrayList<Deck> getAllDecks() {
        return allDecks;
    }
    public final void setDeck(final Deck deck) {
        this.deck = deck;
    }
    public final Deck getDeck() {
        return deck;
    }
    public final void setHero(final Hero hero) {
        this.hero = hero;
    }
    public final Hero getHero() {
        return hero;
    }
    public final ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * reduces the value of the mana
     * @param usedMana value of mana reduction
     */
    public final void useMana(final int usedMana) {
        this.mana -= usedMana;
    }
    public final int getMana() {
        return mana;
    }
    public final int getWins() {
        return wins;
    }
    public final void setPlayerNr(final boolean playerNr) {
        this.playerNr = playerNr;
    }
    public final boolean isPlayerNr() {
        return playerNr;
    }
    public final void setTurn(final boolean turn) {
        this.turn = turn;
    }
    public final boolean isTurn() {
        return turn;
    }

    /**
     * increments the mana after a round
     */
    public final void incrementMana() {
        if (maxMana == MagicNumbers.MAXMANA) {
            mana += maxMana;
        }
        maxMana++;
        mana += maxMana;
    }

    /**
     * Adds card to hand from the deck
     */
    public final void addCardToHand() {
        if (deck.getCards().size() > MagicNumbers.ZERO) {
            hand.add(deck.getCards().get(0));
            deck.getCards().remove(MagicNumbers.ZERO);
        }
    }

    /**
     * increment the wins of the player
     */
    public final void incrementWins() {
        wins++;
    }

    /**
     * Places card on the board
     * @param index index of the card in the hand
     * @param board the board
     * @return error message
     */
    public final String placeCard(final int index, final Board board) {
        if (hand.get(index) instanceof EnvironmentCard) {
            return "Cannot place environment card on table.";
        }
        Card card = hand.get(index);
        if (mana < card.getMana()) {
            return "Not enough mana to place card on table.";
        }
        if (card instanceof RipperCard
                || card instanceof MirajCard
                || card instanceof GoliathCard
                || card instanceof WardenClass) {
            if (board.getRow(MagicNumbers.ROW1).size() == MagicNumbers.NRCOLUMNS
                    && playerNr || board.getRow(MagicNumbers.ROW2).size() == MagicNumbers.NRCOLUMNS
                    && !playerNr) {
                return "Cannot place card on table since row is full.";
            }
            if (playerNr) {
                board.getRow(MagicNumbers.ROW1).add((MinionClass) card);
            } else {
                board.getRow(MagicNumbers.ROW2).add((MinionClass) card);
            }
        } else {
            if (board.getRow(MagicNumbers.ROW0).size() == MagicNumbers.NRCOLUMNS
                    || board.getRow(MagicNumbers.ROW3).size() == MagicNumbers.NRCOLUMNS) {
                return "Cannot place card on table since row is full.";
            }
            if (playerNr) {
                board.getRow(MagicNumbers.ROW0).add((MinionClass) card);
            } else {
                board.getRow(MagicNumbers.ROW3).add((MinionClass) card);
            }
        }
        hand.remove(index);
        mana -= card.getMana();
        return null;
    }

    /**
     * resets the values saved in player
     */
    public final void resetPlayer() {
        deck = null;
        hero = null;
        hand = new ArrayList<>();
        mana = 0;
        maxMana = 0;
        playerNr = false;
        turn = false;
    }
}
