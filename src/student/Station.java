package student;

import model.Space;

/**
 * The Class for the Station that represents the individual spaces
 * on the map that are stations
 *
 * @author Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
public class Station extends student.Space implements model.Station{ //extends student.Space{
    private String name;
    private int id;

    /**
     * Contructor for Station
     * @param name Name of Station
     * @param row Row of the Station
     * @param col Column of the Station
     * @param id Id of the Station
     */
    public Station(String name, int row, int col, int id) {
        super(row,col);
        this.name = name;
    }

    /**
     * Returns the ID of the station
     * @return the ID of the station
     */
    public int ID(){
        return this.id;
    }

    /**
     * Returns the name of the station
     * @return the name of the station
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * The row of the station
     * @return the row of the station
     */
    @Override
    public int getRow() {
        return super.getRow();
    }

    /**
     * The column of the station
     * @return the column of the station
     */
    @Override
    public int getCol() {
        return super.getCol();
    }

    /**
     * Compares two spaces and returns true if they are equivalent
     * @param other The other space to which this space is being compared for
     *              collocation.
     *
     * @return true or false
     */
    @Override
    public boolean collocated(Space other) {
        return other.getRow() == this.getRow() && other.getCol() == this.getCol();
    }
}
