package dssc.assignment.bank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class AccountNumberWriter {

    private final List<AccountNumber> accountNumbers;

    public AccountNumberWriter(List<AccountNumber> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    public void writeAccountNumbers(Path filePath, Function<AccountNumber, String> myMethod) throws IOException {
        StringBuilder toBeWritten = new StringBuilder();
        for (AccountNumber accountNumber : accountNumbers) {
            toBeWritten.append(myMethod.apply(accountNumber));
        }
        Files.write(filePath, toBeWritten.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static String getString(AccountNumber accountNumber) {
        String toBeWritten = accountNumber.toString();
        if (accountNumber.hasQuestionMarkDigit()){
            toBeWritten += " ILL";
        } else if (!accountNumber.isValid()){
            toBeWritten += " ERR";
        }
        toBeWritten += System.lineSeparator();
        return toBeWritten;
    }


    public static String getStringWithSuggestions(AccountNumber accountNumber) {
        if (!accountNumber.hasQuestionMarkDigit() && accountNumber.isValid()){ // replace this w/ isReal() after merging!
            return accountNumber.toString() + System.lineSeparator();
        }
        return writeSuggestions(accountNumber);
    }

    private static String writeSuggestions(AccountNumber accountNumber) {
        List<AccountNumber> suggestions = accountNumber.suggestedAccountNumbers();
        if (isNumberOfSuggestionsEqualsTo(suggestions, 0)){
            return accountNumber.toString() + " ILL" + System.lineSeparator();
        } else if (isNumberOfSuggestionsEqualsTo(suggestions, 1)){
            return suggestions.get(0).toString() + System.lineSeparator();
        }
        return accountNumber.toString() + " AMB " + suggestions.toString() + System.lineSeparator();
    }

    private static boolean isNumberOfSuggestionsEqualsTo(List<AccountNumber> suggestions, int i) {
        return suggestions.size() == i;
    }

}
