package dssc.assignment.bank.parse;

import dssc.assignment.bank.Cell;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseCellTest {

    @ParameterizedTest
    @MethodSource("provideStringsForParsingCells")
     void parseCellInto(String valueCell, String expected) {
        assertEquals(expected, new Cell(valueCell).toString());
    }

    private static Stream<Arguments> provideStringsForParsingCells() {
        String zeroAsText =
                        " _ " +
                        "| |" +
                        "|_|";
        String oneAsText =
                        "   " +
                        "  |" +
                        "  |";
        String twoAsText =
                        " _ " +
                        " _|" +
                        "|_ ";
        String threeAsText =
                        " _ " +
                        " _|" +
                        " _|";
        String fourAsText =
                        "   " +
                        "|_|" +
                        "  |";
        String fiveAsText =
                        " _ " +
                        "|_ " +
                        " _|";
        String sixAsText =
                        " _ " +
                        "|_ " +
                        "|_|";
        String sevenAsText =
                        " _ " +
                        "  |" +
                        "  |";
        String eightAsText =
                        " _ " +
                        "|_|" +
                        "|_|";
        String nineAsText =
                        " _ " +
                        "|_|" +
                        " _|";
        return Stream.of(
                Arguments.of(zeroAsText,  "0"),
                Arguments.of(oneAsText,   "1"),
                Arguments.of(twoAsText,   "2"),
                Arguments.of(threeAsText, "3"),
                Arguments.of(fourAsText,  "4"),
                Arguments.of(fiveAsText,  "5"),
                Arguments.of(sixAsText,   "6"),
                Arguments.of(sevenAsText, "7"),
                Arguments.of(eightAsText, "8"),
                Arguments.of(nineAsText,  "9")
        );
    }
}
