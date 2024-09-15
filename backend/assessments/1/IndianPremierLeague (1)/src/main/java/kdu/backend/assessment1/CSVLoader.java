package kdu.backend.assessment1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CSVLoader {
    private CSVLoader(){

    }
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
