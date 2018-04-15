package student;

import model.RailroadMapObserver;
import model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RailroadMap implements model.RailroadMap {
    private int rows;
    private int columns;
    private student.Space[][] Sections;
    private ArrayList<RailroadMapObserver> observers = new ArrayList<>();
    private ArrayList<model.Route> routes = new ArrayList<>();


    public RailroadMap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.Sections = new Space[rows][columns];
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                Sections[row][column] = new student.Space(row, column);
            }
        }
    }

    /**
     * Adds the specified {@linkplain RailroadMapObserver observer} to the
     * map. The observer will be notified of significant events involving this
     * map such as when a {@linkplain Route route} has been claimed by a
     * {@linkplain Baron}.
     *
     * @param observer The {@link RailroadMapObserver} being added to the map.
     */
    @Override
    public void addObserver(RailroadMapObserver observer) {
        //observer.routeClaimed(this,routes.get(routes.size()-1));
        this.observers.add(observer);
    }

    /**
     * Removes the specified {@linkplain RailroadMapObserver observer} from
     * the map. The observer will no longer be notified of significant events
     * involving this map.
     *
     * @param observer The observer to remove from the collection of
     *                 registered observers that will be notified of
     *                 significant events involving this map.
     */
    @Override
    public void removeObserver(RailroadMapObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * Returns the number of rows in the map. This is determined by the
     * location of the southernmost {@linkplain Station station} on the map.
     *
     * @return The number of rows in the map.
     */
    @Override
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the map. This is determined by the
     * location of the easternmost {@linkplain Station station} on the map.
     *
     * @return The number of columns in the map.
     */
    @Override
    public int getCols() {
        return this.columns;
    }

    /**
     * Returns the {@linkplain Space space} located at the specified
     * coordinates.
     *
     * @param row The row of the desired {@link Space}.
     * @param col The column of the desired {@link Space}.
     * @return The {@link Space} at the specified location, or null if the
     * location doesn't exist on this map.
     */
    @Override
    public model.Space getSpace(int row, int col) {
        return Sections[row][col];
    }

    /**
     * Returns the {@linkplain Route route} that contains the
     * {@link Track track} at the specified location (if such a route exists}.
     *
     * @param row The row of the location of one of the {@link Track tracks}
     *            in the route.
     * @param col The column of the location of one of the
     *            {@link Track tracks} in the route.
     * @return The {@link Route} that contains the {@link Track} at the
     * specified location, or null if there is no such {@link Route}.
     */
    @Override
    public model.Route getRoute(int row, int col) {
        System.out.println(routes.size());
        return ((model.Track)Sections[row][col]).getRoute();
    }

    /**
     * Called to update the {@linkplain RailroadMap map} when a
     * {@linkplain Baron} has claimed a {@linkplain Route route}.
     *
     * @param route The {@link Route} that has been claimed.
     */
    @Override
    public void routeClaimed(model.Route route) {
        model.Station origin = route.getOrigin();
        model.Station destination =  route.getDestination();
        Sections[origin.getRow()][origin.getCol()] = (student.Space) origin;
        Sections[destination.getRow()][destination.getCol()] = (student.Space) destination;
        for (model.Track track : route.getTracks() ){
            Sections[track.getRow()][track.getCol()] = (student.Space) track;
        }
        routes.add(route);
        for (RailroadMapObserver observer: observers){
            observer.routeClaimed(this,route);
        }
    }

    /**
     * Returns the length of the shortest unclaimed {@linkplain Route route}
     * in the map.
     *
     * @return The length of the shortest unclaimed {@link Route}.
     */
    @Override
    public int getLengthOfShortestUnclaimedRoute() {
        int smallest = 0;
        for (model.Route route : this.routes){
            if (smallest == 0){
                smallest = route.getLength();
            }
            else if (route.getLength() < smallest){
                smallest = route.getLength();
            }
        }
        return smallest;
    }

    /**
     * Returns all of the {@link Route Routes} in this map.
     *
     * @return A {@link Collection} of all of the {@link Route Routes} in this
     * RailroadMap.
     */
    @Override
    public Collection<model.Route> getRoutes() {
        return this.routes;
    }
}
