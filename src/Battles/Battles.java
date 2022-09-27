package Battles;

import ConsoleColors.ConsoleColors;
import Droids.BaseDroid;
import Droids.HealerDroid;
import FileWorks.FileWorks;

import java.util.Arrays;
import java.util.Comparator;


public class Battles {
    public static FileWorks fileWorks;
    public static void oneVsOne(BaseDroid droid1, BaseDroid droid2){
        for(int i = 1; ;i++){
            if (droid2.getSpeedPoints() > droid1.getSpeedPoints()){
                if (oneVsOneRound(droid2,droid1,i)) break;
            }else if(droid1.getSpeedPoints() == droid2.getSpeedPoints()){
                if (Math.random() < 0.5){
                    if (oneVsOneRound(droid1,droid2,i)) break;
                }else
                    if (oneVsOneRound(droid2,droid1,i)) break;
            }
            if (oneVsOneRound(droid1,droid2,i)) break;
        }
        if (droid1.getIsAlive())
            printWinner(droid1);
        else
            printWinner(droid2);
        droid1.reset();
        droid2.reset();
    }
    private static boolean oneVsOneRound(BaseDroid droid1, BaseDroid droid2, int number){
        print( "\n\t\t"+ ConsoleColors.YELLOW_UNDERLINED +"Раунд " + number + ConsoleColors.RESET);
        droid1.attack(new BaseDroid[]{droid2});
        if (!(droid1.getIsAlive()&&droid2.getIsAlive())) return true;
        droid2.attack(new BaseDroid[]{droid1});
        return !(droid1.getIsAlive() && droid2.getIsAlive());
    }
    private static void printWinner(BaseDroid droid){
        print(ConsoleColors.BLUE_UNDERLINED + "\nПереможець - дроїд " + droid.getName() + ConsoleColors.RESET);
    }

    public static void threeVsThree(BaseDroid[] team1, BaseDroid[] team2){
        for(int i = 1; ;i++) {
            if (getTeamTotalSpeed(team1) < getTeamTotalSpeed(team2)) {
                if (threeVsThreeRound(team2, team1, i)) break;
            }else if(getTeamTotalSpeed(team1) == getTeamTotalSpeed(team2)){
                if (Math.random() < 0.5){
                    if (threeVsThreeRound(team1,team2,i)) break;
                }else
                    if (threeVsThreeRound(team2,team1,i)) break;
            }else
                if (threeVsThreeRound(team1, team2, i)) break;
        }
        if (isTeamAlive(team1))
            printWinnerTeam(1);
        else
            printWinnerTeam(2);
        for (var droid: team1)
            droid.reset();
        for (var droid: team2)
            droid.reset();
    }

    private static boolean threeVsThreeRound(BaseDroid[] team1, BaseDroid[] team2, int number){
        print( "\n\t\t"+ ConsoleColors.YELLOW_UNDERLINED +"Раунд " + number + ConsoleColors.RESET);
        team1 = Arrays.stream(team1).sorted(Comparator.comparingInt(BaseDroid::getSpeedPoints).reversed())
                .toArray(BaseDroid[]::new);
        for (var droid: team1) {
            if (!isTeamAlive(team2)) return true;
            if (!droid.getIsAlive()) continue;
            if (droid instanceof HealerDroid)
                droid.attack(team1);
            else
                droid.attack(team2);

        }
        team2 = Arrays.stream(team2).sorted(Comparator.comparingInt(BaseDroid::getSpeedPoints).reversed())
                .toArray(BaseDroid[]::new);
        System.out.println("");
        for (var droid: team2) {
            if (!isTeamAlive(team1)) return true;
            if (!droid.getIsAlive()) continue;
            if (droid instanceof HealerDroid)
                droid.attack(team2);
            else
                droid.attack(team1);

        }
        return false;
    }

    private static boolean isTeamAlive(BaseDroid[] team){
        for (var droid : team)
            if (droid.getIsAlive()) return true;
        return false;
    }

    private static int getTeamTotalSpeed(BaseDroid[] team){
        int result = 0;
        for (var droid:team)
            result += droid.getSpeedPoints();
        return result;
    }

    private static void printWinnerTeam(int teamNumber){
        print(ConsoleColors.BLUE_UNDERLINED + "Виграла Команда " + teamNumber + ConsoleColors.RESET);
    }

     private static void print(String message){
         System.out.println(message);
         fileWorks.print(message);
    }


}
