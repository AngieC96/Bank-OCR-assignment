package dssc.assignment.bank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EntryWriter {
    // since we are not writing entries but account Numbers should we change the name of the class?
    private final List<AccountNumber> accountNumbers;

    public EntryWriter(List<AccountNumber> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public void writeAccountNumbers(Path filePath) throws IOException {
        String toBeWritten = "";
        for (AccountNumber accountNumber : accountNumbers) {
            toBeWritten = getString(toBeWritten, accountNumber);
        }
        Files.write(filePath, toBeWritten.getBytes(StandardCharsets.UTF_8));
    }

    private String getString(String toBeWritten, AccountNumber accountNumber) {
        toBeWritten += accountNumber.toString();
        if (accountNumber.hasQuestionMarkDigit()){
            toBeWritten += " ILL";
        } else if (!accountNumber.isValid()){
            toBeWritten += " ERR";
        }
        toBeWritten += System.lineSeparator();
        return toBeWritten;
    }

    // here there is code duplication but referring to different user stories is it ok to keep?
    public void writeAccountNumbersWithSuggestions(Path filePath) throws IOException {
        String toBeWritten = "";
        for (AccountNumber accountNumber : accountNumbers) {
            toBeWritten = getStringWithSuggestions(toBeWritten, accountNumber);
        }
        Files.write(filePath, toBeWritten.getBytes(StandardCharsets.UTF_8));
    }

    private String getStringWithSuggestions(String toBeWritten, AccountNumber accountNumber) {
        if (!accountNumber.hasQuestionMarkDigit() && accountNumber.isValid()){ // replace this w/ isReal() after merging!
            return toBeWritten + accountNumber.toString() + System.lineSeparator();
        }
        return writeSuggestions(toBeWritten, accountNumber);
    }

    private String writeSuggestions(String toBeWritten, AccountNumber accountNumber) {
        List<AccountNumber> suggestions = accountNumber.suggestedAccountNumbers();
        if (numberOfSuggestions(suggestions, 0)){
            return toBeWritten + accountNumber.toString() + " ILL" + System.lineSeparator();
        } else if (numberOfSuggestions(suggestions, 1)){
            return toBeWritten + suggestions.get(0).toString() + System.lineSeparator();
        }
        return toBeWritten + accountNumber.toString() + " AMB " + suggestions.toString() + System.lineSeparator();
    }

    private boolean numberOfSuggestions(List<AccountNumber> suggestions, int i) {
        return suggestions.size() == i;
    }

}
