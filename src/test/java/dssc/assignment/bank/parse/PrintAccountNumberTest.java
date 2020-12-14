package dssc.assignment.bank.parse;

import dssc.assignment.bank.AccountNumber;
import dssc.assignment.bank.Entry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrintAccountNumberTest {

    @Test
    void allZerosEntry() {
        Entry entry = new Entry(" _  _  _  _  _  _  _  _  _ ","| || || || || || || || || |","|_||_||_||_||_||_||_||_||_|");
        assertEquals("000000000", new AccountNumber(entry).toString());
    }

    @Test
    void allOnesEntry() {
        Entry entry = new Entry("                           ", "  |  |  |  |  |  |  |  |  |", "  |  |  |  |  |  |  |  |  |");
        assertEquals("111111111", new AccountNumber(entry).toString());
    }

    @Test
    void allTwosEntry() {
        Entry entry = new Entry(" _  _  _  _  _  _  _  _  _ ", " _| _| _| _| _| _| _| _| _|", "|_ |_ |_ |_ |_ |_ |_ |_ |_ ");
        assertEquals("222222222", new AccountNumber(entry).toString());
    }

    @Test
    void allOneToNineDigitEntry() {
        Entry entry = new Entry("    _  _     _  _  _  _  _ ", "  | _| _||_||_ |_   ||_||_|", "  ||_  _|  | _||_|  ||_| _|");
        assertEquals("123456789", new AccountNumber(entry).toString());
    }

    @Test
    void isAccountNumberValid() {
        Entry entry = new Entry(" _  _  _  _  _  _  _  _  _ ", " _|| || ||_|| || || || || |", "|_ |_||_||_||_||_||_||_||_|");
        assertTrue(new AccountNumber(entry).isValid());
    }

    @Test
    void isAccountNumberNotValid() {
        Entry entry = new Entry(" _  _  _  _  _  _  _  _  _ ", " _|| || || || || || || || |", "|_ |_||_||_||_||_||_||_||_|");
        assertFalse(new AccountNumber(entry).isValid());
    }
}
