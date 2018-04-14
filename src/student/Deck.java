package student;

import model.Card;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck implements model.Deck{
    private  static  int numCards = 120;
    private  static  int currentCardNum = 120;
    private List<Card> cards = new ArrayList<>();
    public Deck(){
        for (int i = 0; i<numCards;i++){
            student.Card s = new  student.Card();
            Card c = s.getValue();
            cards.add(c);
        }
    }

    @Override
    public void reset() {
        Deck deck =new Deck();
        this.cards = deck.cards;
    }

    @Override
    public Card drawACard() {
        int i = 0;
        while (i<numCards);
            if (cards.get(i)!= Card.NONE){
                cards.remove(i);
                cards.add(Card.NONE);
                currentCardNum--;
                return cards.get(i);
        }
        return Card.NONE;
    }

    @Override
    public int numberOfCardsRemaining() {
        return currentCardNum;
    }
}
