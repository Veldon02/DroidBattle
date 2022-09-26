package Droids;

public class HealerDroid extends BaseDroid{
    public HealerDroid(String name){
        super(name, 125, 0, 45, 0, 10);
    }

    @Override
    public void attack(BaseDroid[] team) {
        BaseDroid droid = null;
        int healthDifference = 0;
        for (var teammate: team) {
            int diff = teammate.DEFAULTHEALTHPOINTS - teammate.getHealthPoints();
            if (diff > healthDifference){
                healthDifference = diff;
                droid = teammate;
            }
        }
        if (healthDifference == 0){
            for (var teammate: team) {
                teammate.buffAttack(1.2);
            }
        }else
            droid.heal(DEFAULTHEALTHPOINTS /5);
    }
}
