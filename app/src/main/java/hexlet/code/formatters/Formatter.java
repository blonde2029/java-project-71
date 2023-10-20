package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<Object, Object>> data, String format) throws Exception {
        String result;
        if (format.equals("plain")) {
            result = PlainFormatter.format(data);
        } else if (format.equals("stylish")) {
            result = StylishFormatter.format(data);
        } else if (format.equals("json")) {
            result = JsonFormatter.format(data);
        } else {
            throw new Exception("Unknown format: '" + format + "'");
        }
        return result;
    }
}
