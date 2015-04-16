package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CTurnLeft extends AbstractAction {
    private GameWorld target;
    private static CTurnLeft left = null;

    private CTurnLeft(){ super("Left");}

    public static CTurnLeft getLeftTurn(){
        if(left == null){
            left = new CTurnLeft();
        }
        return left;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.steerCarLeft();
        target.notifyObservers();
    }
}
