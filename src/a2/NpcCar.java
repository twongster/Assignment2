package a2;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 */
public class NpcCar extends Car {
    private int npcNumber;
    protected IStrategy driveStrategy;


    public NpcCar(float locationX, float locationY, int npcNumber){
        super(locationX, locationY);
        this.setNpcNumber(npcNumber);
        this.setMaximumDamage(200);

    }

    public int getNpcNumber() {
        return npcNumber;
    }

    public void setNpcNumber(int npcNumber) {
        this.npcNumber = npcNumber;
    }

    public void setStrategy(IStrategy s){
        driveStrategy = s;
    }

    public void invokeStrategy(Object x){
        driveStrategy.apply(this, x);
    }


}
