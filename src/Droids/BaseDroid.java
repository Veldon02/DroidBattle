package Droids;

public abstract class BaseDroid {
    protected String name;
    protected int healthPoints;
    protected int attackPoints;
    protected int speedPoints;
    protected int criticalDamageChance;
    protected int dodgeChance;

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
    protected abstract void reset();
}
