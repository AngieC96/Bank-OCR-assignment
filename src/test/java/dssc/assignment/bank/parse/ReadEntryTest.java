package dssc.assignment.bank.parse;

import dssc.assignment.bank.BankOcrAcceptanceTest;
import dssc.assignment.bank.Entry;
import dssc.assignment.bank.EntryReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadEntryTest {


    @ParameterizedTest
    @CsvSource({"allZerosEntry, '000000000'",
                "allOnesEntry, '111111111'",
                "allTwosEntry, '222222222'",
                "allOneToNineDigitEntry,  '123456789'"})
    void readFromFileOneEntry(String inputFileName, String expectedEntry) throws Exception {
        URL oneSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource(inputFileName);
        EntryReader reader = new EntryReader(Path.of(oneSingleEntry.toURI()));
        List<Entry> entries  = reader.readEntries();
        Entry entryToRead = entries.get(0);
        assertEquals(expectedEntry, entryToRead.toString());
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