package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    String path1;
    String path2;
    String absolutePath;
    @BeforeEach
    void beforeEach() {
        String path = "src/test/resources";
        File file = new File(path);
        absolutePath = file.getAbsolutePath();
    }

    @Test
    public void appTestJSONStylish() throws IOException {
        path1 = absolutePath + "/file1.json";
        path2 = absolutePath + "/file2.json";
        String actual = Formatter.stylish(Differ.getDiffJSON(path1, path2));
        String expected = """
                 {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                 }""";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestJSONPlain() throws IOException {
        path1 = absolutePath + "/file1.json";
        path2 = absolutePath + "/file2.json";
        String actual = Formatter.plain(Differ.getDiffJSON(path1, path2));
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLStylish() throws IOException {
        path1 = absolutePath + "/file1.yml";
        path2 = absolutePath + "/file2.yml";
        String actual = Formatter.stylish(Differ.getDiffYAML(path1, path2));
        String expected = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 29
                 + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
    }
}
