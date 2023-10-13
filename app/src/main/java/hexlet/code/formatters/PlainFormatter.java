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
                        if (i.get("newValue") instanceof String) {
                            result.append("'").append(i.get("newValue")).append("'");
                        } else if (i.get("newValue") instanceof Collection<?>
                                || i.get("newValue") instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append(i.get("newValue"));
                        }
                        result.append("\n");
                    } else if (i.get("changeType").equals("changed")) {
                        result.append("Property '").append(i.get("field")).append("' was updated. From ");
                        if (i.get("oldValue") instanceof String) {
                            result.append("'").append(i.get("oldValue")).append("'");
                        } else if (i.get("oldValue") instanceof Collection<?>
                                || i.get("oldValue") instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append(i.get("oldValue"));
                        }
                        result.append(" to ");
                        if (i.get("newValue") instanceof String) {
                            result.append("'").append(i.get("newValue")).append("'");
                        } else if (i.get("newValue") instanceof Collection<?>
                                || i.get("oldValue") instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append(i.get("newValue"));
                        }
                        result.append("\n");
                    }
                });
        return String.valueOf(result).trim();
    }
}
