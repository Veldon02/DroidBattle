package Droids;

public class BerserkDroid extends BaseDroid{
    public BerserkDroid(String name){
        super(name,200,40,45,10,10);
    }


    @Override
    protected boolean getDamage(int damage){
        var result = super.getDamage(damage);
        if (healthPoints < DEFAULTHEALTHPOINTS/4){
            buffAttack(2.0);
            buffSpeed(15);
            buffCriticalDamageChance(5);
            buffDodgeChance(5);
        }

        return result;
    }
}
