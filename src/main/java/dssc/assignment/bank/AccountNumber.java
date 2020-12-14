package dssc.assignment.bank;

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

}
