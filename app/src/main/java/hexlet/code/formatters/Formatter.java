package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import hexlet.code.DiffAnalizer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String stylish(List<DiffAnalizer> data) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        data.stream()
                .forEach(i -> {
                    if (i.getDifference().equals("removed")) {
                        result.append("  - ").append(i.getKey()).append(": ").append(i.getValue1()).append("\n");
                    } else if (i.getDifference().equals("added")) {
                        result.append("  + ").append(i.getKey()).append(": ").append(i.getValue2()).append("\n");
                    } else if (i.getDifference().equals("equal")) {
                        result.append("    ").append(i.getKey()).append(": ").append(i.getValue1()).append("\n");
                    } else if (i.getDifference().equals("changed")) {
                        result.append("  - ").append(i.getKey()).append(": ").append(i.getValue1()).append("\n");
                        result.append("  + ").append(i.getKey()).append(": ").append(i.getValue2()).append("\n");
                    }
                });
        result.append("}");
        return String.valueOf(result);
    }
    public static String plain(List<DiffAnalizer> data) {
        StringBuilder result = new StringBuilder();

        data.stream()
                .forEach(i -> {
                    if (i.getDifference().equals("removed")) {
                        result.append("Property '").append(i.getKey()).append("' was removed").append("\n");
                    } else if (i.getDifference().equals("added")) {
                        result.append("Property '").append(i.getKey()).append("' was added with value: ");
                        if (i.getValue2() instanceof String) {
                            result.append("'").append(i.getValue2()).append("'");
                        } else if (i.getValue2() instanceof Collection<?> || i.getValue2() instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append(i.getValue2());
                        }
                        result.append("\n");
                    } else if (i.getDifference().equals("changed")) {
                        result.append("Property '").append(i.getKey()).append("' was updated. From ");
                        if (i.getValue1() instanceof String) {
                            result.append("'").append(i.getValue1()).append("'");
                        } else if (i.getValue1() instanceof Collection<?> || i.getValue1() instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append(i.getValue1());
                        }
                        result.append(" to ");
                        if (i.getValue2() instanceof String) {
                            result.append("'").append(i.getValue2()).append("'");
                        } else if (i.getValue2() instanceof Collection<?> || i.getValue2() instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append(i.getValue2());
                        }
                        result.append("\n");
                    }
                });
        return String.valueOf(result).trim();
    }
    public static String json(List<DiffAnalizer> data) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder result = new StringBuilder();
        data.stream()
                .forEach(i -> {
                    try {
                        //result.append(objectMapper.writeValueAsString(i));
                        result.append(ow.writeValueAsString(i));
                        result.append("\n");
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
        return String.valueOf(result);
    }
}
