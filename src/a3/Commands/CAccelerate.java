package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/15/15.
 */
public class CAccelerate extends AbstractAction {
    private GameWorld target;
    private static CAccelerate vvroom = null;

    private CAccelerate(){ super("Accelerate");}

    public static CAccelerate getAccelerate(){
        if(vvroom == null){
            vvroom = new CAccelerate();
        }
        return vvroom;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.accelerateCar();
        target.notifyObservers();
    }

}
