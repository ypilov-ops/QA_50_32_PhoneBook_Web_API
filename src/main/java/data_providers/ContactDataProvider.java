package data_providers;

import dto.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactDataProvider {
    @DataProvider
    public Iterator<Contact> dataProviderFromFile() {
        List<Contact> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader
                    (new FileReader("src/test/resources/data_csv/dataContacts.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] splitArray = line.split(",");
                list.add(Contact.builder()
                        .name(splitArray[0])
                        .lastName(splitArray[1])
                        .email(splitArray[2])
                        .phone(splitArray[3])
                        .address(splitArray[4])
                        .description(splitArray[5])
                        .build());
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IO exception");
        }
        return list.listIterator();
    }
}
