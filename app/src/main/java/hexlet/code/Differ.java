package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Differ {
     public static StringBuilder getDiff(String content1, String content2) {
        Map<String, Object> data1 = getData(content1);
        Map<String, Object> data2 = getData(content2);
         TreeMap<String, Object> allData = new TreeMap<>();
         allData.putAll(data1);
         allData.putAll(data2);

         List<String> diffs = new ArrayList<>();
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
         diffs.stream().forEach(i -> {
             result.append(i + "\n");
         });
         result.append("}");
         return result;
    }
    public static Map<String, Object> getData(String content) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = null;
        try {
            data = mapper.readValue(content, HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
