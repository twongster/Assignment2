package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CExitOilSlick extends AbstractAction {
    private GameWorld target;
    private static CExitOilSlick exitOilSlick = null;

    private CExitOilSlick(){ super("Exit Oil Slick");}

    public static CExitOilSlick getExitOilSlick(){
        if(exitOilSlick == null){
            exitOilSlick = new CExitOilSlick();
        }
        return exitOilSlick;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.carExitedOilSlick();
        target.notifyObservers();
    }
}
