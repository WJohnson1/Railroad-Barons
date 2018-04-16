package student;

import model.Space;

public class Station extends student.Space implements model.Station{ //extends student.Space{
    private String name;
    private int id;

    public Station(String name, int row, int col, int id) {
        super(row,col);
        this.name = name;
    }

    public int ID(){
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getRow() {
        return super.getRow();
    }

    @Override
    public int getCol() {
        return super.getCol();
    }

    @Override
    public boolean collocated(Space other) {
        return other.getRow() == this.getRow() && other.getCol() == this.getCol();
    }
}
