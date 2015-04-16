package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CCollideCar extends AbstractAction{
    private GameWorld target;
    private static CCollideCar collideCar = null;

    private CCollideCar(){ super("Collide with NPC");}

    public static CCollideCar getCollideCar(){
        if(collideCar == null){
            collideCar = new CCollideCar();
        }
        return collideCar;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.carCollision();
        target.notifyObservers();
    }
}
