package hexlet.code;

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
                        result.append(" - " + i.getKey() + ": " + i.getValue1() + "\n");
                    } else if (i.getDifference().equals("added")) {
                        result.append(" + " + i.getKey() + ": " + i.getValue2() + "\n");
                    } else if (i.getDifference().equals("equal")) {
                        result.append("   " + i.getKey() + ": " + i.getValue1() + "\n");
                    } else if (i.getDifference().equals("changed")) {
                        result.append(" - " + i.getKey() + ": " + i.getValue1() + "\n");
                        result.append(" + " + i.getKey() + ": " + i.getValue2() + "\n");
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
                        result.append("Property '" + i.getKey() + "' was removed" + "\n");
                    } else if (i.getDifference().equals("added")) {
                        result.append("Property '" + i.getKey() + "' was added with value: ");
                        if (i.getValue2() instanceof String) {
                            result.append("'" + i.getValue2() + "'");
                        } else if (i.getValue2() instanceof Collection<?> || i.getValue2() instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append("" + i.getValue2());
                        }
                        result.append("\n");
                    } else if (i.getDifference().equals("changed")) {
                        result.append("Property '" + i.getKey() + "' was updated. From ");
                        if (i.getValue1() instanceof String) {
                            result.append("'" + i.getValue1() + "'");
                        } else if (i.getValue1() instanceof Collection<?> || i.getValue1() instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append("" + i.getValue1());
                        }
                        result.append(" to ");
                        if (i.getValue2() instanceof String) {
                            result.append("'" + i.getValue2() + "'");
                        } else if (i.getValue2() instanceof Collection<?> || i.getValue2() instanceof Map<?, ?>) {
                            result.append("[complex value]");
                        } else {
                            result.append("" + i.getValue2());
                        }
                        result.append("\n");
                    }
                });
        return String.valueOf(result);
    }
}
