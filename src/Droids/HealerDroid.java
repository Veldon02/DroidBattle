package Droids;

public class HealerDroid extends BaseDroid{
    public HealerDroid(String name){
        this.name = name;
        healthPoints = 125;
        attackPoints = 0;
        speedPoints = 45;
        criticalDamageChance = 0;
        dodgeChance = 10;
    }

    @Override
    protected void reset() {
        healthPoints = 125;
        attackPoints = 0;
        speedPoints = 45;
        criticalDamageChance = 0;
        dodgeChance = 10;
    }
}
