package crypto.assign1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for loading JSON data from resources using Jackson ObjectMapper.
 */
public class JSONLoader {
    private JSONLoader(){

    }

    /**
     * Loads JSON data from a specified file in the resources folder.
     *
     * @param fileName The name of the JSON file in the resources folder.
     * @return A JsonNode representing the parsed JSON data.
     * @throws IOException If an I/O error occurs while reading the JSON file.
     */
    public static JsonNode loadJSONFromResources(String fileName) throws IOException {
        // Use ClassLoader to load the resource
        ClassLoader classLoader = JSONLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // Use Jackson ObjectMapper to parse the JSON data
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(inputStream);
    }
}
