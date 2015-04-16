package a3;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

/*
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a GameWorld object that has a collection of GameWorldObjects stored as a GameWorld ICollection.
 * It contains a set of methods execute user commands. It also stores a list of observers as a Vector.
 */
public class GameWorld implements Observable, IGameWorld, ActionListener {
    private GameObjectCollection worldObjects;
    private GameWorldProxy gwp;
    private Vector observers;
    private float randomX;
    private float randomY;
    private Random randomGenerator;
    private int numberPylons, size, numberNpcs, clock, lives, called;
    private boolean sound;
    private Timer timer;
    private final int DELAY_IN_MSEC = 20;

    public GameWorld(){
        worldObjects = new GameObjectCollection();
        gwp = new GameWorldProxy(this);
        randomGenerator = new Random();
        observers = new Vector();
        numberPylons = 5;
        lives = 3;
        numberNpcs = 3;
        clock = 0;
        size = 680;
    }

    /*
     * This method creates the initial layout of the GameWorld
     * Each object created is passed a random locationX and locationY
     * The first pylon created is set to be at the same starting location as the car.
     */
    @Override
    public void initLayout(){
        float startX = randomGenerator.nextFloat() * size;
        float startY = randomGenerator.nextFloat() * size;
        worldObjects.add(makeCar(startX, startY));
        initPylons(startX, startY, 6);
        initBirds(3);
        initFuelCans(5);
        initNpcCars(startX, startY, 4);
        initOilSlicks(4);
        timer = new Timer(DELAY_IN_MSEC, this);
        timer.start();
    }

    /***
     * Factory Methods
     *
     */

    public Car makeCar(float x, float y){ return new Car(x,y);}
    public Pylon makeFirstPylon(float x, float y){ return new Pylon(x,y, 1);}
    public Pylon makePylon(int num){return new Pylon(getPoint().getX(), getPoint().getY(), num);}
    public NpcCar makeNpcCar(float x, float y, int num){
        NpcCar temp = new NpcCar(getNpcCarStartPoint(x, y).getX(), getNpcCarStartPoint(x, y).getY(), num);
        temp.setStrategy(makeStrategy());
        return temp;
    }
    public OilSlick makeOilSlick(){return new OilSlick(getPoint().getX(), getPoint().getY());}
    public FuelCan makeFuelCan(){return new FuelCan(getPoint().getX(), getPoint().getY());}
    public Bird makeBird(){return new Bird(getPoint().getX(), getPoint().getY());}
    public IStrategy makeStrategy(){
        if((randomGenerator.nextFloat() * 12) > 7){
            return new DerbyStrategy();
        } else{
            return new WinStrategy();
        }
    }
    public Point getNpcCarStartPoint(float x, float y){
        float tempX = (x+ (randomGenerator.nextFloat() * (randomGenerator.nextFloat() *130)) + (randomGenerator.nextFloat() *130));
        float tempY = (y+ (randomGenerator.nextFloat() * 5) + 20);
        while(!checkLocation(tempX, tempY)){
            tempX = (x+ (randomGenerator.nextFloat() * (randomGenerator.nextFloat() *130)) +(randomGenerator.nextFloat() *130));
            tempY = (y+ (randomGenerator.nextFloat() * 5) +20);
        }
        return new Point(tempX, tempY);
    }

    /***
     * This method creates all the Pylons for the GameWorld. Initializing the first pylon at the desired starting x and y location and the remaining at random locations.
     * @param x = starting x location for the first pylon
     * @param y = starting y location for the first pylon
     * @param num = total number of pylons desired
     */
    public void initPylons(float x, float y, int num){
        worldObjects.add(makeFirstPylon(x,y));
        for(int i = 2; i<= num; i++){
            worldObjects.add(makePylon(i));
        }
    }

    /***
     * This method creates all the Npc Cars for the GameWorld.
     * @param num = total number of Npc Cars desired
     */
    public void initNpcCars(float x, float y, int num){
        for(int i = 1; i<= num; i++){
            worldObjects.add(makeNpcCar(x, y, i));
        }
    }

    /***
     * This method creates all the Oil Slicks for the GameWorld.
     * @param num = total number of Oil Slicks desired
     */
    public void initOilSlicks(int num){
        for(int i = 1; i<= num; i++){
            worldObjects.add(makeOilSlick());
        }
    }

    /***
     * This method creates all the Birds for the GameWorld.
     * @param num = total number of Birds desired
     */
    public void initBirds(int num){
        for(int i = 1; i<= num; i++){
            worldObjects.add(makeBird());
        }
    }

