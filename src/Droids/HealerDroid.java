package Droids;

import ConsoleColors.ConsoleColors;
import FileWorks.FileWorks;

import java.sql.SQLOutput;

public class HealerDroid extends BaseDroid{
    public HealerDroid(String name, FileWorks fileWorks){
        super("Лікар" ,name, 125, 0, 45, 0, 10,fileWorks);
    }

    @Override
    public void attack(BaseDroid[] team) {
        BaseDroid droid = null;
        int healthDifference = 0;
        for (var teammate: team) {
            int diff = teammate.DEFAULTHEALTHPOINTS - teammate.getHealthPoints();
            if (diff > healthDifference){
                healthDifference = diff;
                droid = teammate;
            }
        }
        if (healthDifference == 0){
            for (var teammate: team) {
                teammate.buffAttack(1.2);
            }
            print(ConsoleColors.GREEN + "Дроїд " + name + " збільшив атаку команди на 20% "+ConsoleColors.RESET);
        }else {
            droid.heal(DEFAULTHEALTHPOINTS / 5);
            print(ConsoleColors.GREEN + "Дроїд " + name + " відновив 25 здоров'я дроїда " + droid.getName()+ConsoleColors.RESET);
        }
    }
}
