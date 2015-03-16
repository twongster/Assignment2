package a2;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 */
public class ScoreView implements Observer {
    public ScoreView(Observable model){
        model.addObserver(this);
    }

    //This constructor assumes 3rd-party Observer registration
    public ScoreView(){

    }

    public void update(Observable o) {

    }

}
