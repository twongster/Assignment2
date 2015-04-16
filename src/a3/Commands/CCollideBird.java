package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CCollideBird extends AbstractAction {
    private GameWorld target;
    private static CCollideBird collideBird = null;

    private CCollideBird(){ super("Collide with Bird");}

    public static CCollideBird getCollideBird(){
        if(collideBird == null){
            collideBird = new CCollideBird();
        }
        return collideBird;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        if(!target.birdCollision()) {
            target.loseLife();
        }
        target.notifyObservers();
    }
}
