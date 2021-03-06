package student;

import model.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class represents the Deck object where players gets extra cards from in every start of a turn.
 * @authors Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class Deck implements model.Deck{
    private  static  int numCards = 200;
    private  static  int currentCardNum = 200;
    private List<Card> cards = new ArrayList<>();

    /**
     * Constructor for Deck, which creates a new deck of cards
     */
    public Deck(){
        for (int i = 0; i<numCards;i++){
            student.Card s = new  student.Card(i%9);
            Card c = s.getValue();
            cards.add(c);
        }
        Collections.shuffle(cards);
    }

    /**
     * Resets the {@linkplain Deck deck} to its starting state. Restores any
     * {@linkplain Card cards} that were drawn and shuffles the deck.
     */
    @Override
    public void reset() {
        Deck deck =new Deck();
        this.cards = deck.cards;
        currentCardNum = numCards;
    }

    /**
     * Draws the next {@linkplain Card card} from the "top" of the deck.
     *
     * @return The next {@link Card}, unless the deck is empty, in which case
     * this should return {@link Card#NONE}.
     */
    @Override
    public Card drawACard() {
        int i = 0;
        while (i<numCards){
            if (cards.get(i)!= Card.NONE) {
                cards.remove(i);
                cards.add(Card.NONE);
                currentCardNum--;
                return cards.get(i);
            }
            i++;
        }
        return Card.NONE;
    }

    /**
     * Returns the number of {@link Card cards} that have yet to be drawn.
     *
     * @return The number of {@link Card cards} that have yet to be drawn.
     */
    @Override
    public int numberOfCardsRemaining() {
        return currentCardNum;
    }
}
