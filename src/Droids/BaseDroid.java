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
    public void getDamage(BaseDroid enemy){
        var damage = enemy.getAttackPoints();
        healthPoints -= damage;
        if (healthPoints <= 0){
            System.out.println("Дроїд " + name + " був убитий дроїдом " + enemy.getName());
            healthPoints = 0;
        }
        else
            if (damage%10 == 1)
                System.out.println("Дроїд " + name + " отримав " + damage + " шкоду від дроїда " + enemy.getName());
            else
                System.out.println("Дроїд " + name + " отримав " + damage + " шкоди від дроїда " + enemy.getName());
    }
    protected abstract void reset();
    public abstract void attack(BaseDroid enemy);
}
