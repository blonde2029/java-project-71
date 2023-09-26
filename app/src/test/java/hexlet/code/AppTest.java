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
        String actual = String.valueOf(Differ.getDiffJSON(path1, path2));
        String expected = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 21
                 + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAML() throws IOException {
        path1 = absolutePath + "/file1.yml";
        path2 = absolutePath + "/file2.yml";
        String actual = String.valueOf(Differ.getDiffYAML(path1, path2));
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
