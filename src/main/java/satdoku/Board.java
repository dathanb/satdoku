package satdoku;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A Sudoku board, where each position may or may not be filled in.
 */
public class Board {
    private final Integer[][] board = new Integer[][]{
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
            new Integer[]{null, null, null, null, null, null, null, null, null},
    };

    public Board() {
    }

    public void set(int row, int col, Integer value) {
        board[row][col] = value;
    }

    public void set(Coordinate coordinate, Integer value) {
        set(coordinate.row, coordinate.column, value);
    }

    public Integer get(int row, int col) {
        return board[row][col];
    }

    public Integer get(Coordinate coordinate) {
        return get(coordinate.row, coordinate.column);
    }

    /**
     * Check whether all filled-in positions match between two boards.
     *
     * @param other
     * @return
     */
    public boolean matches(Board other) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != null && other.board[row][col] != null && !Objects.equals(board[row][col], other.board[row][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validate that the board state does not violate any Sudoku constraints;
     *
     * @return
     */
    public boolean validate() {
        for (int i = 0; i < 9; i++) {
            if (!(validate(getRow(i)) && validate(getCol(i)) && validate(getMiniSquare(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean validate(Integer[] nums) {
        Integer[] sortedNums = new Integer[nums.length];
        System.arraycopy(nums, 0, sortedNums, 0, nums.length);
        Arrays.sort(sortedNums, (a, b) -> {
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return -1;
            } else if (b == null) {
                return 1;
            } else if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            } else {
                return 0;
            }
        });
        for (int i = 1; i < nums.length; i++) {
            if (Objects.equals(sortedNums[i - 1], sortedNums[i])) {
                return false;
            }
        }
        return true;
    }

    private Integer[] getRow(int row) {
        return board[row];
    }

    private Integer[] getCol(int col) {
        Integer[] colNums = new Integer[9];
        for (int i = 0; i < 9; i++) {
            colNums[i] = board[i][col];
        }
        return colNums;
    }

    private Integer[] getMiniSquare(int n) {
        return miniSquareCoordinates(n)
                .map(coord -> board[coord.row][coord.column])
                .toArray(Integer[]::new);
    }

    public static Stream<Coordinate> allCoordinates() {
        return Arrays.stream(new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(0, 1),
        });
    }

    public static Stream<Coordinate> rowCoordinates(int row) {
        return IntStream.range(0, 9)
                .mapToObj(col -> new Coordinate(row, col));
    }

    public static Stream<Coordinate> colCoordinates(int col) {
        return IntStream.range(0, 9)
                .mapToObj(row -> new Coordinate(row, col));
    }

    public static Stream<Coordinate> miniSquareCoordinates(int square) {
        int miniSquareY  = square / 3;
        int startingRow = 3*miniSquareY;
        int miniSquareX = square % 3;
        int startingCol = 3*miniSquareX;

        return IntStream.range(0, 3)
                .boxed()
                .flatMap(rowOffset -> IntStream.range(0, 3).mapToObj(colOffset -> new Coordinate(startingRow + rowOffset, startingCol+colOffset)));
    }
}
