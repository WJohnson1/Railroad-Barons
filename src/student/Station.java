package student;

import model.Space;

public class Station implements model.Station {
    private String name;
    private int row;
    private int col;

    public Station(String name, int row, int col) {
        this.name = name;
        this.row = row;
        this.col = col;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public boolean collocated(Space other) {
        return other.getRow() == this.row && other.getCol() == this.col;
    }
}
