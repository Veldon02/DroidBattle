package Droids;

public class AssassinDroid extends BaseDroid{
    public AssassinDroid(String name){
        this.name = name;
        healthPoints = 150;
        attackPoints = 50;
        speedPoints = 90;
        criticalDamageChance = 20;
        dodgeChance = 30;
    }

    @Override
    protected void reset() {
        healthPoints = 150;
        attackPoints = 50;
        speedPoints = 90;
        criticalDamageChance = 20;
        dodgeChance = 30;
    }
}
