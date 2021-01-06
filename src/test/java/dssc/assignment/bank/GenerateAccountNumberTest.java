package dssc.assignment.bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URL;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateAccountNumberTest {

    @ParameterizedTest
    @MethodSource("ProvideAccountNumberAndExpectedSuggestions")
    void accountNumberSuggestions(AccountNumber number, List<String> expected) {
        List<AccountNumber> suggested = number.suggestedAccountNumbers();
        List<String> accountNumberAsStrings = suggested.stream().map(AccountNumber::toString).collect(Collectors.toList());
        assertEquals(expected, accountNumberAsStrings);
    }

    private static Stream<Arguments> ProvideAccountNumberAndExpectedSuggestions() {
        Entry entry1 = new Entry("    _  _  _  _  _  _     _ ","|_||_|| ||_||_   |  |  | _ ","  | _||_||_||_|  |  |  | _|");
        AccountNumber number1 = new AccountNumber(entry1);
        Entry entry2 = new Entry("    _  _     _  _  _  _  _ "," _| _| _||_||_ |_   ||_||_|","  ||_  _|  | _||_|  ||_| _|");
        AccountNumber number2 = new AccountNumber(entry2);
        Entry entry3 = new Entry(" _     _  _  _  _  _  _    ","| || || || || || || ||_   |","|_||_||_||_||_||_||_| _|  |");
        AccountNumber number3 = new AccountNumber(entry3);
        Entry entry4 = new Entry("                           ","  |  |  |  |  |  |  |  |  |","  |  |  |  |  |  |  |  |  |");
        AccountNumber number4 = new AccountNumber(entry4);
        Entry entry5 = new Entry(" _  _  _  _  _  _  _  _  _ ","|_||_||_||_||_||_||_||_||_|","|_||_||_||_||_||_||_||_||_|");
        AccountNumber number5 = new AccountNumber(entry5);
        return Stream.of(
                Arguments.of(number1, Collections.singletonList("490867715")),
                Arguments.of(number2, Collections.singletonList("123456789")),
                Arguments.of(number3, Collections.singletonList("000000051")),
                Arguments.of(number4, Collections.singletonList("711111111")),
                Arguments.of(number5, Arrays.asList("888886888", "888888988", "888888880"))
        );
    }

    @Test
    void suggestionForMultipleEntriesFromFile() throws Exception {
        URL allOnesSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("multipleEntriesWithMissing");
        EntryReader reader = new EntryReader(Path.of(allOnesSingleEntry.toURI()));
        List<Entry> entries = reader.readEntries();
        List<AccountNumber> numbers = entries.stream().map(AccountNumber::new).collect(Collectors.toList());
        List<String> suggested = numbers.stream()
                .map(AccountNumber::suggestedAccountNumbers)
                .flatMap(Collection::stream)
                .map(AccountNumber::toString)
                .collect(Collectors.toList());
        List<String> expected = Arrays.asList("899999999", "993999999", "999959999", "490867715");
        assertEquals(expected, suggested);
    }
}
