package student;

import model.*;
import model.Card;
import model.Pair;
import model.Route;

import java.util.*;

public class RailroadBaronPlayer implements Player {
    private Baron baron;
    private model.Pair lastTwoCards = new student.Pair(model.Card.NONE, model.Card.NONE);
    private model.Card[] hand = new model.Card[4];
    private  int score = 0;
    private int trainPieces = 45;
    private boolean alreadyClaimed = false;
    private Set<PlayerObserver> observers = new HashSet<>();
    private  Collection<model.Route> claimedRoutes = Collections.emptyList();
    public  RailroadBaronPlayer(int i){
        switch (i){
            case 0:
                this.baron = Baron.BLUE;
                break;
            case 1:
                this.baron = Baron.GREEN;
                break;
            case 2:
                this.baron = Baron.RED;
                break;
            case 3:
                this.baron = Baron.YELLOW;
                break;
        }
    }
    @Override
    public void reset(model.Card... dealt) {
        trainPieces = 45;
        hand = dealt;
        score = 0;
        claimedRoutes = Collections.emptyList();
        lastTwoCards = new student.Pair(Card.NONE,Card.NONE);
        //dealt[0].g
    }

    @Override
    public void addPlayerObserver(PlayerObserver observer) {
        observers.add(observer);

    }

    @Override
    public void removePlayerObserver(PlayerObserver observer) {
        observers.remove(observer);
    }

    @Override
    public Baron getBaron() {
        return this.baron;
    }

    @Override
    public void startTurn(Pair dealt) {
        this.lastTwoCards = dealt;

    }

    @Override
    public Pair getLastTwoCards() {
        return lastTwoCards;
    }

    @Override
    public int countCardsInHand(model.Card card) {
        if (card != Card.NONE) {
            int count = 0;
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] == card) {
                    count++;
                }
            }
            return count;
        }
        else{
            return 0;
        }
    }

    @Override
    public int getNumberOfPieces() {
        return trainPieces;
    }

    @Override
    public boolean canClaimRoute(Route route) {
        if (route.getBaron() == Baron.UNCLAIMED){
            if (!alreadyClaimed){

                if (this.countCardsInHand(Card.BLACK) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.BLUE) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.GREEN) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.ORANGE) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.PINK) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.RED) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.WHITE) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.YELLOW) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
                else if (this.countCardsInHand(Card.BACK) + this.countCardsInHand(Card.WILD)>=route.getLength()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {
        boolean completed = false;
        int w = 0;
        for (Card c: hand){
            if (this.countCardsInHand(c)>= route.getLength() & !c.equals(Card.WILD)){
                for (int i = 0; i<hand.length;i++){
                    if (hand[i].equals(c)){
                        hand[i] = Card.NONE;
                    }
                }
                completed = true;
            }
            else if((this.countCardsInHand(c) + this.countCardsInHand(Card.WILD))>= route.getLength()){
                for (int i = 0; i<hand.length;i++){
                    if (hand[i].equals(c)){
                        hand[i] = Card.NONE;
                    }
                    else if (hand[i].equals(Card.WILD) && w <1){
                        hand[i] = Card.NONE;
                        w++;
                    }
                }
                completed = true;
            }
        }
        if (!completed){
            throw new RailroadBaronsException("Route cannot be claimed");
        }
        this.trainPieces = this.trainPieces-route.getLength();
        claimedRoutes.add(route);
        route.claim(this.baron);
        alreadyClaimed = true;
        score+=route.getPointValue();
    }

    @Override
    public Collection<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean canContinuePlaying(int shortestUnclaimedRoute) {
        for (Card c: hand){
            if ((this.countCardsInHand(c) + this.countCardsInHand(Card.WILD))>=shortestUnclaimedRoute){
                if (trainPieces >= shortestUnclaimedRoute){
                    return true;
                }
            }
        }
        return false;
    }

    public void changeAlreadyClaimed() {
         alreadyClaimed = !alreadyClaimed;
    }
}
