package fileManager;

import analyzer.FileAnalyzer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    private FileManager fileManager;
    private FileAnalyzer fileAnalyzer;

    @BeforeEach
    void setUp() throws IOException {
        fileManager = new FileManager();
        fileAnalyzer = new FileAnalyzer();

        new File("Directory for testing").mkdir();
        new File("Directory for testing\\Dir0").mkdir();
        new File("Directory for testing\\Dir2").mkdir();
        new File("Directory for testing\\Dir4").mkdir();
        new File("Directory for testing\\Dir4\\Dir6").mkdir();
        new File("Directory for testing\\Dir8").mkdir();
        new File("Directory for testing\\Dir4\\Dir6\\file1").createNewFile();
        new File("Directory for testing\\file3").createNewFile();
        new File("Directory for testing\\Dir4\\file5").createNewFile();
        new File("Directory for testing\\file7").createNewFile();
        new File("Directory for testing\\Copied file").createNewFile();

    }

    @AfterEach
    void tearDown() {
        File file = new File("Directory for testing");

        new File("Directory for testing\\Dir4\\Dir6\\file1").delete();
        new File("Directory for testing\\Copied file").delete();
        new File("Directory for testing\\file3").delete();
        new File("Directory for testing\\Dir4\\file5").delete();
        new File("Directory for testing\\file7").delete();
        new File("Directory for testing\\Copied file").delete();
        new File("Directory for testing\\Dir4\\Dir6").delete();
        new File("Directory for testing\\Dir0").delete();
        new File("Directory for testing\\Dir2").delete();
        new File("Directory for testing\\Dir4").delete();
        new File("Directory for testing\\Dir8").delete();
        new File("Directory for testing").delete();

    }


    @Test
    void countFiles() {
        assertEquals(5, fileManager.countFiles("Directory for testing"));
    }

    @Test
    void countDirs() {
        assertEquals(5, fileManager.countDirs("Directory for testing"));
    }

    @Test
    void copy() throws IOException {
        FileManager.copy("Test word.txt", "Directory for testing\\Copied file");
        fileAnalyzer = new FileAnalyzer();
        String expected = fileAnalyzer.readFile("Test word.txt");
        String actual = fileAnalyzer.readFile("Directory for testing\\Copied file");

        assertEquals(expected, actual);

    }
}