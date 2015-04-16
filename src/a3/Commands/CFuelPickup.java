package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CFuelPickup extends AbstractAction {
    private GameWorld target;
    private static CFuelPickup collideFuel = null;

    private CFuelPickup(){ super("Fuel Pickup");}

    public static CFuelPickup getCollideFuel(){
        if(collideFuel == null){
            collideFuel = new CFuelPickup();
        }
        return collideFuel;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.fuelCanCollision();
        target.notifyObservers();
    }
}
