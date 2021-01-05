package dssc.assignment.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountNumber {

    private final Entry entry;

    public AccountNumber(Entry entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return entry.toString();
    }

    public boolean isValid() {
        String accountNumber = entry.toString();
        int length = accountNumber.length();
        int checkSum = 0;
        for (int i=0; i < length; ++i) {
            checkSum += (length-i) * Integer.parseInt(accountNumber.substring(i, i+1));
        }
        return checkSum % 11 == 0;
    }

    public boolean hasQuestionMarkDigit() {
        String accountNumber = entry.toString();
        return accountNumber.codePoints().anyMatch(x -> x == '?');
    }

    private int findQuestionMarkDigit(){
        String accountNumber = entry.toString();
        return IntStream.range(0, accountNumber.length())
                .filter(i -> accountNumber.charAt(i) == '?').findAny().getAsInt();
    }

    public List<AccountNumber> suggestedAccountNumbers(){
        List<AccountNumber> possibleAccountNumbers = new ArrayList<>();
        if (hasQuestionMarkDigit()){
            int questionMarkIndex = findQuestionMarkDigit();
            possibleAccountNumbers.addAll(generateAccountNumbersFromCell(questionMarkIndex));
        }
        else if (!isValid()) {
            for (int i = 0; i < 9; ++i) {
                possibleAccountNumbers.addAll(generateAccountNumbersFromCell(i));            }
        }
        return possibleAccountNumbers;
    }

    private List<AccountNumber> generateAccountNumbersFromCell(int i) {
        Cell currentCell = entry.getCells().get(i);
        List<Cell> closestCells = currentCell.nearestCells();
        return closestCells.stream().map(x -> replaceCellAt(i, x))
                .filter(AccountNumber::isReal).collect(Collectors.toList());
    }

    private AccountNumber replaceCellAt(int index, Cell cellToChange){
        List<Cell> accountNumberCells = new ArrayList<>(entry.getCells());
        accountNumberCells.set(index, cellToChange);
        return new AccountNumber(new Entry(accountNumberCells));
    }

    private boolean isReal() {
        return !hasQuestionMarkDigit() && isValid();
    }

}