    /***
     * This method creates all the Oil Slicks for the GameWorld.
     * @param num = total number of Oil Slicks desired
     */
    public void initFuelCans(int num){
        for(int i = 1; i<= num; i++){
            worldObjects.add(makeFuelCan());
        }
    }

    //Returns a Point where no other object resides
    public Point getPoint(){
        float tempX = (randomGenerator.nextFloat() * size) + 30;
        float tempY = (randomGenerator.nextFloat() * size) + 30;
        while(!checkLocation(tempX, tempY)){
            tempX = (randomGenerator.nextFloat() * size) + 30;
            tempY =(randomGenerator.nextFloat() * size) + 30;
        }
        return new Point(tempX, tempY);
    }

    public boolean checkLocation(float x, float y){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            GameWorldObjects gwObject = (GameWorldObjects)theCollection.getNext();
            if((((40 + gwObject.getLocationX()) <= x) && ((40 - gwObject.getLocationX()) >= x)) || (((40 +gwObject.getLocationY()) <= y) && ((40 -gwObject.getLocationY()) >= y))){
                System.out.println("false");
                return false;
            }
        }
        return true;
    }
    @Override
    public GameObjectCollection getWorldObjects() {
        return worldObjects;
    }

    @Override
    public int getClock() {
        return clock;
    }

    @Override
    public void setClock(int clock) {
        this.clock = clock;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public boolean isSound() {
        return sound;
    }

    @Override
    public void setSound(boolean sound) {
        this.sound = sound;
    }

    @Override
    public void toggleSound(){
        if(sound){
            sound = false;
        } else{
            sound = true;
        }
    }

    /**
     * This method finds the car object in the vector and then calls the accelerate
     * method of that car to increase the speed.
     */
    @Override
    public void accelerateCar(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.accelerate();
            }
        }
    }
    /**
     * This method finds the car object in the vector and then calls the brake
     * method of that car to decrease the speed.
     */
    @Override
    public void brakeCar(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.brake();
            }
        }
    }
    /**
     * This method finds the car object in the vector and then calls the steerLeft
     * method of that car to adjust the steering direction of the car
     */
    @Override
    public void steerCarLeft(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                    car.steerLeft();
            }
        }
    }


    /**
     * This method finds the car object in the vector and then calls the steerRight
     * method of that car to adjust the steering direction of the car
     */
    @Override
    public void steerCarRight(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.steerRight();
            }
        }
    }


    /**
     * This method creates a new randomly located oil slick in the game world
     */
    @Override
    public void createOilSlick(){
        worldObjects.add(makeOilSlick());
    }

    @Override
    public void createCar(int checkpt){
        worldObjects.add(new Car(randomGenerator.nextFloat() * size,randomGenerator.nextFloat() * size, checkpt));
    }

    @Override
    public void loseLife(){
        setLives(getLives() - 1);
            Car car = new Car();
            Iterator theCollection = worldObjects.getIterator();
            while(theCollection.hasNext()){
                Object gwObject = theCollection.getNext();
                if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                    car = (Car)gwObject;
                    break;
                }
            }
            int checkpt = car.getCheckPoint();
            worldObjects.remove(car);
            createCar(checkpt);


    }

    @Override
    public void createFuelCan(){
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * size,randomGenerator.nextFloat() * size));
    }

    //returns True if the car can continue to play after collision
    @Override
    public void carCollision(){
        Iterator theCollection = worldObjects.getIterator();
        Car car = null;
        NpcCar npcCar = null;
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(gwObject instanceof NpcCar){
                npcCar = (NpcCar)gwObject;
            }
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                car = (Car)gwObject;
            }
        }
        car.damaged(npcCar);
        npcCar.damaged(car);
        if (!npcCar.canMove()){
            int carNum = npcCar.getNpcNumber();
            worldObjects.remove(npcCar);
            createNpcCar(carNum);
        }
        if(!carCanMove()){
            loseLife();
        }
    }

    @Override
    public void createNpcCar(int carNumber){
        worldObjects.add(new NpcCar(randomGenerator.nextFloat() * size,randomGenerator.nextFloat() * size, carNumber));
    }

    @Override
    public void createBird(){
        worldObjects.add(new Bird(randomGenerator.nextFloat() * size,randomGenerator.nextFloat() * size));
    }
    @Override
    public boolean birdCollision(){
        Iterator theCollection = worldObjects.getIterator();
        Bird b = null;
        Car car = null;
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(gwObject instanceof Bird){
                b = (Bird) gwObject;
            }
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                car = (Car)gwObject;
            }
        }
        car.damaged(b);
        worldObjects.remove(b);
        createBird();
        return (car.canMove());
    }
    @Override
    public boolean pylonExists(int pylonNum){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(gwObject instanceof Pylon){
                Pylon p = (Pylon) gwObject;
                if (p.getSequenceNumber() == pylonNum) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void pylonCollision(int pylonNum){
        Car car = new Car();
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                car = (Car) gwObject;
            }
        }
        car.pylonCollision(pylonNum);
    }

    //Returns true if the car has collided with the last pylon and has won the race
    @Override
    public boolean checkWin(){
        return(this.getHighestPylon() == numberPylons );
    }

    @Override
    public void fuelCanCollision(){
        Object delete = null;
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(gwObject instanceof FuelCan){
                Car car = findPlayersCar();
                car.addFuel(((FuelCan) gwObject).getSize());
                delete = gwObject;
                createFuelCan();
                break;
            }
        }
        worldObjects.remove(delete);
    }

    @Override
    public Car findPlayersCar(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                return car;
            }
        }
        return null;
    }

    @Override
    public void carEnteredOilSlick(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.setStuck(true);
            }
        }
    }

    @Override
    public void carExitedOilSlick(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.setStuck(false);
            }
        }
    }

    //Changes the color of every object who is of type IColorable
    @Override
    public void changeObjectsColors(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(gwObject instanceof IColorable){
                IColorable obj = (IColorable)gwObject;
                obj.changeColor();
            }
        }
    }


    @Override
    public int findNextPylon(){
        if(getHighestPylon() != numberPylons){
            return getHighestPylon() +1;
        } else {
            return getHighestPylon();
        }
    }

    @Override
    public Pylon getNextPylon(){
        Pylon temp = null;
        if(getHighestPylon() != numberPylons) {
            Iterator theCollection = worldObjects.getIterator();
            while (theCollection.hasNext()) {
                Object gwObject = theCollection.getNext();
                if (gwObject instanceof Pylon) {
                    temp = (Pylon) gwObject;
                    if (temp.getSequenceNumber() == findNextPylon()) {
                        return temp;
                    }
                }
            }
        }
        return temp;
    }

    public void updateAllMoveables(int called){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(gwObject instanceof Moveable){
                if(gwObject instanceof NpcCar){
                    NpcCar car = (NpcCar)gwObject;
                    if(car.getDriveStrategy().equalsIgnoreCase("win strategy")){
                        car.invokeStrategy(getNextPylon());
                        car.move();
                    } else{
                        car.invokeStrategy(findPlayersCar());
                        car.move();
                    }
                } else{
                    Moveable obj = (Moveable)gwObject;
                    obj.move();
                    if(obj.getLocationX() < 0 || obj.getLocationY() > 825){
                        obj.setHeading(obj.getHeading() - 180);
                    } else if(obj.getLocationX() > size || obj.getLocationY() < 0){
                        obj.setHeading(obj.getHeading()+180);
                    }
                }

            }
        }
    }

    @Override
    public boolean carCanMove(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.canMove();
            }
        }
        return false;
    }

    @Override
    public int getHighestPylon(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.getCheckPoint();
            }
        }
        return 0;
    }

    @Override
    public int getCarFuelLevel(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.getFuelLevel();
            }
        }
        return 0;
    }

    @Override
    public int getCarDamageLevel(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.getDamageLevel();
            }
        }
        return 0;
    }


    public void tick(int x){
        called++;
        if((called%50)==0){
            setClock(getClock() +1);
        }
        for(int i = 0; i<=x; i++){
            updateAllMoveables(called);
            if(getCarFuelLevel() <= 0){
                loseLife();
            }
            notifyObservers();
        }

    }

    @Override
    public void addObserver(Observer obs){
        observers.add(obs);
    }

    @Override
    public void notifyObservers(){
        for(Object o : observers){
            Observer x = (Observer) o;
            x.update(gwp);
        }
    }

    //Returns true if the string passed in is numeric and can be converted to type int
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    /**
     * Changes all NPC Cars' strategies based on the random algorithm
     */
    @Override
    public void changeStrategies(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(gwObject instanceof NpcCar){
                NpcCar temp = (NpcCar) gwObject;
                if(temp.getDriveStrategy().equalsIgnoreCase("Win Strategy")){
                    temp.setStrategy(new DerbyStrategy());
                } else{
                    temp.setStrategy(new WinStrategy());
                }
            }
        }

    }

    @Override
    public String toString(){
        Iterator theCollection = worldObjects.getIterator();
        String output = "";
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            output = output + gwObject.toString() + "\n";
        }
        return output;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick(DELAY_IN_MSEC);
    }
}
