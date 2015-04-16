package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CEnterOilSlick extends AbstractAction {
    private GameWorld target;
    private static CEnterOilSlick enterOilSlick = null;

    private CEnterOilSlick(){ super("Enter Oil Slick");}

    public static CEnterOilSlick getEnterOilSlick(){
        if(enterOilSlick == null){
            enterOilSlick = new CEnterOilSlick();
        }
        return enterOilSlick;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.carEnteredOilSlick();
        target.notifyObservers();
    }
}
