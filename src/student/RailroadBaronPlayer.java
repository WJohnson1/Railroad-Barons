package student;

import model.*;
import model.Card;
import model.Pair;
import model.Route;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RailroadBaronPlayer implements Player {
    private Baron baron;
    private model.Pair lastTwoCards = new student.Pair(model.Card.NONE, model.Card.NONE);
    private model.Card[] hand;
    private  int score = 0;
    private int trainPieces = 45;
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

    }

    @Override
    public void removePlayerObserver(PlayerObserver observer) {

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
        int count = 0;
        for (int i = 0; i<hand.length;i++){
            if (hand[i] == card){
                count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfPieces() {
        return trainPieces;
    }

    @Override
    public boolean canClaimRoute(Route route) {
        return false;
    }

    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {
        claimedRoutes.add(route);
        route.claim(this.baron);
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
        return false;
    }


}
