package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import hexlet.code.formatters.Formatter;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> data1 = getData(filePath1);
        Map<String, Object> data2 = getData(filePath2);
        TreeMap<String, Object> allData = new TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);
        //получим отличия содержимого файлов
        List<Map<Object, Object>> data = Comparator.compare(data1, data2, allData);
        //выведем различия в нужном формате
        return Formatter.format(data, format);
    }
    public static String generate(String filePath1, String filePath2) throws IOException {
        return generate(filePath1, filePath2, "stylish");
    }
    private static Map<String, Object> getData(String filePath) throws IOException {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        Files.exists(path);
        String content = Files.readString(path);
        Map<String, Object> data = new TreeMap<>();
//        if (filePath.endsWith(".json")) {
//            data = Parser.getDataJSON(content);
//        } else if (filePath.endsWith(".yml")) {
//            data = Parser.getDataYAML(content);
//        }
        if (filePath.endsWith(".json")) {
            data = Parser.getData(content, "json");
        } else if (filePath.endsWith(".yml")) {
            data = Parser.getData(content, "yml");
        }
        return data;
    }
}
