package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {
    public static StringBuilder getDiff(String filePath1, String filePath2) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        System.out.println(path1);
        isFileExists(path1);
        isFileExists(path2);

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        Map<String, Object> data1 = getData(content1);
        Map<String, Object> data2 = getData(content2);
        java.util.TreeMap<String, Object> allData = new java.util.TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);

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
         //выведем теперь в строку в нужном формате
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        diffs.stream().forEach(i -> result.append(i + "\n"));
        result.append("}");
        return result;
    }
    public static Map<String, Object> getData(String content) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = null;
        TypeReference<java.util.HashMap<String, Object>> typeReference = new TypeReference<>() { };
        try {
            data = mapper.readValue(content, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
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