package satdoku;

import org.junit.Test;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertTrue;

public class SatGeneratorTest {
    @Test
    public void testGenerate() {
        Board board = new Board();
        SatGenerator generator = new SatGenerator();
        String cnf = generator.generate(board);
        System.out.println(cnf);
    }

    @Test
    public void testGenerate_OnAFullyFilledBoard_Passes() throws TimeoutException, IOException, ParseFormatException, ContradictionException {
        SatGenerator generator = new SatGenerator();
        Board board = TestBoards.getSolution1();
        String cnf = generator.generate(board);
//        System.out.println(cnf);

        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(60); // one minute
        Reader reader = new DimacsReader(solver);
        IProblem problem = reader.parseInstance(new ByteArrayInputStream(cnf.getBytes()));

        PrintWriter out = new PrintWriter(System.out, true);
//        problem.isSatisfiable();
        assertTrue(problem.isSatisfiable());
        reader.decode(problem.model(),out);
        out.flush();
        for(int n: problem.model()) {
            if (n > 0) {
                System.out.println(new Assignment(n).algebraicForm());
            }
        }
    }

    @Test
    public void testGenerate_onAPartialSatisfiableBoard_Passes() throws IOException, ParseFormatException, ContradictionException, TimeoutException {
        SatGenerator generator = new SatGenerator();
        Board board = TestBoards.getBoard1();
        String cnf = generator.generate(board);
        System.out.println(cnf);

        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(60); // one minute
        Reader reader = new DimacsReader(solver);
        IProblem problem = reader.parseInstance(new ByteArrayInputStream(cnf.getBytes()));

        PrintWriter out = new PrintWriter(System.out, true);
//        problem.isSatisfiable();
        assertTrue(problem.isSatisfiable());
        reader.decode(problem.model(),out);
        out.flush();
        for(int n: problem.model()) {
            if (n > 0) {
                System.out.println(new Assignment(n).algebraicForm());
            }
        }
    }
}
