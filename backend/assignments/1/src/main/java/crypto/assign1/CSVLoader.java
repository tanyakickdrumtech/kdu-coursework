package crypto.assign1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Utility class for loading CSV data from resources using Apache Commons CSV.
 */
public class CSVLoader {
    private CSVLoader(){

    }

    /**
     * Loads CSV data from a specified file in the resources folder.
     *
     * @param fileName The name of the CSV file in the resources folder.
     * @return A list of CSV records parsed from the file.
     * @throws IOException If an I/O error occurs while reading the CSV file.
     */
    public static List<CSVRecord> loadCSVFromResources(String fileName) throws IOException {
        // Use ClassLoader to load the resource
        ClassLoader classLoader = CSVLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // Use Apache Commons CSV to parse the CSV data
        CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(new InputStreamReader(inputStream));

        // Return the list of CSV records
        return csvParser.getRecords();
    }
}
