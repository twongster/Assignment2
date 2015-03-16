package a2;


/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 */
public class MapView implements Observer {

    public MapView(Observable model){
        model.addObserver(this);
    }

    //This constructor assumes 3rd-party Observer registration
    public MapView(){}

    public void update(Observable o, Object arg) {

    }
}
