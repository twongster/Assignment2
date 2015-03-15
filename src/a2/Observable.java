package a2;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 */
public interface Observable {
    public void addObserver(Observer obs);
    public void notifyObservers();
}
