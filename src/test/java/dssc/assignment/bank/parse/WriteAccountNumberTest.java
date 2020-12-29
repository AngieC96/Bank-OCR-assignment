package dssc.assignment.bank.parse;

import dssc.assignment.bank.*;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteAccountNumberTest {

    @Test
    void allZerosEntry() throws Exception {
        URL allZerosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allZerosEntry");
        EntryReader reader = new EntryReader(Path.of(allZerosSingleEntry.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = new ArrayList<>();
        for (Entry entry : entries) {
            numbers.add(new AccountNumber(entry));
        }
        EntryWriter writer = new EntryWriter(numbers);
        writer.writeAccountNumbers(Paths.get("src/test/resources/writeallZerosEntry"));
        List<String> fileLines = Files.readAllLines(Paths.get("src/test/resources/writeallZerosEntry"));
        assertEquals("000000000", fileLines.get(0));
    }

    @Test
    void MultipleLines() throws Exception {
        URL MultipleLines = BankOcrAcceptanceTest.class.getClassLoader().getResource("multipleEntries");
        EntryReader reader = new EntryReader(Path.of(MultipleLines.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = new ArrayList<>();
        for (Entry entry : entries) {
            numbers.add(new AccountNumber(entry));
        }
        EntryWriter writer = new EntryWriter(numbers);
        writer.writeAccountNumbers(Paths.get("src/test/resources/writeMultipleEntries"));
        List<String> fileLines = Files.readAllLines(Paths.get("src/test/resources/writeMultipleEntries"));
        assertAll(
                () -> assertEquals("200800000", fileLines.get(0)),
                () -> assertEquals("999999999 ERR", fileLines.get(1)),
                () -> assertEquals("490867715", fileLines.get(2))
        );
    }
    @Test
    void MultipleLinesWithMissing() throws Exception {
        URL MultipleLines = BankOcrAcceptanceTest.class.getClassLoader().getResource("multipleEntriesWithMissing");
        EntryReader reader = new EntryReader(Path.of(MultipleLines.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = new ArrayList<>();
        for (Entry entry : entries) {
            numbers.add(new AccountNumber(entry));
        }
        EntryWriter writer = new EntryWriter(numbers);
        writer.writeAccountNumbers(Paths.get("src/test/resources/writeMultipleEntriesWithMissing"));
        List<String> fileLines = Files.readAllLines(Paths.get("src/test/resources/writeMultipleEntriesWithMissing"));
        assertAll(
                () -> assertEquals("200800000", fileLines.get(0)),
                () -> assertEquals("999999999 ERR", fileLines.get(1)),
                () -> assertEquals("49086771? ILL", fileLines.get(2))
        );
    }

}
