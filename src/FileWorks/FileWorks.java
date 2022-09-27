package FileWorks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWorks {
    private FileWriter fileWriter;
    private String path;

    public FileWorks(String path){
        this.path = path;
        try {
            fileWriter = new FileWriter(new File(path),false);
        }
        catch (Exception e) {
            System.out.println("Не вдалося відкрити файл!");
            System.exit(1);
        }
    }

    public void printAll(){
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(new File(path));
        } catch (Exception e) {
            System.out.println("Не вдалося відкрити файл!");
            System.exit(1);
        }
        while (fileScan.hasNext())
            System.out.println(fileScan.nextLine());
        fileScan.close();
    }

    public void print(String message){
        try {
            fileWriter.write(message + "\n");
        }
        catch (Exception e){
            System.out.println("Не вдалося записати значення");
        }
    }

    public void close() {
        try {
            fileWriter.close();
        }
        catch (Exception e) {
            System.out.println("Не вдалось закрити файл");
            System.exit(1);
        }
    }
    public void reset(){
        try {
            fileWriter = new FileWriter(new File(path),false);
        }
        catch (Exception e) {
            System.out.println("Не вдалося відкрити файл!");
            System.exit(1);
        }
    }
}
