package dssc.assignment.bank;

import java.util.ArrayList;
import java.util.List;

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
        for (int i=0; i < length; i++) {
            checkSum += (length-i) * Integer.parseInt(accountNumber.substring(i, i+1));
        }
        return checkSum % 11 == 0;
    }

    public boolean hasQuestionMarkDigit() {
        String accountNumber = entry.toString();
        return accountNumber.codePoints().anyMatch(x -> x == '?');
    }

    public int placeQuestionMarkDigit(){
        String accountNumber = entry.toString();
        int length = accountNumber.length();
        for (int i = 0; i < length; i++) {
            if (accountNumber.charAt(i) == '?') {
                return i;
            }
        }
        return -1;
    }

    public List<AccountNumber> suggestedAccountNumbers(){
        List<AccountNumber> possibleAccountNumbers = new ArrayList<>();
        if (hasQuestionMarkDigit()){
            int questionMarkIndex = placeQuestionMarkDigit();
            generateAccountNumbersFromCell( possibleAccountNumbers, questionMarkIndex );
        }
        else if (!isValid()) {
            for (int i = 0; i < 9; ++i) {
                generateAccountNumbersFromCell( possibleAccountNumbers, i );
            }
        }
        return possibleAccountNumbers;
    }

    private void generateAccountNumbersFromCell(List<AccountNumber> possibleAccountNumbers, int i) {
        Cell currentCell = entry.getCells().get(i);
        List<Cell> closestCells = currentCell.nearestCells();

        for (Cell cell : closestCells) {
            AccountNumber alternativeAccountNumber = replaceAt(i, cell);
            if (!alternativeAccountNumber.hasQuestionMarkDigit() && alternativeAccountNumber.isValid()) {
                possibleAccountNumbers.add(alternativeAccountNumber);
            }
        }
    }

    private AccountNumber replaceAt(int index, Cell cellToChange){
        List<Cell> accountNumberCells = new ArrayList<>(entry.getCells());
        accountNumberCells.set(index, cellToChange);
        return new AccountNumber(new Entry(accountNumberCells));
    }

}
