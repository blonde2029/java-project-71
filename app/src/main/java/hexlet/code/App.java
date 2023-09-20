package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {
    @Option(names = {"-f", "--format"}, defaultValue = "", description = "output format [default: stylish]")
    private String format;
    @Parameters(index = "0", paramLabel = "filePath1", defaultValue = "", description = "path to first file")
    private String filePath1;
    @Parameters(index = "1", paramLabel = "filePath2", defaultValue = "", description = "path to second file")
    private String filePath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

    }
}
