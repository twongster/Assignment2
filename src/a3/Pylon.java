package a3;

import javafx.scene.shape.Circle;

import java.awt.*;

/**
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a Pylon object which is a fixed and non-colorable game object.
 */
public class Pylon extends Fixed implements IDrawable {
    private int radius;
    private int sequenceNumber;

    public Pylon(float locationX, float locationY, int sequenceNumber) {
        this.setColor(Color.GREEN);
        this.setSequenceNumber(sequenceNumber);
        this.setRadius(20);
        this.setLocationY(locationY);
        this.setLocationX(locationX);
    }

    public void setRadius(int radius){
        this.radius= radius;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getSequenceNumber(){
        return sequenceNumber;
    }

    public int getRadius(){
        return radius;
    }

    void setColor(Color color){
        this.color = color;
    }

    Color getColor() {
        return color;
    }



    public String toString(){
        String string = "";
        string = "Pylon: " + super.toString()+ " radius=" + this.getRadius() + " seqNum=" + this.getSequenceNumber();
        return string;
    }

    public void draw(Graphics g) {
        int x = (int)(getLocationX() - (getRadius()/2));
        int y = (int)(getLocationY() - (getRadius()/2));

        g.setColor(getColor());
        g.fillOval(x, y, getRadius(), getRadius());
        g.setColor(Color.BLUE);
        g.drawOval(x, y, getRadius(), getRadius());
        g.setColor(Color.black);
        g.drawString(""+ getSequenceNumber(), (int)getLocationX()-(getRadius()/4), (int)getLocationY()+(getRadius()/4));
    }
}
