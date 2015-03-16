package a2;

import java.util.Random;
import java.util.Vector;

/*
 * Created by Tiffany Chiapuzio-Wong on 2/19/15.
 * This class is a GameWorld object that has a collection of GameWorldObjects stored as a Vector.
 * It contains a set of methods execute user commands.
 */
public class GameWorld implements Observable {
    private GameObjectCollection worldObjects;
    private Vector observers;
    private float randomX;
    private float randomY;
    private Random randomGenerator;
    private int numberPylons;
    private int numberNpcs;

    public GameWorld(){
        worldObjects = new GameObjectCollection();
        randomGenerator = new Random();
        numberPylons = 5;
        numberNpcs = 3;
    }

    /*
     * This method creates the initial layout of the GameWorld
     * Each object created is passed a random locationX and locationY
     * The first pylon created is set to be at the same starting location as the car.
     */
    public void initLayout(){
        float startX = randomGenerator.nextFloat() * 1000;
        float startY = randomGenerator.nextFloat() * 1000;
        worldObjects.add(new Car(startX, startY));
        worldObjects.add(new Pylon(startX, startY, 1));
        for(int i = 2; i<=numberPylons; i++){
            worldObjects.add(new Pylon(randomGenerator.nextFloat() * 1000, randomGenerator.nextFloat() * 1000, i));
        }
        for(int i = 1; i<=numberNpcs; i++){
            NpcCar temp = new NpcCar(startX+ (randomGenerator.nextFloat() * 12), startY+ (randomGenerator.nextFloat() * 20), i);
            if((randomGenerator.nextFloat() * 12) > 7){
                temp.setStrategy(new DerbyStrategy());
            } else{
                temp.setStrategy(new WinStrategy());
            }
            worldObjects.add(temp);
        }
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new Bird(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new Bird(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
    }
    /**
     * This method finds the car object in the vector and then calls the accelerate
     * method of that car to increase the speed.
     */
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
    public void createOilSlick(){
        worldObjects.add(new OilSlick(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
    }

    public void createCar(int checkpt){
        worldObjects.add(new Car(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000, checkpt));
    }

    public void loseLife(){
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

    public void createFuelCan(){
        worldObjects.add(new FuelCan(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000));
    }

    //returns True if the car can continue to play after collision
    public boolean carCollision(){
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
        return (car.canMove());
    }

    public void createNpcCar(int carNumber){
        worldObjects.add(new NpcCar(randomGenerator.nextFloat() * 1000,randomGenerator.nextFloat() * 1000, carNumber));
    }

    public boolean birdCollision(){
        Iterator theCollection = worldObjects.getIterator();
        while(theCollection.hasNext()){
            Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.damaged(new Bird());
                return (car.canMove());
            }
        }
        return false;
    }
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
    public boolean checkWin(){
        return(this.getHighestPylon() == numberPylons );
    }

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

    public void carEnteredOilSlick(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.setStuck(true);
            }
        }
    }

    public void carExitedOilSlick(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(!(gwObject instanceof NpcCar) && (gwObject instanceof Car)){
                Car car = (Car)gwObject;
                car.setStuck(false);
            }
        }
    }

    //Changes the color of every object who is of type IColorable
    public void changeObjectsColors(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(gwObject instanceof IColorable){
                IColorable obj = (IColorable)gwObject;
                obj.changeColor();
            }
        }
    }

    public void updateAllMoveables(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if(gwObject instanceof Moveable){
                Moveable obj = (Moveable)gwObject;
                obj.move();
            }
        }
    }

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

    public int getHighestPylon(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.getCheckPoint();
            }
        }
        return 0;
    }

    public int getCarFuelLevel(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.getFuelLevel();
            }
        }
        return 0;
    }

    public int getCarDamageLevel(){
        Iterator theCollection = worldObjects.getIterator();         while(theCollection.hasNext()){             Object gwObject = theCollection.getNext();
            if (!(gwObject instanceof NpcCar) && (gwObject instanceof Car)) {
                Car car = (Car) gwObject;
                return car.getDamageLevel();
            }
        }
        return 0;
    }

    public void addObserver(Observer obs){
        observers.add(obs);
    }

    public void notifyObservers(){
        //MapView is going to have different values
        //
    }

}
