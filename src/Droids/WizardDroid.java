package Droids;

public class WizardDroid extends BaseDroid{
    public WizardDroid(String name){
        this.name = name;
        healthPoints = 200;
        attackPoints = 30;
        speedPoints = 50;
        criticalDamageChance = 10;
        dodgeChance = 10;
    }

    @Override
    protected void reset() {
        healthPoints = 200;
        attackPoints = 30;
        speedPoints = 50;
        criticalDamageChance = 10;
        dodgeChance = 10;
    }
}
