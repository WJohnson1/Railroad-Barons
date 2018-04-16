package student;

import model.Baron;
import model.Orientation;
import model.Space;

/**
 * The Class for track that represents the individual spaces
 * between two stations to form a route
 *
 * @author Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class Track extends student.Space implements model.Track{
    private Orientation orientation;
    private Baron owner;
    private Route route;

    /**
     * Constructor for the Track
     * @param orientation orientation of the track
     * @param owner owner of the track
     * @param route route that the track belongs to
     * @param row the row of the track
     * @param col the column of the track
     */
    public Track(Orientation orientation, Baron owner, Route route, int row, int col) {
        super(row,col);
        this.orientation = orientation;
        this.owner = owner;
        this.route = route;
    }

    /**
     * Returns the orientation of the track; either
     * {@linkplain Orientation#HORIZONTAL horizontal} or
     * {@linkplain Orientation#VERTICAL vertical}. This is based on the
     * {@linkplain Orientation orientation} of the {@linkplain Route route}
     * that contains the track.
     *
     * @return The {@link Orientation} of the {@link Track}; this is the same
     * as the {@link Orientation} of the {@link Route} that contains the
     * track.
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Returns the current {@link Baron owner} of this track, either
     * {@linkplain Baron#UNCLAIMED unclaimed} if the track has not been
     * claimed, or the {@linkplain Baron owner} that corresponds with the
     * color of the player that successfully claimed the
     * {@linkplain Route route} of which this track is a part.
     *
     * @return The {@link Baron} that has claimed the route of which this
     * track is a part.
     */
    @Override
    public Baron getBaron() {
        return this.owner;
    }

    /**
     * Returns the {@linkplain Route route} of which this
     * {@linkplain Track track} is a part.
     *
     * @return The {@link Route} that contains this track.
     */
    @Override
    public model.Route getRoute() {
        return this.route;
    }

    /**
     * Returns the row of the space's location in the map.
     *
     * @return The row of the space's location in the map.
     */
    @Override
    public int getRow() {
        return super.getRow();
    }

    /**
     * Returns the column of the space's location in the map.
     *
     * @return The column of the space's location in the map.
     */
    @Override
    public int getCol() {
        return super.getCol();
    }

    /**
     * Returns true if the other space is occupying the same physical location
     * in the map as this space.
     *
     * @param other The other space to which this space is being compared for
     *              collocation.
     * @return True if the two spaces are in the same physical location (row
     * and column) in the map; false otherwise.
     */
    @Override
    public boolean collocated(Space other) {
        return other.getCol() == super.getCol() && other.getRow() == super.getRow();
    }
}
