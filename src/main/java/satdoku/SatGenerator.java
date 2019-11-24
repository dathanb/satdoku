package satdoku;

/**
 * Generates a SAT statement in CNF form for a Sudoku board.
 */
public class SatGenerator {
    public String generate(Board board) {
        StringBuilder builder = new StringBuilder();
        generateBaseConstraints(builder);
        return builder.toString();
    }

    /**
     * Generate all the basic Sudoku constraints in CNF form.
     */
    private void generateBaseConstraints(StringBuilder builder) {
        for (int i = 0; i < 9; i++) {
            generateRowConstraints(i, builder);
            generateColumnConstraints(i, builder);
            generateMiniSquareConstraints(i, builder);
        }
    }

    private void generateRowConstraints(int row, StringBuilder output) {
        for (int n = 0; n < 9; n++) {
            Assignment[] rowAssignments = new Assignment[]{
                    new Assignment(row,0, n),
                    new Assignment(row,1, n),
                    new Assignment(row,2, n),
                    new Assignment(row,3, n),
                    new Assignment(row,4, n),
                    new Assignment(row,5, n),
                    new Assignment(row,6, n),
                    new Assignment(row,7, n),
                    new Assignment(row,8, n),
            };
        }
    }

    private void generateColumnConstraints(int col, StringBuilder output) {
    }

    private void generateMiniSquareConstraints(int square, StringBuilder output) {
    }
}

/**
 * Given row, column, and N, represents a boolean proposition that the cell at `row,column` is assigned the value N.
 * <p>
 * Also responsible for encoding and decoding that proposition into a densely-packed, ordinal variable space.
 */
class Assignment {
    private final int row;
    private final int col;
    private final int n;

    public Assignment(int row, int col, int n) {
        this.row = row;
        this.col = col;
        this.n = n;
    }

    public Assignment(int encodedName) {
        this.n = encodedName % 9;
        encodedName /= 9;
        this.col = encodedName % 9;
        encodedName /= 9;
        this.row = encodedName % 9;
    }

    public int encode() {
        int code = row;
        code = code * 9 + col;
        code = code * 9 + n;
        return code;
    }
}
