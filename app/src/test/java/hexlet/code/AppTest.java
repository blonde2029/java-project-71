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
        String format = "stylish";
        String actual = Differ.generate(path1, path2, format);
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
        String actual2 = Differ.generate(path1, path2);
        assertThat(actual2).isEqualTo(expected);
    }
    @Test
    public void appTestJSONPlain() throws IOException {
        path1 = absolutePath + "/file1.json";
        path2 = absolutePath + "/file2.json";
        String format = "plain";
        String actual = Differ.generate(path1, path2, format);
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
    public void appTestJSONJson() throws IOException {
        path1 = absolutePath + "/file1.json";
        path2 = absolutePath + "/file2.json";
        String format = "json";
        String actual = Differ.generate(path1, path2, format);
        String expected = """
                  {
                    "key" : "chars1",
                    "value1" : [ "a", "b", "c" ],
                    "value2" : [ "a", "b", "c" ],
                    "difference" : "equal"
                  }
                  {
                    "key" : "chars2",
                    "value1" : [ "d", "e", "f" ],
                    "value2" : false,
                    "difference" : "changed"
                  }
                  {
                    "key" : "checked",
                    "value1" : false,
                    "value2" : true,
                    "difference" : "changed"
                  }
                  {
                    "key" : "default",
                    "value1" : null,
                    "value2" : [ "value1", "value2" ],
                    "difference" : "changed"
                  }
                  {
                    "key" : "id",
                    "value1" : 45,
                    "value2" : null,
                    "difference" : "changed"
                  }
                  {
                    "key" : "key1",
                    "value1" : "value1",
                    "value2" : null,
                    "difference" : "removed"
                  }
                  {
                    "key" : "key2",
                    "value1" : null,
                    "value2" : "value2",
                    "difference" : "added"
                  }
                  {
                    "key" : "numbers1",
                    "value1" : [ 1, 2, 3, 4 ],
                    "value2" : [ 1, 2, 3, 4 ],
                    "difference" : "equal"
                  }
                  {
                    "key" : "numbers2",
                    "value1" : [ 2, 3, 4, 5 ],
                    "value2" : [ 22, 33, 44, 55 ],
                    "difference" : "changed"
                  }
                  {
                    "key" : "numbers3",
                    "value1" : [ 3, 4, 5 ],
                    "value2" : null,
                    "difference" : "removed"
                  }
                  {
                    "key" : "numbers4",
                    "value1" : null,
                    "value2" : [ 4, 5, 6 ],
                    "difference" : "added"
                  }
                  {
                    "key" : "obj1",
                    "value1" : null,
                    "value2" : {
                      "nestedKey" : "value",
                      "isNested" : true
                    },
                    "difference" : "added"
                  }
                  {
                    "key" : "setting1",
                    "value1" : "Some value",
                    "value2" : "Another value",
                    "difference" : "changed"
                  }
                  {
                    "key" : "setting2",
                    "value1" : 200,
                    "value2" : 300,
                    "difference" : "changed"
                  }
                  {
                    "key" : "setting3",
                    "value1" : true,
                    "value2" : "none",
                    "difference" : "changed"
                  }
                  """;
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLStylish1() throws IOException {
        path1 = absolutePath + "/file1.yml";
        path2 = absolutePath + "/file2.yml";
        String format = "stylish";
        String actual = Differ.generate(path1, path2, format);
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
        String actual2 = Differ.generate(path1, path2);
        assertThat(actual2).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLStylish2() throws IOException {
        path1 = absolutePath + "/file1small.yml";
        path2 = absolutePath + "/file2small.yml";
        String format = "stylish";
        String actual = Differ.generate(path1, path2, format);
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
        String actual2 = Differ.generate(path1, path2);
        assertThat(actual2).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLPlain1() throws IOException {
        path1 = absolutePath + "/file1.yml";
        path2 = absolutePath + "/file2.yml";
        String format = "plain";
        String actual = Differ.generate(path1, path2, format);
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
    public void appTestYAMLPlain2() throws IOException {
        path1 = absolutePath + "/file1small.yml";
        path2 = absolutePath + "/file2small.yml";
        String format = "plain";
        String actual = Differ.generate(path1, path2, format);
        String expected = """
                Property 'follow' was removed
                Property 'proxy' was removed
                Property 'timeout' was updated. From 50 to 29
                Property 'verbose' was added with value: true
                """;
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLJson() throws IOException {
        path1 = absolutePath + "/file1small.yml";
        path2 = absolutePath + "/file2small.yml";
        String format = "json";
        String actual = Differ.generate(path1, path2, format);
        String expected = """
                  {
                    "key" : "follow",
                    "value1" : false,
                    "value2" : null,
                    "difference" : "removed"
                  }
                  {
                    "key" : "host",
                    "value1" : "hexlet.io",
                    "value2" : "hexlet.io",
                    "difference" : "equal"
                  }
                  {
                    "key" : "proxy",
                    "value1" : "123.234.53.22",
                    "value2" : null,
                    "difference" : "removed"
                  }
                  {
                    "key" : "timeout",
                    "value1" : 50,
                    "value2" : 29,
                    "difference" : "changed"
                  }
                  {
                    "key" : "verbose",
                    "value1" : null,
                    "value2" : true,
                    "difference" : "added"
                  }
                  """;
        assertThat(actual).isEqualTo(expected);
    }
}
