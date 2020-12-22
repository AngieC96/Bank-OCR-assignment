package dssc.assignment.bank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EntryWriter {

    private final List<AccountNumber> accountNumbers;

    public EntryWriter(List<AccountNumber> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public void writeAccountNumbers(Path filePath) throws IOException {
        String toBeWritten = "";
        for (AccountNumber number : accountNumbers) {
            toBeWritten += number.toString();
            if (number.hasQuestionMarkDigit()){
                toBeWritten += " ILL";
            } else if (!number.isValid()){
                toBeWritten += " ERR";
            }
            toBeWritten += System.lineSeparator();
        }
        Files.write(filePath, toBeWritten.getBytes(StandardCharsets.UTF_8));
    }
}
