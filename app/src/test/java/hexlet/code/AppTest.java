package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    private String path1Json;
    private String path2Json;
    private String path1Yaml;
    private String path2Yaml;
    private String absolutePath;
    @BeforeEach
    final void beforeEach() {
        String path = "src/test/resources";
        File file = new File(path);
        absolutePath = file.getAbsolutePath();
        path1Json = absolutePath + "/file1.json";
        path2Json = absolutePath + "/file2.json";
        path1Yaml = absolutePath + "/file1.yml";
        path2Yaml = absolutePath + "/file2.yml";
    }
    @Test
    public void appTestJSON2Arguments() throws Exception {
        String expected = readFromFixture("ExpectedStylish.txt");
        String actual2 = Differ.generate(path1Json, path2Json);
        assertThat(actual2).isEqualTo(expected);
    }
    @Test
    public void appTestYAML2Arguments() throws Exception {
        String expected = readFromFixture("ExpectedStylish.txt");
        String actual2 = Differ.generate(path1Yaml, path2Yaml);
        assertThat(actual2).isEqualTo(expected);
    }
    @Test
    public void appTestJSONStylish() throws Exception {
        String actual = Differ.generate(path1Json, path2Json, "stylish");
        String expected = readFromFixture("ExpectedStylish.txt");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestJSONPlain() throws Exception {
        String actual = Differ.generate(path1Json, path2Json, "plain");
        String expected = readFromFixture("ExpectedPlain.txt");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestJSONJson() throws Exception {
        String actual = Differ.generate(path1Json, path2Json, "json");
        String expected = readFromFixture("ExpectedJson.txt");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLStylish() throws Exception {
        String actual = Differ.generate(path1Yaml, path2Yaml, "stylish");
        String expected = readFromFixture("ExpectedStylish.txt");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLPlain() throws Exception {
        String actual = Differ.generate(path1Yaml, path2Yaml, "plain");
        String expected = readFromFixture("ExpectedPlain.txt");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void appTestYAMLJson() throws Exception {
        String actual = Differ.generate(path1Yaml, path2Yaml, "json");
        String expected = readFromFixture("ExpectedJson.txt");
        assertThat(actual).isEqualTo(expected);
    }
    public final String readFromFixture(String fixture) throws IOException {
        return Files.readString(Path.of(absolutePath + "/" + fixture)).trim();
    }
}
