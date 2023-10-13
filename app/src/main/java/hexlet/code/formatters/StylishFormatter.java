package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(List<Map<Object, Object>> data) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        data.stream()
                .forEach(i -> {
                    if (i.get("changeType").equals("removed")) {
                        result.append("  - ")
                                .append(i.get("field"))
                                .append(": ")
                                .append(i.get("oldValue"))
                                .append("\n");
                    } else if (i.get("changeType").equals("added")) {
                        result.append("  + ")
                                .append(i.get("field"))
                                .append(": ")
                                .append(i.get("newValue"))
                                .append("\n");
                    } else if (i.get("changeType").equals("equal")) {
                        result.append("    ")
                                .append(i.get("field"))
                                .append(": ")
                                .append(i.get("newValue"))
                                .append("\n");
                    } else if (i.get("changeType").equals("changed")) {
                        result.append("  - ")
                                .append(i.get("field"))
                                .append(": ")
                                .append(i.get("oldValue"))
                                .append("\n");
                        result.append("  + ")
                                .append(i.get("field"))
                                .append(": ")
                                .append(i.get("newValue"))
                                .append("\n");
                    }
                });
        result.append("}");
        return String.valueOf(result);
    }
}
