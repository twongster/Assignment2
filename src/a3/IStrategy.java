package a3;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 */
public interface IStrategy {
    public void apply(NpcCar ncpCar, Object destination);
    public String toString();
}
