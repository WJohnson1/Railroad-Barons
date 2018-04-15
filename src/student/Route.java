package student;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Route implements model.Route {
    private int firstID;
    private int secondD;
    private Baron owner;
    private HashMap<Integer, Station> stations;

    public Route(int firstID, int secondD, Baron owner, HashMap<Integer, Station> stations) {
        this.firstID = firstID;
        this.secondD = secondD;
        this.owner = owner;
        this.stations = stations;
    }

    /**
     * Returns the {@linkplain Baron} that has claimed this route. Note that
     * this route may be {@linkplain Baron#UNCLAIMED unclaimed}.
     *
     * @return The {@link Baron} that has claimed this route.
     */
    @Override
    public Baron getBaron() {
        return owner;
    }

    /**
     * Returns the {@linkplain Station station} at the beginning of this
     * route. The origin must be directly north of or to the west of the
     * destination.
     *
     * @return The {@link Station} at the beginning of this route.
     */
    @Override
    public model.Station getOrigin() {
        return this.stations.get(this.firstID);
    }

    /**
     * Returns the {@linkplain Station station} at the end of this route. The
     * destination must be directly south of or to the east of the origin.
     *
     * @return The {@link Station} at the end of this route.
     */
    @Override
    public model.Station getDestination() {
        return this.stations.get(this.secondD);
    }

    /**
     * Returns the {@linkplain Orientation orientation} of this route; either
     * {@linkplain Orientation#HORIZONTAL horizontal} or
     * {@linkplain Orientation#VERTICAL vertical}.
     *
     * @return The {@link Orientation} of this route.
     */
    @Override
    public Orientation getOrientation() {
        if (getOrigin().getRow() == getDestination().getRow()){
            return Orientation.HORIZONTAL;
        }
        else{
            return Orientation.VERTICAL;
        }
    }

    /**
     * The set of {@linkplain Track tracks} that make up this route.
     *
     * @return The {@link List} of {@link Track tracks} that make up this
     * route.
     */
    @Override
    public List<model.Track> getTracks() {
        ArrayList<model.Track> tracks = new ArrayList<>();
        if (getOrientation() == Orientation.HORIZONTAL){
            for (int i = 1; i < getLength(); i++){
                tracks.add(new Track(getOrientation(), this.owner, this, getOrigin().getRow(), getOrigin().getCol() + i));
            }
        }
        else{
            for (int i = 1; i < getLength(); i++){
                tracks.add(new Track(getOrientation(), this.owner, this, getOrigin().getRow() + i, getOrigin().getCol()));
            }
        }
        return tracks;
    }

    /**
     * Returns the length of the route, not including the {@linkplain Station
     * stations} at the end points.
     *
     * @return The number of {@link Track Tracks} comprising this route.
     */
    @Override
    public int getLength() {
        if (getOrientation() == Orientation.HORIZONTAL){
            return Math.abs(getOrigin().getCol() - getDestination().getCol()-1);
        }
        else{
            return Math.abs(getOrigin().getRow() - getDestination().getRow()-1);
        }
    }

    /**
     * Returns the number of points that this {@linkplain Route route} is
     * worth according to the following algorithm:
     * <ul>
     * <li>1 - 1 point</li>
     * <li>2 - 2 points</li>
     * <li>3 - 4 points</li>
     * <li>4 - 7 points</li>
     * <li>5 - 10 points</li>
     * <li>6 - 15 points</li>
     * <li>7 (or more) - 5 * (length - 3) points</li>
     * </ul>
     *
     * @return The number of points that this route is worth.
     */
    @Override
    public int getPointValue() {
        if (getLength() < 3){
            return getLength();
        }
        else if (getLength() == 3){
            return getLength() + 1;
        }
        else if (getLength() == 4){
            return getLength() + 3;
        }
        else if (getLength() == 5){
            return getLength() + 5;
        }
        else if (getLength() == 6){
            return getLength() + 9;
        }
        else {
            return 5*(getLength()-3);
        }
    }

    /**
     * Returns true if the route covers the ground at the location of the
     * specified {@linkplain Space space} and false otherwise.
     *
     * @param space The {@link Space} that may be in this route.
     * @return True if the {@link Space Space's} coordinates are a part of
     * this route, and false otherwise.
     */
    @Override
    public boolean includesCoordinate(model.Space space) {
        for (model.Track track : getTracks()){
            if (track.collocated(space)){
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to claim the route on behalf of the specified
     * {@linkplain Baron}. Only unclaimed routes may be claimed.
     *
     * @param claimant The {@link Baron} attempting to claim the route. Must
     *                 not be null or {@link Baron#UNCLAIMED}.
     * @return True if the route was successfully claimed. False otherwise.
     */
    @Override
    public boolean claim(Baron claimant) {
        if (this.owner != Baron.UNCLAIMED){
            return false;
        }
        else{
            this.owner = claimant;
            return true;
        }
    }
}
