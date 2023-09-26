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
    public void appTestJSON() throws IOException {
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
    public void appTestYAML() throws IOException {
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
