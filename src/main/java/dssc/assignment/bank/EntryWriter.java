package dssc.assignment.bank;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EntryWriter {

    private final List<AccountNumber> accountNumbers;

    public EntryWriter(List<AccountNumber> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }
    public static void writeUsingOutputStream(Path filePath) {
        OutputStream os = null;
        String data = "ciao";
        try {
            os = new FileOutputStream(new File(String.valueOf(filePath)));
            os.write(data.getBytes(), 0, data.length());
        }
        catch (IOException e) {
            e.printStackTrace();
        }finally
        {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeAccountNumbers(Path filePath) throws IOException {
        String toBeWritten = "";
        for (AccountNumber number : accountNumbers) {
            toBeWritten += number.toString() + System.lineSeparator();
        }
        Files.write(filePath, "ciao".getBytes());
        //Files.write(filePath, toBeWritten.getBytes(StandardCharsets.UTF_8));
    }
}
