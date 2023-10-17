package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(List<Map<Object, Object>> data) {
        StringBuilder result = new StringBuilder();
        data.stream()
                .forEach(i -> {
                    if (i.get("changeType").equals("removed")) {
                        result.append("Property '").append(i.get("field")).append("' was removed").append("\n");
                    } else if (i.get("changeType").equals("added")) {
                        result.append("Property '").append(i.get("field")).append("' was added with value: ");
                        result.append(getDiffString(i.get("newValue")));
                        result.append("\n");
                    } else if (i.get("changeType").equals("changed")) {
                        result.append("Property '").append(i.get("field")).append("' was updated. From ");
                        result.append(getDiffString(i.get("oldValue")));
                        result.append(" to ");
                        result.append(getDiffString(i.get("newValue")));
                        result.append("\n");
                    }
                });
        return String.valueOf(result).trim();
    }

    public static String getDiffString(Object value) {
        StringBuilder result = new StringBuilder();
        if (value instanceof String) {
            result.append("'").append(value).append("'");
        } else if (value instanceof Collection<?>
                || value instanceof Map<?, ?>) {
            result.append("[complex value]");
        } else {
            result.append(value);
        }
        return result.toString();
    }
}
