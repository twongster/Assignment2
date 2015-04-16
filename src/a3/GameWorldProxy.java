package a3;

/**
 * Created by Tiffany Chiapuzio-Wong on 4/14/15.
 */
public class GameWorldProxy implements IGameWorld, Observable {
    private GameWorld gw;

    public GameWorldProxy(GameWorld gw){
        this.gw = gw;
    }

    @Override
    public void initLayout() {

    }

    public GameObjectCollection getWorldObjects() {
        return gw.getWorldObjects();
    }

    public int getLives() {
        return gw.getLives();
    }

    @Override
    public void setLives(int lives) {

    }

    public int getCarFuelLevel() {
        return gw.getCarFuelLevel();
    }

    public int getClock() {
        return gw.getClock();
    }

    @Override
    public void setClock(int clock) {

    }

    public int getCarDamageLevel() {
        return gw.getCarDamageLevel();
    }

    public void tick(int x) {

    }

    public boolean isSound() {
        return gw.isSound();
    }

    @Override
    public void setSound(boolean sound) {

    }

    @Override
    public void toggleSound() {

    }

    @Override
    public void accelerateCar() {

    }

    @Override
    public void brakeCar() {

    }

    @Override
    public void steerCarLeft() {

    }

    @Override
    public void steerCarRight() {

    }

    @Override
    public void createOilSlick() {

    }

    @Override
    public void createCar(int checkpt) {

    }

    @Override
    public void loseLife() {

    }

    @Override
    public void createFuelCan() {

    }

    @Override
    public void carCollision() {

    }

    @Override
    public void createNpcCar(int carNumber) {

    }

    @Override
    public void createBird() {

    }

    @Override
    public boolean birdCollision() {
        return false;
    }

    @Override
    public boolean pylonExists(int pylonNum) {
        return false;
    }

    @Override
    public void pylonCollision(int pylonNum) {

    }

    @Override
    public boolean checkWin() {
        return false;
    }

    @Override
    public void fuelCanCollision() {

    }

    @Override
    public Car findPlayersCar() {
        return null;
    }

    @Override
    public void carEnteredOilSlick() {

    }

    @Override
    public void carExitedOilSlick() {

    }

    @Override
    public void changeObjectsColors() {

    }

    @Override
    public int findNextPylon() {
        return -1;
    }

    @Override
    public Pylon getNextPylon() {
        return null;
    }

    @Override
    public void updateAllMoveables(int x) {

    }

    @Override
    public boolean carCanMove() {
        return false;
    }

    @Override
    public int getHighestPylon() {
        return gw.getHighestPylon();
    }

    public void addObserver(Observer obs) {
        gw.addObserver(obs);
    }

    @Override
    public void notifyObservers() {
        gw.notifyObservers();
    }

    @Override
    public void changeStrategies() {

    }
}
