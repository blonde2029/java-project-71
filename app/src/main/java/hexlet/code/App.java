package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
//
//
    @Override
    public Integer call() throws IOException {
        filePath1 = "file1.json";
        filePath2 = "file2.json";
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        // проверим существование файлов
        isFileExists(path1);
        isFileExists(path2);
        // получим содержимое файлов
        String content1 = null;
        content1 = Files.readString(path1);
        String content2 = null;
        content2 = Files.readString(path2);
        // найдем отличия
        String result = String.valueOf(Differ.getDiff(content1, content2));
        System.out.println(result);
        return 0;
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
