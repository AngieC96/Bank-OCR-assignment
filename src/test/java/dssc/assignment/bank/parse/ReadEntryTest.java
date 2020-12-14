package dssc.assignment.bank.parse;

import dssc.assignment.bank.BankOcrAcceptanceTest;
import dssc.assignment.bank.Entry;
import dssc.assignment.bank.EntryReader;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadEntryTest {

    @Test
    void allZerosEntry() throws Exception {
        URL allZerosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allZerosEntry");
        EntryReader reader = new EntryReader(Path.of(allZerosSingleEntry.toURI()));
        Entry entry = reader.readEntry();
        assertEquals("000000000", entry.toString());
    }

    @Test
    void allOnesEntry() throws Exception {
        URL allOnesSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allOnesEntry");
        EntryReader reader = new EntryReader(Path.of(allOnesSingleEntry.toURI()));
        Entry entry = reader.readEntry();
        assertEquals("111111111", entry.toString());
    }

    @Test
    void allTwosEntry() throws Exception {
        URL allTwosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allTwosEntry");
        EntryReader reader = new EntryReader(Path.of(allTwosSingleEntry.toURI()));
        Entry entry = reader.readEntry();
        assertEquals("222222222", entry.toString());
    }

    @Test
    void allOneToNineDigitEntry() throws Exception {
        URL allOneToNineDigitSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allOneToNineDigitEntry");
        EntryReader reader = new EntryReader(Path.of(allOneToNineDigitSingleEntry.toURI()));
        Entry entry = reader.readEntry();
        assertEquals("123456789", entry.toString());
    }

    @Test
    void multipleEntries() throws Exception {
        URL multipleEntries = BankOcrAcceptanceTest.class.getClassLoader().getResource("multipleEntries");
        EntryReader reader = new EntryReader(Path.of(multipleEntries.toURI()));
        List<Entry> entries = reader.readMultipleEntries();
        assertAll(
                () -> assertEquals("200800000", entries.get(0).toString()),
                () -> assertEquals("999999999", entries.get(1).toString()),
                () -> assertEquals("490867715", entries.get(2).toString())
        );
    }

}