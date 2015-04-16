package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/16/15.
 */
public class CQuit extends AbstractAction {
    private GameWorld target;
    private static CQuit quit = null;

    private CQuit(){ super("Quit");}

    public static CQuit getQuit(){
        if(quit == null){
            quit = new CQuit();
        }
        return quit;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }

    public void actionPerformed (ActionEvent e){
        String input = JOptionPane.showInputDialog("Are you sure you want to quit?");
        if(input != null){
            boolean invalid = true;
            while(invalid){
                input = input.toLowerCase();
                if((input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))&& (input.length() > 0)){
                    JOptionPane.showMessageDialog(null, "Thanks for playing!");
                    System.exit(0);
                } else if ((input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no"))&& (input.length() > 0)){
                    break;
                } else{
                    JOptionPane.showMessageDialog(null, "Input was invalid try again.");
                    input = JOptionPane.showInputDialog("Are you sure you want to quit?");
                }
            }
        }
    }


}
