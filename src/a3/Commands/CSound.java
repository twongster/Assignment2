package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/17/15.
 */
public class CSound extends AbstractAction{
    private GameWorld target;
    private static CSound sound = null;

    private CSound(){ super("Sound");}

    public static CSound getSound(){
        if(sound == null){
            sound = new CSound();
        }
        return sound;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        target.toggleSound();
        target.notifyObservers();
    }

}
