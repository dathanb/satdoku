package satdoku;

public class TestBoards {
    public static Board getBoard1() {
        Integer[][] board = new Integer[][]{
                new Integer[]{null, null, null, 2, 6, null, 7, null, 1},
                new Integer[]{6, 8, null, null, 7, null, null, 9, null},
                new Integer[]{1, 9, null, null, null, 4, 5, null, null},
                new Integer[]{8, 2, null, 1, null, null, null, 4, null},
                new Integer[]{null, null, 4, 6, null, 2, 9, null, null},
                new Integer[]{null, 5, null, null, null, 3, null, 2, 8},
                new Integer[]{null, null, 9, 3, null, null, null, 7, 4},
                new Integer[]{null, 4, null, null, 5, null, null, 3, 6},
                new Integer[]{7, null, 3, null, 1, 8, null, null, null}
        };
        return new Board(board);
    }

    public static Board getSolution1() {
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
        return new Board(solution);
    }
}
