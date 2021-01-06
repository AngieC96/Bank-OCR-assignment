package dssc.assignment.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistancesTest {

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void zeroEightDistance(Cell input1, Cell input2, int expected) {
        assertEquals(expected, input1.distanceFrom(input2));
    }

    private static Stream<Arguments> provideStringsForIsBlank() {
        String zeroAsText =
                        " _ " +
                        "| |" +
                        "|_|";
        Cell zeroCell = new Cell(zeroAsText);
        String eightAsText =
                        " _ " +
                        "|_|" +
                        "|_|";
        Cell eightCell = new Cell(eightAsText);
        return Stream.of(
                Arguments.of(zeroCell, eightCell, 1)
        );
    }

    @Test
    void fiveAlmostFiveDistance() {
        String fiveAsText =
                        " _ " +
                        "|_ " +
                        " _|";
        Cell fiveCell = new Cell(fiveAsText);
        String almostFiveAsText =
                        " _ " +
                        "|  " +
                        " _|";
        Cell almostFiveCell = new Cell(almostFiveAsText);
        assertEquals(1, fiveCell.distanceFrom(almostFiveCell));
    }

    @Test
    void sevenAlmostOneDistance() {
        String sevenAsText =
                        " _ " +
                        "  |" +
                        "  |";
        Cell sevenCell = new Cell(sevenAsText);
        String almostOneAsText =
                        "   " +
                        " _|" +
                        "  |";
        Cell almostOneCell = new Cell(almostOneAsText);
        assertEquals(2, sevenCell.distanceFrom(almostOneCell));
    }

    @Test
    void eightAlmostOneDistance() {
        String eightAsText =
                        " _ " +
                        "|_|" +
                        "|_|";
        Cell eightCell = new Cell(eightAsText);
        String almostOneAsText =
                        "   " +
                        " _|" +
                        "  |";
        Cell almostOneCell = new Cell(almostOneAsText);
        assertEquals(4, eightCell.distanceFrom(almostOneCell));
    }

    @Test
    void eightDistancesFrom0to9() {
        String eightAsText =
                        " _ " +
                        "|_|" +
                        "|_|";
        Cell eightCell = new Cell(eightAsText);
        List<Integer> eightDistances = eightCell.distancesFrom0to9Cells();
        List<Integer> trueEightDistances = Arrays.asList(1, 5, 2, 2, 3, 2, 1, 4, 0, 1);

        assertEquals(trueEightDistances, eightDistances);

    }

    @Test
    void almostEightDistancesFrom0to9() {
        String almostEightAsText =
                        "   " +
                        "|_|" +
                        "|_|";
        Cell almostEightCell = new Cell(almostEightAsText);
        List<Integer> almostEightDistances = almostEightCell.distancesFrom0to9Cells();
        List<Integer> trueAlmostEightDistances = Arrays.asList(2, 4, 3, 3, 2, 3, 2, 5, 1, 2);

        assertEquals(trueAlmostEightDistances, almostEightDistances);

    }
}
