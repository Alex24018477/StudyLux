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

        File file = new File("Directory for testing");
        file.mkdir();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                File file1 = new File(file.getAbsolutePath() + "\\" + "Directory" + i);
                file1.mkdir();
            } else {
                if (i > 5) {
                    File file1 = new File(file.getAbsolutePath() + "\\" + "Directory4" + "\\" + "file" + i);
                    file1.createNewFile();
                } else {
                    File file1 = new File(file.getAbsolutePath() + "\\" + "Directory0" + "\\" + "file" + i);
                    file1.createNewFile();
                }


            }
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File("Directory for testing");

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    File[] files2 = file1.listFiles();
                    for (File file3 : files2) {
                        file3.delete();
                    }
                }
                file1.delete();
            }
        }
        file.delete();
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
        FileManager.copy("Test word.txt", "Copied file");
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        String expected = fileAnalyzer.readFile("Test word.txt");
        String actual = fileAnalyzer.readFile("Copied file");

        assertEquals(expected,actual);

    }


}