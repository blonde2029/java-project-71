package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<Object, Object>> data, String format) {
        String result;
        if (format.equals("plain")) {
            result = PlainFormatter.format(data);
        } else if (!format.equals("json")) {
            result = StylishFormatter.format(data);
        } else {
            result = JsonFormatter.format(data);
        }
        return result;
    }
}
