package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CAddOilSlick extends AbstractAction {
    private GameWorld target;
    private static CAddOilSlick addOilSlick = null;

    private CAddOilSlick(){ super("Add Oil Slick");}

    public static CAddOilSlick getAddOilSlick(){
        if(addOilSlick == null){
            addOilSlick = new CAddOilSlick();
        }
        return addOilSlick;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.createOilSlick();
        target.notifyObservers();
    }
}
