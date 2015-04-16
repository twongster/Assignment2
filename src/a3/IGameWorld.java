package a3;

/**
 * Created by Tiffany Chiapuzio-Wong on 4/14/15.
 */
public interface IGameWorld {
    /*
         * This method creates the initial layout of the GameWorld
         * Each object created is passed a random locationX and locationY
         * The first pylon created is set to be at the same starting location as the car.
         */
    void initLayout();

    GameObjectCollection getWorldObjects();

    int getClock();

    void setClock(int clock);

    int getLives();

    void setLives(int lives);

    boolean isSound();

    void setSound(boolean sound);

    void toggleSound();

    void accelerateCar();

    void brakeCar();

    void steerCarLeft();

    void steerCarRight();

    void createOilSlick();

    void createCar(int checkpt);

    void loseLife();

    void createFuelCan();

    //returns True if the car can continue to play after collision
    void carCollision();

    void createNpcCar(int carNumber);

    void createBird();

    boolean birdCollision();

    boolean pylonExists(int pylonNum);

    void pylonCollision(int pylonNum);

    //Returns true if the car has collided with the last pylon and has won the race
    boolean checkWin();

    void fuelCanCollision();

    Car findPlayersCar();

    void carEnteredOilSlick();

    void carExitedOilSlick();

    //Changes the color of every object who is of type IColorable
    void changeObjectsColors();

    int findNextPylon();

    Pylon getNextPylon();

    void updateAllMoveables();

    boolean carCanMove();

    int getHighestPylon();

    int getCarFuelLevel();

    int getCarDamageLevel();

    void tick(int x);

    void addObserver(Observer obs);

    void notifyObservers();

    void changeStrategies();

    String toString();
}
