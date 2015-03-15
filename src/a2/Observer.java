package a2;

import java.util.Observable;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 */
public interface Observer {
    public void update(Observable o, Object arg);
}
