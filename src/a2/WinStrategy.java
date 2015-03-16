package a2;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 */
public class WinStrategy implements IStrategy{
    public void apply(NpcCar ncpCar, Object pylon){
        Pylon nextPylon= (Pylon)pylon;
        Float floatAngle = (float)Math.toDegrees(Math.atan2(nextPylon.getLocationY() - ncpCar.getLocationY(),
                nextPylon.getLocationX() - ncpCar.getLocationX()));
        int angle = (int)(double)floatAngle;
        while (angle < 0) {
            angle += 360;
        }
        ncpCar.setHeading(angle);
    }
}
