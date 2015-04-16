package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CTick extends AbstractAction {
    private GameWorld target;
    private static CTick tick = null;

    private CTick(){ super("Tick");}

    public static CTick getTick(){
        if(tick == null){
            tick = new CTick();
        }
        return tick;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
       // target.tick();
        target.notifyObservers();
    }
}
