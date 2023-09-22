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
    @Option(names = {"-f", "--format"}, defaultValue = "", description = "output format [default: stylish]")
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
        // найдем отличия
        String result = String.valueOf(Differ.getDiff(filePath1, filePath2));
        System.out.println(result);
        return 0;
    }
}
