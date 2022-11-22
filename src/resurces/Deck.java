package resurces;

import fileio.CardInput;
import resurces.cards.Card;
import resurces.cards.enviroment.FirestormCard;
import resurces.cards.enviroment.HeartHoundCard;
import resurces.cards.enviroment.Winterfell;
import resurces.cards.minions.MinionClass;
import resurces.cards.minions.regular.BerserkerCard;
import resurces.cards.minions.regular.GoliathCard;
import resurces.cards.minions.regular.SenitnelCard;
import resurces.cards.minions.regular.WardenClass;
import resurces.cards.minions.special.CursedCard;
import resurces.cards.minions.special.DiscipleCard;
import resurces.cards.minions.special.MirajCard;
import resurces.cards.minions.special.RipperCard;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }
    public Deck(ArrayList<Card> cards) {
        this.cards = new ArrayList<>();
        this.cards.addAll(cards);
    }
    public void insertCardsIoDeck(ArrayList<CardInput> cards, int nrCardInDeck) {
        for (int i=0; i < nrCardInDeck; i++) {
            Card card;
            switch (cards.get(i).getName()) {
                case "Berserker" -> {
                    card = new BerserkerCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "Goliath" -> {
                    card = new GoliathCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "Sentinel" -> {
                    card = new SenitnelCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "Warden" -> {
                    card = new WardenClass(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "The Cursed One" -> {
                    card = new CursedCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "Disciple" -> {
                    card = new DiscipleCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "Miraj" -> {
                    card = new MirajCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth()) {
                    };
                    this.cards.add(card);
                }
                case "The Ripper" -> {
                    card = new RipperCard(cards.get(i).getName(),
                            cards.get(i).getColors(), cards.get(i).getDescription(),
                            cards.get(i).getMana(), cards.get(i).getAttackDamage(), cards.get(i).getHealth());
                    this.cards.add(card);
                }
                case "Firestorm" -> {
                    card = new FirestormCard(cards.get(i).getMana(),
                            cards.get(i).getName(), cards.get(i).getColors(),
                            cards.get(i).getDescription());
                    this.cards.add(card);
                }
                case "Winterfell" -> {
                    card = new Winterfell(cards.get(i).getMana(),
                            cards.get(i).getName(), cards.get(i).getColors(),
                            cards.get(i).getDescription());
                    this.cards.add(card);
                }
                case "Heart Hound" -> {
                    card = new HeartHoundCard(cards.get(i).getMana(),
                            cards.get(i).getName(), cards.get(i).getColors(),
                            cards.get(i).getDescription());
                    this.cards.add(card);
                }
            }
        }
    }
    public void shuffleCards(int seed) {
        Random rand = new Random(seed);
        shuffle(cards,rand);
    }
    public void copyCards(Deck cards) {
        this.cards = cards.getCards();

    }
    public int getNrCardInDeck() {
        return cards.size();
    }
    public ArrayList<Card> getCards() {
        return cards;
    }

}
