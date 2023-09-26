package hexlet.code;

import java.util.List;

public class Formatter {
    public static String stylish(List<DiffAnalizer> data) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        data.stream()
                .forEach(i -> {
                    if (i.getDifference().equals("-")) {
                        result.append(" - " + i.getKey() + ": " + i.getValue() + "\n");
                    } else if (i.getDifference().equals("+")) {
                        result.append(" + " + i.getKey() + ": " + i.getValue() + "\n");
                    } else if (i.getDifference().equals("e")) {
                        result.append("   " + i.getKey() + ": " + i.getValue() + "\n");
                    }
                });
        result.append("}");
        return String.valueOf(result);
    }
}
