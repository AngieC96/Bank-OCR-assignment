package dssc.assignment.bank.parse;

import dssc.assignment.bank.*;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        URL writeallZerosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("writeallZerosEntry");
        writer.writeUsingOutputStream(Path.of(writeallZerosSingleEntry.toURI()));
    }
}
