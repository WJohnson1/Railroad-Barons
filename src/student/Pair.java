package student;

import model.Card;

public class Pair implements model.Pair{
    private  Card firstCard;
    private  Card secondCard;

    /**
     * The constructor for a pair of cards
     * @param first the first card
     * @param second the second card
     */
    public  Pair (Card first, Card second){
        firstCard = first;
        secondCard = second;
    }

    /**
     * Returns the first {@linkplain Card card} in the pair. Note that, if the
     * game deck is empty, the value of this card may be {@link Card#NONE}.
     *
     * @return The first {@link Card} in the pair.
     */
    @Override
    public Card getFirstCard() {
        return this.firstCard;
    }

    /**
     * Returns the second {@linkplain Card card} in the pair. if the
     * game deck is empty, the value of this card may be {@link Card#NONE}.
     *
     * @return The second {@link Card} in the pair.
     */
    @Override
    public Card getSecondCard() {
        return this.secondCard;
    }
}
