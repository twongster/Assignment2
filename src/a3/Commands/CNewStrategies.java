package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/17/15.
 */
public class CNewStrategies extends AbstractAction {
    private GameWorld target;
    private static CNewStrategies newStrategies = null;

    private CNewStrategies(){ super("Change Strategies");}

    public static CNewStrategies getNewStrategies(){
        if(newStrategies == null){
            newStrategies = new CNewStrategies();
        }
        return newStrategies;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.changeStrategies();
        target.notifyObservers();
    }
}
