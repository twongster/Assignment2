package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CCollidePylon extends AbstractAction {
    private GameWorld target;
    private static CCollidePylon collidePylon = null;

    private CCollidePylon(){ super("Collide with Pylon");}

    public static CCollidePylon getCollidePylon(){
        if(collidePylon == null){
            collidePylon = new CCollidePylon();
        }
        return collidePylon;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        String input = JOptionPane.showInputDialog("Please input a pylon number");
        if(input != null){
            boolean invalid = true;
            int pylonNum = -1;
            while(invalid){
                if(target.isNumeric(input) && (input.length() > 0)){
                    pylonNum = Integer.parseInt(input);
                    break;
                } else{
                    JOptionPane.showMessageDialog(null, "Input was invalid try again.");
                    input = JOptionPane.showInputDialog("Please input a pylon number");
                }
            }
            if(target.pylonExists(pylonNum)){
                target.pylonCollision(pylonNum);
                target.notifyObservers();
            } else{
                JOptionPane.showMessageDialog(null, "Pylon does not exist.");
            }
        }
    }
}
