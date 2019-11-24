package satdoku;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Generates a SAT statement in CNF form for a Sudoku board.
 */
public class SatGenerator {
    public static final int NUM_ROWS = 9;
    public static final int NUM_COLS = 9;
    public static final int NUM_CELLS = NUM_ROWS * NUM_COLS;
    public static final int NUM_VARIABLES = 9 * NUM_CELLS;

    public String generate(Board board) {
        List<String> baseConstraints = generateBaseConstraints();//.stream().limit(37*19).collect(Collectors.toList());
        List<String> boardConstraints = generateBoardConstraints(board);
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("p cnf %d %d\n", NUM_VARIABLES, baseConstraints.size() + boardConstraints.size()));
        baseConstraints.forEach(s -> builder.append(s).append("    0\n"));
        boardConstraints.forEach(s -> builder.append(s).append("    0\n"));
        return builder.toString();
    }

    /**
     * Generate all the basic Sudoku constraints in CNF form.
     */
    private List<String> generateBaseConstraints() {
        List<String> constraints = new ArrayList<>();
        Board.allCoordinates().forEach(coord -> constraints.addAll(generateCellConstraints(coord)));
        for (int i = 0; i < 9; i++) {
            constraints.addAll(generateRowConstraints(i));
            constraints.addAll(generateColumnConstraints(i));
            constraints.addAll(generateMiniSquareConstraints(i));
        }
        return constraints;
    }


    private List<String> generateBoardConstraints(Board board) {
        return Board.allCoordinates()
                .filter(coord -> board.get(coord) != null)
                .map(coord -> new Assignment(coord, board.get(coord)))
                .map(Assignment::toString)
                .collect(Collectors.toList());
    }

    private List<String> generateCellConstraints(Coordinate coord) {
        return writeConstraints(Board.numbers()
                .boxed()
                .map(n -> new Assignment(coord,n))
                .toArray(Assignment[]::new));
    }

    private List<String> generateRowConstraints(int row) {
        return generateConstraints(() -> Board.rowCoordinates(row));
    }

    private List<String> generateColumnConstraints(int col) {
        return generateConstraints(() -> Board.colCoordinates(col));
    }

    private List<String> generateMiniSquareConstraints(int square) {
        return generateConstraints(() -> Board.miniSquareCoordinates(square));
    }

    private List<String> generateConstraints(Supplier<Stream<Coordinate>> cells) {
        final List<String> constraints = new ArrayList<>();
        Board.numbers()
                .boxed()
                .forEach(num -> {
                            Assignment[] assignments = cells.get()
                                    .map(coord -> new Assignment(coord, num))
                                    .toArray(Assignment[]::new);
                            constraints.addAll(writeConstraints(assignments));
                        }
                );
        return constraints;
    }

    private List<String> writeConstraints(Assignment[] assignments) {
        List<String> constraints = new ArrayList<>();
        // write the proposition that at least one of these assignments is true
        StringBuilder positiveAssignmentBuilder = new StringBuilder();
        for (Assignment assignment: assignments) {
            positiveAssignmentBuilder.append(String.format("%-10s", assignment));
        }
        constraints.add(positiveAssignmentBuilder.toString().trim());

        // now write the no-two-assignments-are-both-true proposition
        for (int i=0; i<9; i++) {
            for (int j = i + 1; j < 9; j++) {
                constraints.add(String.format("-%-10s-%-10s", assignments[i], assignments[j]).trim());
            }
        }
        return constraints;
    }
}

