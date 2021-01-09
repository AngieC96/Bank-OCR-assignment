package dssc.assignment.bank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistancesTest {

    @ParameterizedTest
    @MethodSource("provideTwoCellsAndExpected")
    void distanceBetweenTwoCells(Cell input1, Cell input2, int expected) {
        assertEquals(expected, input1.distanceFrom(input2));
    }

    private static Stream<Arguments> provideTwoCellsAndExpected() {
        String zeroAsText =
                        " _ " +
                        "| |" +
                        "|_|";
        Cell zeroCell = new Cell(zeroAsText);
        String almostOneAsText =
                        "   " +
                        " _|" +
                        "  |";
        Cell almostOneCell = new Cell(almostOneAsText);
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
        String sevenAsText =
                        " _ " +
                        "  |" +
                        "  |";
        Cell sevenCell = new Cell(sevenAsText);
        String eightAsText =
                        " _ " +
                        "|_|" +
                        "|_|";
        Cell eightCell = new Cell(eightAsText);

        return Stream.of(
                Arguments.of(zeroCell, eightCell, 1),
                Arguments.of(fiveCell, almostFiveCell, 1),
                Arguments.of(sevenCell, almostOneCell, 2),
                Arguments.of(eightCell, almostOneCell, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideACellAndExpectedList")
    void cellDistancesFrom0to9(String cellAsText, List<Integer> trueDistances) {
        Cell cell = new Cell(cellAsText);
        List<Integer> cellDistances = cell.distancesFrom0to9Cells();
        assertEquals(trueDistances, cellDistances);
    }

    private static Stream<Arguments> provideACellAndExpectedList() {
        String eightAsText =
                        " _ " +
                        "|_|" +
                        "|_|";
        String almostEightAsText =
                        "   " +
                        "|_|" +
                        "|_|";
        return Stream.of(
                Arguments.of(eightAsText, Arrays.asList(1, 5, 2, 2, 3, 2, 1, 4, 0, 1)),
                Arguments.of(almostEightAsText, Arrays.asList(2, 4, 3, 3, 2, 3, 2, 5, 1, 2))
        );
    }
}
