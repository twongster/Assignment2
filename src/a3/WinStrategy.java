package a3;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 * This class implements the "Win" strategy on the NonPlayable car that is passed in.
 * It uses an algorithm to change the car's heading based on the next sequential pylon's location.
 * Collaboration with fellow students was done to discover the algorithm to find the new heading.
 */
public class WinStrategy implements IStrategy{
    public void apply(NpcCar ncpCar, Object pylon){
        Pylon nextPylon= (Pylon)pylon;
        float floatAngle = (float)Math.toDegrees(Math.atan2(nextPylon.getLocationY() - ncpCar.getLocationY(),
                nextPylon.getLocationX() - ncpCar.getLocationX()));
        int angle = (int)(double)floatAngle;
        while (angle < 0) {
            angle += 360;
        }
        ncpCar.setHeading(angle);
    }
    public String toString(){
        return "Win Strategy";
    }

}
