package a3.Commands;

import a3.GameWorld;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/17/15.
 */
public class CAbout extends AbstractAction {
    private GameWorld target;
    private static CAbout about = null;

    private CAbout(){ super("About");}

    public static CAbout getAbout(){
        if(about == null){
            about = new CAbout();
        }
        return about;
    }

    public void setTarget(GameWorld gw){
        target = gw;
    }


    public void actionPerformed (ActionEvent e) {
        String output = "The Wong Way: 2 Wongz Don't Make A Whight, Part XIV \n";
        output = output + "Author: Tiffany Chiapuzio-Wong\n";
        output = output + "Version: 2.0";
        JOptionPane.showMessageDialog(null, output, "About", JOptionPane.INFORMATION_MESSAGE);
    }

}
