package Droids;

import ConsoleColors.ConsoleColors;
import FileWorks.FileWorks;

import java.util.Arrays;
import java.util.Comparator;

public abstract class BaseDroid {
    protected String name;
    protected final int DEFAULTHEALTHPOINTS;
    protected final int DEFAULTATTACKPOINTS;
    protected final int DEFAULTSPEEDPOINTS;
    protected final int DEFAULTCRITICALDAMAGECHANCE;
    protected final int DEFAULTDODGECHANCE;
    protected String type;
    protected int healthPoints;
    protected int attackPoints;
    protected int speedPoints;
    protected int criticalDamageChance;
    protected int dodgeChance;
    protected boolean isAlive;
    protected FileWorks fileWorks;

    protected BaseDroid(String type, String name, int healthPoints, int attackPoints,
                        int speedPoints, int criticalDamageChance, int dodgeChance,FileWorks fileWorks) {
        this.type = type;
        this.name = name;
        DEFAULTHEALTHPOINTS = healthPoints;
        this.healthPoints = healthPoints;
        DEFAULTATTACKPOINTS = attackPoints;
        this.attackPoints = attackPoints;
        DEFAULTSPEEDPOINTS = speedPoints;
        this.speedPoints = speedPoints;
        DEFAULTCRITICALDAMAGECHANCE = criticalDamageChance;
        this.criticalDamageChance = criticalDamageChance;
        DEFAULTDODGECHANCE = dodgeChance;
        this.dodgeChance = dodgeChance;
        this.fileWorks = fileWorks;
        isAlive = true;
    }

    public String getName() {
        return name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getSpeedPoints() {
        return speedPoints;
    }

    public int getCriticalDamageChance() {
        return criticalDamageChance;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }
    public boolean getIsAlive() {
        return isAlive;
    }
    public String getType() {
        return type;
    }

    protected boolean getDamage(int damage){
        if (Math.random()*100 + 1 < dodgeChance) return false;
        healthPoints -= damage;
        if (healthPoints <= 0){
            healthPoints = 0;
            isAlive = false;
        }
        return true;
    }

    protected void buffAttack(double coefficient){
        attackPoints *= coefficient;
    }

    protected void buffSpeed(int value){
        attackPoints += value;
    }
    protected void buffCriticalDamageChance(int value){
        criticalDamageChance += value;
    }
    protected void buffDodgeChance(int value){
        dodgeChance += value;
    }

    protected void heal(int value){
        healthPoints += value;
        if (healthPoints > DEFAULTHEALTHPOINTS)
            healthPoints = DEFAULTHEALTHPOINTS;
    }

    protected BaseDroid[] getEnemiesWithMinHealth(int count, BaseDroid[] enemyTeam){
        int aliveEnemies = 0;
        for (var enemy: enemyTeam)
            if (enemy.isAlive) aliveEnemies++;
        if (aliveEnemies == 0) return null;
        if (aliveEnemies<count) count = aliveEnemies;

        var sortedList = Arrays.stream(enemyTeam)
                .sorted(Comparator.comparingInt(BaseDroid::getHealthPoints)).toArray();

        var result = new BaseDroid[count];
        int j = 0;
        for(int i = 0; i < count; i++){
            while(true){
                if (!((BaseDroid)sortedList[i+j]).isAlive) j++;
                else break;
            }
            result[i] = (BaseDroid)sortedList[i+j];
        }
        return result;
    }

    protected void printAttackMessage(BaseDroid enemy, int damage){
        boolean crit = Math.random() * 100 + 1 < criticalDamageChance;
        if (crit) damage*=2;
        if (enemy.getDamage(damage)) {
            if (damage % 10 == 1)
                print("Дроїд " + enemy.getName() + " отримав " + damage + " шкоду від дроїда " + name);
            else
                print("Дроїд " + enemy.getName() + " отримав " + damage + " шкоди від дроїда " + name);
            if (crit)
                print(ConsoleColors.RED_UNDERLINED + "Це критичний удар!" + ConsoleColors.RESET);
        }
        else
            print(ConsoleColors.BLUE_BOLD_BRIGHT + "Дроїд " + enemy.getName() + " ухилився від атаки дроїда " + name + ConsoleColors.RESET);
    }

    protected void printKillMessage(BaseDroid enemy){
        print(ConsoleColors.RED + name + " вбив дроїда " + enemy.getName() + ConsoleColors.RESET);
    }

    public void attack(BaseDroid[] enemyTeam){
        var sortedEnemies = getEnemiesWithMinHealth(1,enemyTeam);
        if (sortedEnemies == null) return;
        BaseDroid enemy = sortedEnemies[0];
        printAttackMessage(enemy,attackPoints);
        if (!enemy.isAlive)
            printKillMessage(enemy);
    }

    public void reset(){
        healthPoints = DEFAULTHEALTHPOINTS;
        attackPoints = DEFAULTATTACKPOINTS;
        speedPoints = DEFAULTSPEEDPOINTS;
        criticalDamageChance = DEFAULTCRITICALDAMAGECHANCE;
        dodgeChance = DEFAULTDODGECHANCE;
        isAlive = true;
    }

    protected void print(String message){
        System.out.println(message);
        fileWorks.print(message);
    }

}
