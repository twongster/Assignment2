package a3;

import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 * This class is a Car but is a Non-Playable car.
 * It has a drive strategy which depending on it's strategy will change it's driving behavior
 * based on it's goal. It has a higher damage level as well as fuel level to allow it's longevity
 * in playing the game. Because it doesn't have the controls to accelerate, it has a set speed of
 * of 30.
 */
public class NpcCar extends Car implements IDrawable {
    private int npcNumber;
    protected IStrategy driveStrategy;


    public NpcCar(float locationX, float locationY, int npcNumber){
        super(locationX, locationY);
        this.setNpcNumber(npcNumber);
        this.setMaximumDamage(200);
        this.setFuelLevel(999999);
        this.setSpeed(20);
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

    public String getDriveStrategy() {
        return driveStrategy.toString();
    }

    public String toString(){
        String output = "Non-Playable " + super.toString();
        output = output + " Strategy: " + driveStrategy;
        return output;
    }

    public void move(){
        super.moveNpc();
    }

    @Override
    public void draw(Graphics g) {
        int x = (int)(getLocationX() + getWidth()/2);
        int y = (int)(getLocationY() + getLength()/2);
        g.setColor(getColor());
        g.drawRect(x, y, getWidth(), getLength());
    }
}
