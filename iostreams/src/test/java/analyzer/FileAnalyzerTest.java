package analyzer;

//1: Написать программу FileAnalyzer, которая в консоли принимает 2 параметра:
//        1) путь к файлу
//        2) слово
//        Usage:
//        java FileAnalyzer C:/test/story.txt duck
//
//        Выводит:
//        1) Кол-во вхождений искомого слова в файле
//        2) Все предложения содержащие искомое слово(предложение заканчивается символами ".", "?", "!"), каждое предложение с новой строки.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileAnalyzerTest {
    private FileAnalyzer fileAnalyzer;
    private String filePath;
    private String word;

    @BeforeEach
    void setUp() {
        filePath = "Test word.txt";
        word = "Java";

        fileAnalyzer = new FileAnalyzer();
    }

    //    @Test
//    public void testFileIsPresent(){
//        File file = new File(fileAnalyzer.getFilePath());
//        File fakeFile = new File("/task.txt");
//
//        assertNotNull(file);
//        assertNull(fakeFile);
//    }
    @Test
    public void testReadFile() throws IOException {
        assertEquals("Hello JAVA!", fileAnalyzer.readFile(filePath));
    }

    @Test
    public void testFindWordInFile() throws IOException {
        assertEquals(1, fileAnalyzer.findWord(filePath, word));
    }

    @Test
    public void testFindSentenceInFileWithWord() throws IOException {
        fileAnalyzer.setFilePath("Test sentence.txt");
        assertEquals("Hello Java.\n" +
                "Hello Java?\n" +
                "Hello Java!\n" +
                "Hello  Hello Java.\n" +
                "Java!", fileAnalyzer.findSentence("Test sentence.txt", word));
    }


}