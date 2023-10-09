package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hexlet.code.DiffAnalizer;

import java.util.List;

public class JsonFormatter {
    public static String format(List<DiffAnalizer> data) {
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
