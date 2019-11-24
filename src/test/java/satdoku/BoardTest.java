package satdoku;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class BoardTest {
    @Test
    public void boardAlwaysMatchesItself() {
        Board board = new Board();
        assertTrue(board.matches(board));
        board.set(0, 0, 1);
        assertTrue(board.matches(board));
        board.set(0, 5, 9);
        assertTrue(board.matches(board));
    }

    @Test
    public void testMatches_FailureCase() {
        Board board1 = new Board();
        board1.set(0, 0, 1);
        Board board2 = new Board();
        board2.set(0, 0, 2);

        assertFalse(board1.matches(board2));
    }

    @Test
    public void matches_whenBoardsOnlyDifferOnNonFilledInSquares_ReturnsTrue() {
        Board board1 = new Board();
        board1.set(0, 0, 1);
        Board board2 = new Board();
        board2.set(1, 1, 2);
        assertTrue(board1.matches(board2));
    }

    @Test
    public void validate_withDuplicatesInARow_returnsFalse() {
        Board board1 = new Board();
        board1.set(0, 0, 1);
        board1.set(0, 8, 1);
        assertFalse(board1.validate());
    }

    @Test
    public void validate_withDuplicatesInACol_returnsFalse() {
        Board board1 = new Board();
        board1.set(0, 0, 1);
        board1.set(8, 0, 1);
        assertFalse(board1.validate());
    }

    @Test
    public void validate_withDuplicatesInAMiniSquare_returnsFalse() {
        Board board = new Board();
        // duplicates in the first minisquare
        board.set(0, 0, 1);
        board.set(0, 1, 1);
        assertFalse(board.validate());

        // duplicates in the second minisquare
        board = new Board();
        board.set(0, 3, 1);
        board.set(0, 4, 1);
        assertFalse(board.validate());

        // duplicates in the third minisquare
        board = new Board();
        board.set(0, 6, 1);
        board.set(0, 7, 1);
        assertFalse(board.validate());

        // duplicates in the fourth minisquare
        board = new Board();
        board.set(3, 0, 1);
        board.set(3, 1, 1);
        assertFalse(board.validate());

        // duplicates in the fifth minisquare
        board = new Board();
        board.set(3, 3, 1);
        board.set(3, 4, 1);
        assertFalse(board.validate());

        // duplicates in the sixth minisquare
        board = new Board();
        board.set(3, 6, 1);
        board.set(3, 7, 1);
        assertFalse(board.validate());

        // duplicates in the seventh minisquare
        board = new Board();
        board.set(6, 0, 1);
        board.set(6, 1, 1);
        assertFalse(board.validate());

        // duplicates in the eighth minisquare
        board = new Board();
        board.set(6, 3, 1);
        board.set(6, 4, 1);
        assertFalse(board.validate());

        // duplicates in the ninth minisquare
        board = new Board();
        board.set(6, 6, 1);
        board.set(6, 7, 1);
        assertFalse(board.validate());
    }

    @Test
    public void test_validSolution_validates() {
        assertTrue(TestBoards.getSolution1().validate());
    }
}
