package satdoku;

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
        this.n = n-1;
    }

    public Assignment(Coordinate coord, int n) {
        this.row = coord.row;
        this.col = coord.column;
        this.n = n-1;
    }

    public Assignment(int encodedName) {
        encodedName -= 1;
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
        code += 1;
        return code;
    }

    @Override
    public String toString() {
        return Integer.toString(encode());
//        return algebraicForm();
    }

    public String algebraicForm() {
        return String.format("A(%d,%d,%d)", row, col, n+1);
    }
}
