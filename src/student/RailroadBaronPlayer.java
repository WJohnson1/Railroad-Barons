package student;

import model.*;
import model.Route;

import java.util.Collection;

public class RailroadBaronPlayer implements Player {
    private Baron baron;
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
    public void reset(Card... dealt) {

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

    }

    @Override
    public Pair getLastTwoCards() {
        return null;
    }

    @Override
    public int countCardsInHand(Card card) {
        return 0;
    }

    @Override
    public int getNumberOfPieces() {
        return 0;
    }

    @Override
    public boolean canClaimRoute(model.Route route) {
        return false;
    }

    @Override
    public void claimRoute(model.Route route) throws RailroadBaronsException {

    }

    @Override
    public Collection<Route> getClaimedRoutes() {
        return null;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean canContinuePlaying(int shortestUnclaimedRoute) {
        return false;
    }

}
