package dssc.assignment.bank.parse;

import dssc.assignment.bank.AccountNumber;
import dssc.assignment.bank.Entry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AccountNumberTests {

    @ParameterizedTest
    @MethodSource("ProvideEntryAndExpectedValue")
    void readEntriesIntoAccountNumbers(Entry entry, String expected) {
        assertEquals(expected, new AccountNumber(entry).toString());
    }

    private static Stream<Arguments> ProvideEntryAndExpectedValue() {
        Entry entry1 = new Entry(" _  _  _  _  _  _  _  _  _ ","| || || || || || || || || |","|_||_||_||_||_||_||_||_||_|");
        Entry entry2 = new Entry("                           ", "  |  |  |  |  |  |  |  |  |", "  |  |  |  |  |  |  |  |  |");
        Entry entry3 = new Entry(" _  _  _  _  _  _  _  _  _ ", " _| _| _| _| _| _| _| _| _|", "|_ |_ |_ |_ |_ |_ |_ |_ |_ ");
        Entry entry4 = new Entry("    _  _     _  _  _  _  _ ", "  | _| _||_||_ |_   ||_||_|", "  ||_  _|  | _||_|  ||_| _|");
        return Stream.of(
                Arguments.of(entry1, "000000000"),
                Arguments.of(entry2, "111111111"),
                Arguments.of(entry3, "222222222"),
                Arguments.of(entry4, "123456789")
        );
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

    @Test
    void hasNoQuestionMarkDigit() {
        Entry entry = new Entry(" _  _  _  _  _  _  _  _  _ ", " _|| || || || || || || || |", "|_ |_||_||_||_||_||_||_||_|");
        assertFalse(new AccountNumber(entry).hasQuestionMarkDigit());
    }

    @Test
    void hasQuestionMarkDigit() {
        Entry entry = new Entry("    _  _  _  _  _  _     _ ", "|_||_|| ||_||_   |  |  | _ ", "  | _||_||_||_|  |  |  | _|");
        assertTrue(new AccountNumber(entry).hasQuestionMarkDigit());
    }
}
