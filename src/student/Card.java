package student;

import java.util.concurrent.ThreadLocalRandom;

public class Card {
    private model.Card value;
    public Card(int randomNum){
        switch (randomNum) {
            case 0:
                value = model.Card.WILD;
                break;
            case 1:
                value = model.Card.BLACK;
                break;
            case 2:
                value = model.Card.BLUE;
                break;
            case 3:
                value = model.Card.GREEN;
                break;
            case 4:
                value = model.Card.ORANGE;
                break;
            case 5:
                value = model.Card.PINK;
                break;
            case 6:
                value = model.Card.RED;
                break;
            case 7:
                value = model.Card.WHITE;
                break;
            case 8:
                value = model.Card.YELLOW;
                break;
        }
    }

    public model.Card getValue() {
        return value;
    }
}
