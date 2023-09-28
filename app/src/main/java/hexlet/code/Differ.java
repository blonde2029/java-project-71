package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import hexlet.code.formatters.Formatter;


public class Differ {
    public static String getDiff(String filePath1, String filePath2, String format) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        //проверим существуют ли файлы
        isFileExists(path1);
        isFileExists(path2);
        //получим содержимое файлов
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);
        Map<String, Object> data1 = new TreeMap<>();
        Map<String, Object> data2 = new TreeMap<>();
        if (filePath1.endsWith(".json") && filePath2.endsWith(".json")) {
            data1 = Parser.getDataJSON(content1);
            data2 = Parser.getDataJSON(content2);
        } else if (filePath1.endsWith(".yml") && filePath2.endsWith(".yml")) {
            data1 = Parser.getDataYAML(content1);
            data2 = Parser.getDataYAML(content2);
        }
        TreeMap<String, Object> allData = new TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);
        //получим отличия содержимого файлов
        List<DiffAnalizer> data = buildResult(data1, data2, allData);
        String result;
        //выведем различия в нужном формате
        if (format.equals("plain")) {
            result = Formatter.plain(data);
        } else if (!format.equals("json")) {
            result = Formatter.stylish(data);
        } else {
            result = Formatter.json(data);
        }
        return result;
    }
    private static List<DiffAnalizer> buildResult(Map<String, Object> data1, Map<String, Object> data2,
                                            TreeMap<String, Object> allData) {
        List<DiffAnalizer> diffs = new ArrayList<>();

        allData.keySet()
                .forEach(i -> {
                    if (!data2.containsKey(i)) {
                        diffs.add(new DiffAnalizer(i, data1.get(i), null, "removed"));
                    } else if (!data1.containsKey(i) && data2.containsKey(i)) {
                        diffs.add(new DiffAnalizer(i, null, data2.get(i), "added"));
                    } else if (data1.containsKey(i) && data2.containsKey(i)) {
                        if (data1.get(i) == null || data2.get(i) == null) {
                            diffs.add(new DiffAnalizer(i, data1.get(i), data2.get(i), "changed"));
                        } else if (data1.get(i).equals(data2.get(i))) {
                            diffs.add(new DiffAnalizer(i, allData.get(i), allData.get(i), "equal"));
                        } else if (data1.get(i) == data2.get(i)) {
                            diffs.add(new DiffAnalizer(i, allData.get(i), allData.get(i), "equal"));
                        } else {
                            diffs.add(new DiffAnalizer(i, data1.get(i), data2.get(i), "changed"));
                        }
                    }

                });
        return diffs;
    }

    public static void isFileExists(Path path) {
        if (!Files.exists(path)) {
            try {
                throw new Exception("File '" + path + "' does not exist");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
