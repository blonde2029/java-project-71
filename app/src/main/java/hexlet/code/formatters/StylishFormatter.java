package hexlet.code.formatters;

import hexlet.code.DiffAnalizer;

import java.util.List;

public class StylishFormatter {
    public static String format(List<DiffAnalizer> data) {
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
}
