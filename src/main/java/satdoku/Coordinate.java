package satdoku;

/**
 * A position within a Sudoku board.
 */
public class Coordinate {
    public final int row;
    public final int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
