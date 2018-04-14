package student;

import model.Card;

public class Pair implements model.Pair{
    private  Card firstCard;
    private  Card secondCard;
    public  Pair (Card first, Card second){
        firstCard = first;
        secondCard = second;
    }

    @Override
    public Card getFirstCard() {
        return this.firstCard;
    }

    @Override
    public Card getSecondCard() {
        return this.secondCard;
    }
}
