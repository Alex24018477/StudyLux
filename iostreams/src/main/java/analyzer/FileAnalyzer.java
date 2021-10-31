package analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class FileAnalyzer {
    private String filePath;
    private String word;

    public FileAnalyzer() {
    }

    public FileAnalyzer(String filePath, String word) {
        this.filePath = filePath;
        this.word = word;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

//    public String getTextFromFile() {
//        return textFromFile;
//    }

    //    public String readFromFile(){
//        return "";
//    }


    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String string = "";
        while ((string = bufferedReader.readLine()) != null){
            sb.append(string);
        }
//
//        InputStream inputStream = new FileInputStream(filePath);
//        StringBuilder sb = new StringBuilder();
//
//        int i;
//        while ((i = inputStream.read()) != -1) {
//            sb.append((char) i);
//        }
        return sb.toString();
    }

    public int findWord(String filePath, String word) throws IOException {
        int count = 0;
        String textFromFile = readFile(filePath);
        if (textFromFile.length() != 0) {
            String[] wordsFromText = textFromFile.split(" ");
            for (String wordFromText : wordsFromText) {
                if (wordFromText.toUpperCase().contains(word.toUpperCase())) {
                    count++;
                }
            }
        }
        return count;
    }

    public String findSentence(String filePath,String word) throws IOException {
        StringJoiner sj = new StringJoiner("\n");
        for (String sentence : findAllSentences(filePath)) {
            if (sentence.toUpperCase().contains(word.toUpperCase())) {
                sj.add(sentence);
            }
        }
        return sj.toString();
    }

    private List<String> findAllSentences(String filePath) throws IOException {
        List<String> allSentences = new ArrayList<String>();
        String textFromFile = readFile(filePath);
        if (textFromFile.length() != 0) {
            String[] wordsFromText = textFromFile.split(" ");
            StringJoiner sj = new StringJoiner(" ");
            for (String wordFromText : wordsFromText) {
                sj.add(wordFromText);
                if (wordFromText.endsWith(".") || wordFromText.endsWith("?") || wordFromText.endsWith("!")) {
                    allSentences.add(sj.toString());
                    sj = new StringJoiner(" ");
                }
            }
        }
        return allSentences;
    }

    public static void main(String[] args) throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
//        System.out.println(fileAnalyzer.readFile("Test sentence.txt"));
        for (String string: fileAnalyzer.findAllSentences("Test sentence.txt")){
            System.out.println(string);
        }
    }
}
