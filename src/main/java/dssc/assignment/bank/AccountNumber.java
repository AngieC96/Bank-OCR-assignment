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
        return true;
    }
}
