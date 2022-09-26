package Droids;

import java.util.Arrays;
import java.util.Comparator;

public abstract class BaseDroid {
    protected String name;
    protected final int DEFAULTHEALTHPOINTS;
    protected final int DEFAULTATTACKPOINTS;
    protected final int DEFAULTSPEEDPOINTS;
    protected final int DEFAULTCRITICALDAMAGECHANCE;
    protected final int DEFAULTDODGECHANCE;
    protected int healthPoints;
    protected int attackPoints;
    protected int speedPoints;
    protected int criticalDamageChance;
    protected int dodgeChance;
    protected boolean isAlive;

    protected BaseDroid(String name, int healthPoints, int attackPoints,
                        int speedPoints, int criticalDamageChance, int dodgeChance) {
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

        if (aliveEnemies<count) count = aliveEnemies;

        var sortedList = Arrays.stream(enemyTeam)
                .sorted(Comparator.comparingInt(BaseDroid::getHealthPoints)).toArray();

        var result = new BaseDroid[count];
        for(int i = 0; i < count; i++){
            result[i] = (BaseDroid)sortedList[i];
        }
        return result;
    }

    protected void printAttackMessage(BaseDroid enemy, int damage){
        boolean crit = Math.random() * 100 + 1 < criticalDamageChance;
        if (crit) damage*=2;
        if (enemy.getDamage(damage)) {
            if (damage % 10 == 1)
                System.out.println("Дроїд " + enemy.getName() + " отримав " + damage + " шкоду від дроїда " + name);
            else
                System.out.println("Дроїд " + enemy.getName() + " отримав " + damage + " шкоди від дроїда " + name);
            if (crit)
                System.out.println("Це критичний удар!");
        }
        else
            System.out.println("Дроїд " + enemy.getName() + " ухилився від атаки дроїда " + name);
    }

    protected void printKillMessage(BaseDroid enemy){
        System.out.println("Дроїд " + enemy.getName() + " був убитий дроїдом " + name);
    }

    public void attack(BaseDroid[] enemyTeam){
        var enemy = getEnemiesWithMinHealth(1,enemyTeam)[0];
        printAttackMessage(enemy,attackPoints);
        if (!enemy.isAlive)
            printKillMessage(enemy);
    }

    protected void reset(){
        healthPoints = DEFAULTHEALTHPOINTS;
        attackPoints = DEFAULTATTACKPOINTS;
        speedPoints = DEFAULTSPEEDPOINTS;
        criticalDamageChance = DEFAULTCRITICALDAMAGECHANCE;
        dodgeChance = DEFAULTDODGECHANCE;
    }

}
