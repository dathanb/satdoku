package satdoku;

public class TestBoards {
    public static Board getTestBoard1() {

        Integer[][] solution = new Integer[][]{
                new Integer[]{4, 3, 5, 2, 6, 9, 7, 8, 1},
                new Integer[]{6, 8, 2, 5, 7, 1, 4, 9, 3},
                new Integer[]{1, 9, 7, 8, 3, 4, 5, 6, 2},
                new Integer[]{8, 2, 6, 1, 9, 5, 3, 4, 7},
                new Integer[]{3, 7, 4, 6, 8, 2, 9, 1, 5},
                new Integer[]{9, 5, 1, 7, 4, 3, 6, 2, 8},
                new Integer[]{5, 1, 9, 3, 2, 6, 8, 7, 4},
                new Integer[]{2, 4, 8, 9, 5, 7, 1, 3, 6},
                new Integer[]{7, 6, 3, 4, 1, 8, 2, 5, 9},
        };
        Board board = new Board();
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                board.set(row, col, solution[row][col]);
            }
        }
        return board;
    }
}
