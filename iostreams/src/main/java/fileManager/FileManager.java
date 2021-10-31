package fileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {
    private static int countFiles;
    private static int countDirs;

    // public static int countFiles(String path) - принимает путь к папке,
// возвращает количество файлов в папке и всех подпапках по пути
    public static int countFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isFile()) {
                    countFiles++;
                } else {
                    countFiles(file1.getPath());
                }
            }
        }
        return countFiles;
    }

    // public static int countDirs(String path) - принимает путь к папке,
// возвращает количество папок в папке и всех подпапках по пути
    public static int countDirs(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    countDirs++;
                    countDirs(file1.getPath());
                }
            }
        }
        return countDirs;
    }

    //    - метод по копированию папок и файлов.
//    Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
//    public static void move(String from, String to) - метод по перемещению папок и файлов.
//    Параметр from - путь к файлу или папке, параметр to - путь к папке куда будет производиться копирование.
    public static void copy(String from, String to) {
        try (FileInputStream fis = new FileInputStream(from);
             FileOutputStream fos = new FileOutputStream(to)) {
            byte[] bufer = new byte[1024];
            int byteread = 0;
            while ((byteread = fis.read(bufer)) > 0){
                fos.write(bufer, 0, byteread);
                fos.close();
                fis.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FileManager.copy("Test word.txt", "Test copy.txt");
    }
}
