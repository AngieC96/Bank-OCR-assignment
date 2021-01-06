package dssc.assignment.bank.parse;

import dssc.assignment.bank.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseCellTest {

    @ParameterizedTest
    @MethodSource("provideStringsForParsingCells")
     void zeroCell(String valueCell, String expected) {
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
        return Stream.of(
                Arguments.of(zeroAsText, "0"),
                Arguments.of(oneAsText, "1")//,
                //Arguments.of("  ", "2"),
                //Arguments.of("not blank", "3")
        );
    }

    @Test
    void oneCell() {
        String oneAsText =
                        "   " +
                        "  |" +
                        "  |";
        assertEquals("1", new Cell(oneAsText).toString());
    }

    @Test
    void twoCell() {
        String twoAsText =
                        " _ " +
                        " _|" +
                        "|_ ";
        assertEquals("2", new Cell(twoAsText).toString());
    }
}
