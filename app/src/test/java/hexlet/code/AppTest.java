package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    String path1;
    String path2;
    @BeforeEach
    void beforeEach() {
//        String path = "src/test/resources";
//
//        File file = new File(path);
//        String absolutePath = file.getAbsolutePath();
        path1 = "/home/blonde2029/java-project-71/app/src/test/resources/file1.json";
        path2 = "/home/blonde2029/java-project-71/app/src/test/resources/file2.json";
    }

    @Test
    public void appTest() throws IOException {
        String actual = String.valueOf(Differ.getDiff(path1, path2));
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
}
