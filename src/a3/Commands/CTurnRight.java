package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CTurnRight extends AbstractAction {
    private GameWorld target;
    private static CTurnRight right = null;

    private CTurnRight(){ super("Right");}

    public static CTurnRight getRightTurn(){
        if(right == null){
            right = new CTurnRight();
        }
        return right;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.steerCarRight();
        target.notifyObservers();
    }
}
