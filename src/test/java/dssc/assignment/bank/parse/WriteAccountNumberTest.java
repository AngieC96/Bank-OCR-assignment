package dssc.assignment.bank.parse;

import dssc.assignment.bank.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteAccountNumberTest {

    @ParameterizedTest
    @CsvSource({"allZerosEntry, src/test/resources/writeallZerosEntry, '000000000'",
                "multipleEntries, src/test/resources/writeMultipleEntries, '200800000;999999999 ERR;490867715'",
                "multipleEntriesWithMissing, src/test/resources/writeMultipleEntriesWithMissing, '200800000;999999999 ERR;49086771? ILL'"})
    void writeOnFile(String source, String destination, String expected) throws Exception {
        List<String> expectedAsList = Arrays.asList(expected.split(";"));
        URL MultipleLines = BankOcrAcceptanceTest.class.getClassLoader().getResource(source);
        EntryReader reader = new EntryReader(Path.of(MultipleLines.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = new ArrayList<>();
        for (Entry entry : entries) {
            numbers.add(new AccountNumber(entry));
        }
        EntryWriter writer = new EntryWriter(numbers);
        writer.writeAccountNumbers(Paths.get(destination));
        List<String> fileLines = Files.readAllLines(Paths.get(destination));
        assertEquals(expectedAsList, fileLines);


    }

    @ParameterizedTest
    @CsvSource({"multipleEntriesWithMissing, src/test/resources/writeMultipleEntriesWithSuggestions, '200800000;999999999 AMB [899999999, 993999999, 999959999];490867715'",
                "multipleEntriesWithTwoMissing, src/test/resources/writeMultipleEntriesWithNoSuggestions, '200800000;999999999 AMB [899999999, 993999999, 999959999];?9086771? ILL'",
                "anotherMultipleEntriesWithMissing, src/test/resources/writeAnotherMultipleEntriesWithMissing, '555555555 AMB [559555555, 555655555];490867715;49?067715 ILL'"})
    void writeOnFileWithSuggestions(String source, String destination, String expected) throws Exception {
        List<String> expectedAsList = Arrays.asList(expected.split(";"));
        URL MultipleLines = BankOcrAcceptanceTest.class.getClassLoader().getResource(source);
        EntryReader reader = new EntryReader(Path.of(MultipleLines.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = new ArrayList<>();
        for (Entry entry : entries) {
            numbers.add(new AccountNumber(entry));
        }
        EntryWriter writer = new EntryWriter(numbers);
        writer.writeAccountNumbersWithSuggestions(Paths.get(destination));
        List<String> fileLines = Files.readAllLines(Paths.get(destination));
        assertEquals(expectedAsList, fileLines);
    }

}
