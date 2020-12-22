package dssc.assignment.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistancesTest {

    @Test
    void zeroEightDistance() {
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
        assertEquals(1, zeroCell.distanceCell(eightCell));
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
        assertEquals(1, fiveCell.distanceCell(almostFiveCell));
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
        assertEquals(2, sevenCell.distanceCell(almostOneCell));
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
        assertEquals(4, eightCell.distanceCell(almostOneCell));
    }
}
