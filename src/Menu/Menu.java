package Menu;

import java.util.ArrayList;
import java.util.Scanner;

import Battles.Battles;


import Droids.*;
import FileWorks.FileWorks;

public class Menu {

    private final ArrayList<BaseDroid> droids = new ArrayList<BaseDroid>();
    private final Scanner input = new Scanner(System.in);
    private FileWorks fileWorks;

    public Menu(){
        System.out.println("Введіть шлях до файлу для запису бою");
        var path = input.next();
        fileWorks = new FileWorks(path);
        Battles.fileWorks = fileWorks;


    }


    public void start(){
        while(true) {
            printMenu();
            int choice = safeScanInt(1, 6);
            switch (choice) {
                case 1:
                    fileWorks.printAll();
                    break;
                case 2:
                    droids.add(createDroid());
                    break;
                case 3:
                    printAllDroids();
                    break;
                case 4: {
                    if (droids.size() < 2) {
                        System.out.println("Створена недостання кількість дроїдів!");
                        break;
                    }
                    var droidsParticipants = chooseDroids(2);
                    Battles.fileWorks.reset();
                    Battles.oneVsOne(droidsParticipants[0], droidsParticipants[1]);
                    Battles.fileWorks.close();
                    break;
                }
                case 5: {
                    if (droids.size() < 6) {
                        System.out.println("Створена недостання кількість дроїдів!");
                        break;
                    }
                    var droidsParticipants = chooseDroidsForTeamFight(3);
                    Battles.fileWorks.reset();
                    Battles.threeVsThree(droidsParticipants[0], droidsParticipants[1]);
                    Battles.fileWorks.close();
                    break;
                }
                default:
                    return;
            }
        }
    }

    private void printMenu(){
        System.out.println("\n"+"""
                (1) Відтворити бій з файлу
                (2) Створити дроїда
                (3) Показати створених дроїдів
                (4) Бій 1 на 1
                (5) Бій 3 на 3
                (6) Завершити програму""");
    }

    private void printAllDroids(){
        int i = 1;
        System.out.println("Створені дроїди:");
        for (var droid: droids) {
            System.out.printf("#%d. Name = %s\n\tType = %s\n",i++,droid.getName(),droid.getType());
        }
    }

    private int safeScanInt(int start, int end){
        int result;
        while(true){
            try{
                result = input.nextInt();
                if (result < start || result > end) throw new Exception();
                return result;
            }catch (Exception e){
                System.out.println("invalid input! try again");
            }
        }
    }
    private BaseDroid createDroid(){
        System.out.println("""
                Оберіть клас дроїда:
                (1) Танк
                (2) Асасин
                (3) Чаклун
                (4) Лікар
                (5) Берсерк""");
        int droidType = safeScanInt(1,5);
        System.out.println("Введіть ім'я дроїда");
        var name = input.next();
        switch (droidType){
            case 1:
                return new TankDroid(name,fileWorks);
            case 2:
                return new AssassinDroid(name,fileWorks);
            case 3:
                return new WizardDroid(name,fileWorks);
            case 4:
                return new HealerDroid(name,fileWorks);
            default:
                return new BerserkDroid(name,fileWorks);
        }
    }

    private BaseDroid[] chooseDroids(int count){
        int[] used = new int[count];
        var result = new BaseDroid[count];
        int maxCount = droids.size();
        printAllDroids();
        for (int i = 0; i < count; i++){
            int n;
            while (true) {
                n = safeScanInt(1,maxCount);
                if(!arrayContains(used,n)) break;
                System.out.println("Цей дроїд вже був обратний");
            }
            used[i] = n;
            System.out.println("Дроїда додано");
            result[i] = droids.get(n - 1);
        }
        return result;
    }

    private BaseDroid[][] chooseDroidsForTeamFight (int count){
        int[] used = new int[2*count];
        var result = new BaseDroid[2][count];
        int maxCount = droids.size();
        printAllDroids();
        for(int j = 0; j<2;j++ ) {
            System.out.println("Оберіть дроїдів для команди"+(j+1));
            for (int i = 0; i < count; i++) {
                int n;
                while (true) {
                    n = safeScanInt(1, maxCount);
                    if (!arrayContains(used, n)) break;
                    System.out.println("Цей дроїд вже був обратний");
                }
                used[i+3*j] = n;
                System.out.println("Дроїда додано");
                result[j][i] = droids.get(n - 1);
            }
        }
        return result;
    }

    private boolean arrayContains(int[] used, int value){
        for (int i : used) {
            if (i == value) return true;
        }
        return false;
    }


}
