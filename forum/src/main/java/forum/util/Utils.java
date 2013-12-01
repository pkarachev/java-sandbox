package forum.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import java.io.IOException;

public class Utils {
    private static ObjectWriter jsonWriter = new ObjectMapper().writer();

    public static String toJson(Object obj) {
        try {
            return jsonWriter.writeValueAsString(obj);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
