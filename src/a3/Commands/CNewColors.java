package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CNewColors extends AbstractAction {
    private GameWorld target;
    private static CNewColors newColors = null;

    private CNewColors(){ super("New Colors");}

    public static CNewColors getNewColors(){
        if(newColors == null){
            newColors = new CNewColors();
        }
        return newColors;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.changeObjectsColors();
        target.notifyObservers();
    }
}
