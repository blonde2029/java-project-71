package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import hexlet.code.formatters.Formatter;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
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

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    private static Map<String, Object> getData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        Files.exists(path);
        String content = Files.readString(path);
        //получим данные для соответствующего расширения
        String extension = getExtension(filePath);
        return Parser.getData(content, extension);

    }

    public static String getExtension(String filePath) {
        String extension = "";
        if (filePath.endsWith(".json")) {
            extension = "json";
        } else if (filePath.endsWith(".yml")) {
            extension = "yml";
        }
        return extension;
    }
}
