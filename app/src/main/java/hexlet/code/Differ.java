package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static StringBuilder getDiffJSON(String filePath1, String filePath2) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        System.out.println(path1);
        isFileExists(path1);
        isFileExists(path2);

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        Map<String, Object> data1 = Parser.getDataJSON(content1);
        Map<String, Object> data2 = Parser.getDataJSON(content2);
        java.util.TreeMap<String, Object> allData = new java.util.TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);

        java.util.List<String> diffs = buildResult(data1, data2, allData);
         //выведем теперь в строку в нужном формате
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        diffs.stream().forEach(i -> result.append(i + "\n"));
        result.append("}");
        return result;
    }
    public static StringBuilder getDiffYAML(String filePath1, String filePath2) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        System.out.println(path1);
        isFileExists(path1);
        isFileExists(path2);

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        Map<String, Object> data1 = Parser.getDataYAML(content1);
        Map<String, Object> data2 = Parser.getDataYAML(content2);
        java.util.TreeMap<String, Object> allData = new java.util.TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);

        java.util.List<String> diffs = buildResult(data1, data2, allData);
        //выведем теперь в строку в нужном формате
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        diffs.stream().forEach(i -> result.append(i + "\n"));
        result.append("}");
        return result;
    }
    private static List<String> buildResult(Map<String, Object> data1, Map<String, Object> data2,
                                            TreeMap<String, Object> allData) {
        java.util.List<String> diffs = new java.util.ArrayList<>();
        allData.entrySet().stream()
                .forEach(i -> {
                    if (data1.containsKey(i.getKey()) && !data2.containsKey(i.getKey())) {
                        diffs.add(" - " + i.getKey() + ": " + i.getValue());
                    } else if (data1.containsKey(i.getKey()) && data2.containsKey(i.getKey())
                            && data1.get(i.getKey()).equals(data2.get(i.getKey()))) {
                        diffs.add("   " + i.getKey() + ": " + i.getValue());
                    } else if (data1.containsKey(i.getKey()) && data2.containsKey(i.getKey())
                            && !data1.get(i.getKey()).equals(data2.get(i.getKey()))) {
                        diffs.add(" - " + i.getKey() + ": " + data1.get(i.getKey()));
                        diffs.add(" + " + i.getKey() + ": " + i.getValue());
                    } else if (!data1.containsKey(i.getKey()) && data2.containsKey(i.getKey())) {
                        diffs.add(" + " + i.getKey() + ": " + i.getValue());
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
