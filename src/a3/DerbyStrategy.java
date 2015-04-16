package a3;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 * This class implements the "Derby" strategy on the NonPlayable car that is passed in.
 * It uses an algorithm to change the car's heading based on the player car's current location.
 * Collaboration with fellow students was done to discover the algorithm to find the new heading.
 */
public class DerbyStrategy implements IStrategy {
    public void apply(NpcCar ncpCar, Object car){
        Car playersCar= (Car)car;
        float floatAngle = (float)Math.toDegrees(Math.atan2(playersCar.getLocationY() - ncpCar.getLocationY(),
                playersCar.getLocationX() - ncpCar.getLocationX()));
        int angle = (int)(double)floatAngle;
        while (angle < 0) {
            angle += 360;
        }
        ncpCar.setHeading(angle);
    }

    public String toString(){
        return "Derby Strategy";
    }
}
