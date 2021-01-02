package dssc.assignment.bank;

import java.util.ArrayList;
import java.util.List;

public class AccountNumber {

    private Entry entry;

    public AccountNumber(Entry entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return entry.toString();
    }

    public boolean isValid() {
        String accountnumber = entry.toString();
        int length = accountnumber.length();
        int sum = 0;
        for (int i=0; i < length; i++) {
            sum += (length-i) * Integer.parseInt(accountnumber.substring(i, i+1));
        }
        return sum % 11 == 0;
    }

    public boolean hasQuestionMarkDigit() {
        String accountnumber = entry.toString();
        int length = accountnumber.length();
        for (int i=0; i < length; i++) {
            if (accountnumber.charAt(i) == '?') {
                return true;
            }
        }
        return false;
    }

    public int placeQuestionMarkDigit(){

        String accountnumber = entry.toString();
        int length = accountnumber.length();
        for (int i = 0; i < length; i++) {
            if (accountnumber.charAt(i) == '?') {
                return i;
            }
        }

        return -1;
    }

    public AccountNumber replaceAt(int index, Cell cellToChange){

        List<Cell> accountNumberCells = new ArrayList<>(entry.getCells());

        accountNumberCells.set(index, cellToChange);

        return new AccountNumber(new Entry(accountNumberCells));
    }

    public List<AccountNumber> suggestedAccountNumbers(){
        // Initialize possibleAccountNumbers
        List<AccountNumber> possibleAccountNumbers = new ArrayList<>();

        // Case 1: Account number with a "?": replace that cell with a distance = 1 cell
        if (hasQuestionMarkDigit()){

            int questionMarkIndex = placeQuestionMarkDigit();
            Cell questionMarkCell = entry.getCells().get(questionMarkIndex);
            List<Cell> closestCells = questionMarkCell.nearestCells();

            for (Cell cell : closestCells){
                AccountNumber alternativeAccountNumber = replaceAt(questionMarkIndex, cell);
                if (!alternativeAccountNumber.hasQuestionMarkDigit() && alternativeAccountNumber.isValid()) {
                    possibleAccountNumbers.add(alternativeAccountNumber);
                }
            }
        } else if (!isValid()) {
            for (int i = 0; i < 9; ++i) {
                Cell currentCell = entry.getCells().get(i);
                List<Cell> closestCells = currentCell.nearestCells();

                for (Cell cell : closestCells){
                    AccountNumber alternativeAccountNumber = replaceAt(i, cell);
                    if (alternativeAccountNumber.isValid()) {
                        possibleAccountNumbers.add(alternativeAccountNumber);
                    }
                }
            }
        }

        return possibleAccountNumbers;
    }

}
