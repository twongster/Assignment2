package a2;

import java.util.Vector;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 */
public class GameObjectCollection implements Collection {
    private Vector objectCollection;

    public GameObjectCollection() {
        objectCollection = new Vector();
    }

    public void add(Object x){
        objectCollection.addElement(x);
    }

    public Iterator getIterator(){
        return new GameObjectIterator();
    }

    public void remove(Object x){
        objectCollection.remove(x);
    }

    private class GameObjectIterator implements Iterator{
        private int currentIndex;

        public GameObjectIterator(){
            currentIndex = -1;
        }

        public boolean hasNext() {
            if(objectCollection.size() <=0 || (currentIndex == objectCollection.size()) ){return false; }
            return true;
        }

        public Object getNext() {
            return null;
        }
    }


}
