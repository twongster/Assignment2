package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CBrake extends AbstractAction {
    private GameWorld target;
    private static CBrake stop = null;

    private CBrake(){ super("Brake");}

    public static CBrake getBrake(){
        if(stop == null){
            stop = new CBrake();
        }
        return stop;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.brakeCar();
        target.notifyObservers();
    }
}
