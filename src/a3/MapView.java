package a3;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 * This class creates a JPanel of Map View which shows the player it's current
 * values for all the game world objects.
 */
public class MapView extends JPanel implements Observer  {
    JTextPane mapText;
    Observable o;
    JPanel map;
    public MapView(Observable model){
        o = model;
        model.addObserver(this);
        setLayout(null);
        setBackground(new Color(0,0,0));
        setSize(800, 800);
        setLocation(200, 50);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Map"));

      /***  mapText =  new JTextPane();
        mapText.setLocation(50, 50);
        mapText.setEditable(false);
        mapText.setSize(700,600);
        add(mapText);
        GameWorldProxy gwp = (GameWorldProxy) o;
        GameObjectCollection gwc = gwp.getWorldObjects();
        Iterator theCollection = gwc.getIterator();
        String output = "";
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            output = output + gwObject.toString() + "\n";
        }
        mapText.setText(output);**/

    }

    //This constructor assumes 3rd-party Observer registration
    public MapView(){

    }

    public void update(Observable o) {
        repaint();
       /*** GameWorldProxy gwp = (GameWorldProxy) o;
        GameObjectCollection gwc = gwp.getWorldObjects();
        Iterator theCollection = gwc.getIterator();
        String output = "";
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            output = output + gwObject.toString() + "\n";
        }
        System.out.print(output);**/
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        GameWorldProxy gwp = (GameWorldProxy) o;
        GameObjectCollection gwc = gwp.getWorldObjects();
        Iterator theCollection = gwc.getIterator();
        while(theCollection.hasNext()){
            IDrawable gwObject = (IDrawable)theCollection.getNext();
            gwObject.draw(g);
        }

    }
}
