package Droids;

public class WizardDroid extends BaseDroid{
    public WizardDroid(String name){
        super(name, 200, 30, 50, 10,10);
    }

    @Override
    public void attack(BaseDroid[] enemyTeam) {
        var targets = getEnemiesWithMinHealth(2,enemyTeam);
        if (targets.length == 1)
            printAttackMessage(targets[0],attackPoints*2);
        else
            for (var enemy: targets)
                printAttackMessage(enemy,attackPoints);

        for (var enemy:enemyTeam)
            if (!enemy.isAlive)
                printKillMessage(enemy);
    }
}
