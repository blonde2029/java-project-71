package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, defaultValue = "${FORMAT:-stylish}",
            description = "output format [${FORMAT:-stylish}]")
    private String format;
    @Parameters(index = "0", paramLabel = "filePath1", defaultValue = "", description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", paramLabel = "filePath2", defaultValue = "", description = "path to second file")
    private String filePath2;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws IOException {
        filePath1 = "file1.json";
        filePath2 = "file2.json";
        format = "json";
        // найдем отличия
        if (filePath1.endsWith(".json") && filePath2.endsWith(".json")) {
            String result = Differ.getDiff(filePath1, filePath2, format);
            System.out.println(result);
        } else if (filePath1.endsWith(".yml") && filePath2.endsWith(".yml")) {
            String result = Differ.getDiff(filePath1, filePath2, format);
            System.out.println(result);
        }
        return 0;
    }
}
