package Droids;

import FileWorks.FileWorks;

public class WizardDroid extends BaseDroid{
    public WizardDroid(String name, FileWorks fileWorks){
        super("Чаклун", name, 200, 30, 50, 10,10,fileWorks);
    }

    @Override
    public void attack(BaseDroid[] enemyTeam) {
        super.attack(enemyTeam);
        super.attack(enemyTeam);
    }
}
