package Droids;

public class TankDroid extends BaseDroid{
    public TankDroid(String name){
        this.name = name;
        healthPoints = 250;
        attackPoints = 25;
        speedPoints = 40;
        criticalDamageChance = 10;
        dodgeChance = 5;
    }

    @Override
    protected void reset() {
        healthPoints = 250;
        attackPoints = 25;
        speedPoints = 40;
        criticalDamageChance = 10;
        dodgeChance = 5;
    }
}
