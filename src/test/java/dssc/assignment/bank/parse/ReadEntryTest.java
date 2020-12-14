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
        List<Entry> entries = reader.readEntries();
        assertEquals("000000000", entries.get(0).toString());
    }

    @Test
    void allOnesEntry() throws Exception {
        URL allOnesSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allOnesEntry");
        EntryReader reader = new EntryReader(Path.of(allOnesSingleEntry.toURI()));
        List<Entry> entries = reader.readEntries();
        assertEquals("111111111", entries.get(0).toString());
    }

    @Test
    void allTwosEntry() throws Exception {
        URL allTwosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allTwosEntry");
        EntryReader reader = new EntryReader(Path.of(allTwosSingleEntry.toURI()));
        List<Entry> entries = reader.readEntries();
        assertEquals("222222222", entries.get(0).toString());
    }

    @Test
    void allOneToNineDigitEntry() throws Exception {
        URL allOneToNineDigitSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allOneToNineDigitEntry");
        EntryReader reader = new EntryReader(Path.of(allOneToNineDigitSingleEntry.toURI()));
        List<Entry> entries = reader.readEntries();
        assertEquals("123456789", entries.get(0).toString());
    }

    @Test
    void multipleEntries() throws Exception {
        URL multipleEntries = BankOcrAcceptanceTest.class.getClassLoader().getResource("multipleEntries");
        EntryReader reader = new EntryReader(Path.of(multipleEntries.toURI()));
        List<Entry> entries = reader.readEntries();
        assertAll(
                () -> assertEquals("200800000", entries.get(0).toString()),
                () -> assertEquals("999999999", entries.get(1).toString()),
                () -> assertEquals("490867715", entries.get(2).toString())
        );
    }

}