package student;

import model.RailroadMapObserver;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class for the Spaces on the Railroad Map
 *
 * @author Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class Space implements model.Space {
    private int row;
    private int column;
    private Set<RailroadMapObserver> observers = new HashSet<>();

    /**
     *
     * @param row
     * @param column
     */
    public Space(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row of the space's location in the map.
     *
     * @return The row of the space's location in the map.
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the space's location in the map.
     *
     * @return The column of the space's location in the map.
     */
    @Override
    public int getCol() {
        return column;
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
    public boolean collocated(model.Space other) {
        return other.getCol() == this.column && other.getRow() == this.row;
    }
}
