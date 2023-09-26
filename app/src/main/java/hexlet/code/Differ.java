package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static List<DiffAnalizer> getDiffJSON(String filePath1, String filePath2) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        isFileExists(path1);
        isFileExists(path2);

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        Map<String, Object> data1 = Parser.getDataJSON(content1);
        Map<String, Object> data2 = Parser.getDataJSON(content2);
        TreeMap<String, Object> allData = new java.util.TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);

        List<DiffAnalizer> diffs = buildResult(data1, data2, allData);
        return diffs;
    }
    public static List<DiffAnalizer> getDiffYAML(String filePath1, String filePath2) throws IOException {
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        isFileExists(path1);
        isFileExists(path2);

        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        Map<String, Object> data1 = Parser.getDataYAML(content1);
        Map<String, Object> data2 = Parser.getDataYAML(content2);
        TreeMap<String, Object> allData = new TreeMap<>();
        allData.putAll(data1);
        allData.putAll(data2);

        List<DiffAnalizer> diffs = buildResult(data1, data2, allData);
        return diffs;
    }
    private static List<DiffAnalizer> buildResult(Map<String, Object> data1, Map<String, Object> data2,
                                            TreeMap<String, Object> allData) {
        List<DiffAnalizer> diffs = new ArrayList<>();

        allData.keySet()
                .forEach(i -> {
                    if (!data2.containsKey(i)) {
                        diffs.add(new DiffAnalizer(i, allData.get(i), "-"));
                    } else if (!data1.containsKey(i) && data2.containsKey(i)) {
                        diffs.add(new DiffAnalizer(i, allData.get(i), "+"));
                    } else if (data1.containsKey(i) && data2.containsKey(i)) {
                        if (data1.get(i) == null || data2.get(i) == null) {
                            diffs.add(new DiffAnalizer(i, data1.get(i), "-"));
                            diffs.add(new DiffAnalizer(i, data2.get(i), "+"));
                        } else if (data1.get(i).equals(data2.get(i))) {
                            diffs.add(new DiffAnalizer(i, allData.get(i), "e"));
                        } else if (data1.get(i) == data2.get(i)) {
                            diffs.add(new DiffAnalizer(i, allData.get(i), "e"));
                        } else {
                            diffs.add(new DiffAnalizer(i, data1.get(i).toString(), "-"));
                            diffs.add(new DiffAnalizer(i, data2.get(i).toString(), "+"));
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
