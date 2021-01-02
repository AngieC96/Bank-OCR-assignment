package dssc.assignment.bank;

import org.junit.jupiter.api.Test;


import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateAccountNumberTest {

    @Test
    void suggestionForEntryWithMissingValue() throws Exception {
        Entry entry = new Entry("    _  _  _  _  _  _     _ ","|_||_|| ||_||_   |  |  | _ ","  | _||_||_||_|  |  |  | _|");
        AccountNumber number = new AccountNumber(entry);
        List<AccountNumber> suggested = number.suggestedAccountNumbers();
        List<String> suggestedStrings = new ArrayList<>();
        for (AccountNumber x: suggested) {
            suggestedStrings.add(x.toString());
        }
        List<String> expected = Arrays.asList("490867715");
        assertEquals(expected, suggestedStrings);
    }

    @Test
    void suggestionForAnotherEntryWithMissingValue() throws Exception {
        Entry entry = new Entry("    _  _     _  _  _  _  _ "," _| _| _||_||_ |_   ||_||_|","  ||_  _|  | _||_|  ||_| _|");
        AccountNumber number = new AccountNumber(entry);
        List<AccountNumber> suggested = number.suggestedAccountNumbers();
        List<String> suggestedStrings = new ArrayList<>();
        for (AccountNumber x: suggested) {
            suggestedStrings.add(x.toString());
        }
        List<String> expected = Arrays.asList("123456789");
        assertEquals(expected, suggestedStrings);
    }

    @Test
    void suggestionForYetAnotherEntryWithMissingValue() throws Exception {
        Entry entry = new Entry(" _     _  _  _  _  _  _    ","| || || || || || || ||_   |","|_||_||_||_||_||_||_| _|  |");
        AccountNumber number = new AccountNumber(entry);
        List<AccountNumber> suggested = number.suggestedAccountNumbers();
        List<String> suggestedStrings = new ArrayList<>();
        for (AccountNumber x: suggested) {
            suggestedStrings.add(x.toString());
        }
        List<String> expected = Arrays.asList("000000051");
        assertEquals(expected, suggestedStrings);
    }


    @Test
    void suggestionForNotValid() throws Exception {
        Entry entry = new Entry("                           ","  |  |  |  |  |  |  |  |  |","  |  |  |  |  |  |  |  |  |");
        AccountNumber number = new AccountNumber(entry);
        List<AccountNumber> suggested = number.suggestedAccountNumbers();
        List<String> suggestedStrings = new ArrayList<>();
        for (AccountNumber x: suggested) {
            suggestedStrings.add(x.toString());
        }
        List<String> expected = Arrays.asList("711111111");
        assertEquals(expected, suggestedStrings);
    }

    @Test
    void suggestionForAnotherNotValid() throws Exception {
        Entry entry = new Entry(" _  _  _  _  _  _  _  _  _ ","|_||_||_||_||_||_||_||_||_|","|_||_||_||_||_||_||_||_||_|");
        AccountNumber number = new AccountNumber(entry);
        List<AccountNumber> suggested = number.suggestedAccountNumbers();
        List<String> suggestedStrings = new ArrayList<>();
        for (AccountNumber x: suggested) {
            suggestedStrings.add(x.toString());
        }
        List<String> expected = Arrays.asList("888886888", "888888988", "888888880");
        assertEquals(expected, suggestedStrings);
    }

    @Test
    void suggestionForMultipleEntriesFromFile() throws Exception {
        URL allOnesSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("multipleEntriesWithMissing");
        EntryReader reader = new EntryReader(Path.of(allOnesSingleEntry.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = new ArrayList<>();
        for(Entry entry: entries){
            numbers.add(new AccountNumber(entry));
        }
        List<String> suggested = new ArrayList<>();
        for(AccountNumber accountNumber: numbers) {
            List<AccountNumber> generatedAccountNumbers = accountNumber.suggestedAccountNumbers();
            for (AccountNumber x: generatedAccountNumbers) {
                suggested.add(x.toString());
            }
        }
        List<String> expected = Arrays.asList("899999999", "993999999", "999959999", "490867715");
        assertEquals(expected, suggested);
    }
}
