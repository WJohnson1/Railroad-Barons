package student;

import model.Card;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

public class Deck implements model.Deck{
    private  static  int numCards = 120;
    private Collection<Card> cards;
    public Deck(){
        for (int i = 0; i<numCards;i++){
            Card c;
            int randomNum = ThreadLocalRandom.current().nextInt(0, 11);
            switch (randomNum){
                case 0:
                    c = Card.NONE;
                    cards.add(c);
                    break;
                case 1:
                    c = Card.WILD;
                    cards.add(c);
                    break;
                case 2:
                    c = Card.BACK;
                    cards.add(c);
                    break;
                case 3:
                    c = Card.BLACK;
                    cards.add(c);
                    break;
                case 4:
                    c = Card.BLUE;
                    cards.add(c);
                    break;
                case 5:
                    c = Card.GREEN;
                    cards.add(c);
                    break;
                case 6:
                    c = Card.ORANGE;
                    cards.add(c);
                    break;
                case 7:
                    c = Card.PINK;
                    cards.add(c);
                    break;
                case 8:
                    c = Card.RED;
                    cards.add(c);
                    break;
                case 9:
                    c = Card.WHITE;
                    cards.add(c);
                    break;
                case 10:
                    c = Card.YELLOW;
                    cards.add(c);
                    break;
            }
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public Card drawACard() {
        Object[] o = cards.toArray();
        Card card = (Card)o[0];
        cards.remove(o[0]);
        numCards = numCards -1;
        return card;
    }

    @Override
    public int numberOfCardsRemaining() {
        return numCards;
    }
}
