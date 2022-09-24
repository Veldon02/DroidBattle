package Droids;

public class BerserkDroid extends BaseDroid{
    public BerserkDroid(String name){
        this.name = name;
        healthPoints = 200;
        attackPoints = 40;
        speedPoints = 45;
        criticalDamageChance = 10;
        dodgeChance = 10;
    }

    @Override
    protected void reset() {
        healthPoints = 200;
        attackPoints = 40;
        speedPoints = 45;
        criticalDamageChance = 10;
        dodgeChance = 10;
    }
}
