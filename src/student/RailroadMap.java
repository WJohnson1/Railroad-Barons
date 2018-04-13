package student;

import model.RailroadMapObserver;
import model.Route;
import model.Space;

import java.util.Collection;

public class RailroadMap implements model.RailroadMap {
    @Override
    public void addObserver(RailroadMapObserver observer) {

    }

    @Override
    public void removeObserver(RailroadMapObserver observer) {

    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public int getCols() {
        return 0;
    }

    @Override
    public Space getSpace(int row, int col) {
        return null;
    }

    @Override
    public Route getRoute(int row, int col) {
        return null;
    }

    @Override
    public void routeClaimed(Route route) {

    }

    @Override
    public int getLengthOfShortestUnclaimedRoute() {
        return 0;
    }

    @Override
    public Collection<Route> getRoutes() {
        return null;
    }
}
