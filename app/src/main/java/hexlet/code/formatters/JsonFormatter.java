package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<Map<Object, Object>> data) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        StringBuilder result = new StringBuilder();
        data.stream()
                .forEach(i -> {
                    try {
                        result.append(ow.writeValueAsString(i));
                        result.append("\n");
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
        return String.valueOf(result).trim();
    }
}
