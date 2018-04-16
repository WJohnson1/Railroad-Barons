package student;

/**
 * This class is for an individual Card in a deck.
 * @authors Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class Card {
    private model.Card value;

    /**
     * Constructor of the Card
     * @param randomNum value that determines value of the card
     */
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

    /**
     * Gets the value of the card
     * @return the value of the card
     */
    public model.Card getValue() {
        return value;
    }
}
