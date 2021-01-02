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

    public void writeAccountNumbersWithSuggestions(Path filePath) throws IOException {
        String toBeWritten = "";
        for (AccountNumber number : accountNumbers) {
            if (!number.hasQuestionMarkDigit() && number.isValid()){
                toBeWritten += number.toString() + System.lineSeparator();
            }
            else {
                List<AccountNumber> suggested = number.suggestedAccountNumbers();
                if (suggested.size() == 0){
                    toBeWritten += number.toString() + " ILL" + System.lineSeparator();
                }
                else if (suggested.size() == 1){
                    toBeWritten += suggested.get(0).toString() + System.lineSeparator();
                } else {
                    toBeWritten += number.toString() + " AMB " + suggested.toString() + System.lineSeparator();
                }
            }
        }
        Files.write(filePath, toBeWritten.getBytes(StandardCharsets.UTF_8));
    }
}
