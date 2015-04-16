package a3;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 * This class creates a JPanel of Score View which shows the player it's current
 * values of time, lives, last pylon, fuel, and sound value.
 */

public class ScoreView extends JPanel implements Observer {
    private String time, lives, highestPylon, fuel, sound, dLevel;
    private JLabel lTime, lLives, lHighestPylon, lFuel,lSound, ldLevel;
    private int currentLives;
    public ScoreView(Observable model){
        model.addObserver(this);
    }

    //This constructor assumes 3rd-party Observer registration
    public ScoreView(){
        setLayout(new FlowLayout());
        setSize(1000, 50);
        setBackground(Color.white);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Score"));

        time = "0";
        lives = "3";
        currentLives = 3;
        highestPylon = "1";
        fuel = "100";
        sound = "OFF";
        dLevel = "0";

        lTime = new JLabel("Time: " +time);
        add(lTime);

        lLives = new JLabel("Lives Remaining: " +  lives);
        add(lLives);

        lHighestPylon = new JLabel("Highest Pylon Reached: " + highestPylon);
        add(lHighestPylon);

        lFuel = new JLabel("Fuel Remaining: " + fuel);
        add(lFuel);

        lSound = new JLabel("Sound: " + sound);
        add(lSound);

        ldLevel = new JLabel("Damage Level: " + dLevel);
        add(ldLevel);


    }

    private void checkSound(boolean s){
        if(s){
            sound = "ON";
        } else{
            sound = "OFF";
        }
    }

    public void update(Observable o) {
        GameWorldProxy gwp = (GameWorldProxy) o;
        if((gwp.getLives() == 0)) {
            JOptionPane.showMessageDialog(null,
                    "Oh no! You ran out of lives! \n GAME OVER", "GAME OVER",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }else if(currentLives > gwp.getLives()){
                currentLives = gwp.getLives();
                lives = "Lives Remaining: " +gwp.getLives();
                JOptionPane.showMessageDialog(null,
                        "Oh no! You took on too much damage or ran out of gas! You lost 1 life. Lives remaining: " + gwp.getLives(),
                        "LOST LIFE",
                        JOptionPane.WARNING_MESSAGE);
            } else{    lives = "Lives Remaining: " +gwp.getLives();}

        time = "Time: " + gwp.getClock();
        highestPylon = "Highest Pylon Reached: " + gwp.getHighestPylon();
        fuel = "Fuel Remaining: " + gwp.getCarFuelLevel();
        dLevel = "Damage Level: " + gwp.getCarDamageLevel();
        checkSound(gwp.isSound());

        lTime.setText(time);
        lLives.setText(lives);
        lHighestPylon.setText(highestPylon);
        lFuel.setText(fuel);
        lSound.setText("Sound: " + sound);
        ldLevel.setText(dLevel);

    }

}
