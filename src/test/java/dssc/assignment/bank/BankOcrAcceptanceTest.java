package dssc.assignment.bank;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;

public class BankOcrAcceptanceTest {

    @Test
    public void parseFileWithSingleAllZeroesEntryAndShowActualAccountNumberOnConsole() throws Exception {
        URL allZerosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allZerosEntry");
        ApplicationRunner application = new ApplicationRunner();

        application.parseFile(Path.of(allZerosSingleEntry.toURI()));

        application.showsAccountNumber("000000000" + System.lineSeparator());
    }

    @Test
    public void parseFileWithSingleAllOnesEntryAndShowActualAccountNumberOnConsole() throws Exception {
        URL allOnesSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allOnesEntry");
        ApplicationRunner application = new ApplicationRunner();

        application.parseFile(Path.of(allOnesSingleEntry.toURI()));

        application.showsAccountNumber(String.format("111111111%n"));
    }

    @Test
    public void parseFileWithSingleAllTwosEntryAndShowActualAccountNumberOnConsole() throws Exception {
        URL allTwosSingleEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allTwosEntry");
        ApplicationRunner application = new ApplicationRunner();

        application.parseFile(Path.of(allTwosSingleEntry.toURI()));

        application.showsAccountNumber(String.format("222222222%n"));
    }

    @Test
    public void parseFileWithOneToNineDigitEntryAndShowActualAccountNumberOnConsole() throws Exception {
        URL oneToNineEntry = BankOcrAcceptanceTest.class.getClassLoader().getResource("allOneToNineDigitEntry");
        ApplicationRunner application = new ApplicationRunner();

        application.parseFile(Path.of(oneToNineEntry.toURI()));

        application.showsAccountNumber(String.format("123456789%n"));
    }
}
