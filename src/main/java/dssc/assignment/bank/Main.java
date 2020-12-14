package dssc.assignment.bank;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String... args) throws IOException {
        EntryReader parser = new EntryReader(Path.of(args[0]));
        //Entry entry = parser.readEntries();
        List<Entry> entries = parser.readEntries();
        List<AccountNumber> accountNumbers = new ArrayList<>();
        for (Entry entry : entries) {
            accountNumbers.add(new AccountNumber(entry));
        }
        for (AccountNumber accountNumber : accountNumbers) {
            System.out.println(accountNumber.toString());
        }
    }
}
