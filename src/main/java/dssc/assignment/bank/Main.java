package dssc.assignment.bank;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String... args) throws IOException {
        EntryReader parser = new EntryReader(Path.of(args[0]));
        //Entry entry = parser.readEntries();
        List<Entry> entries = parser.readEntries();
        AccountNumber accountNumber = new AccountNumber(entries.get(0));
        System.out.println(accountNumber.toString());
    }
}
