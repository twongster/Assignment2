package a3;

import java.util.Vector;

/**
 * Created by Tiffany Chiapuzio-Wong on 3/14/15.
 */
public class GameObjectCollection implements ICollection {
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
            if(objectCollection.size() <=0 || (currentIndex == objectCollection.size()-1) ){return false; }
            return true;
        }

        public Object getNext() {
            currentIndex++;
            return(objectCollection.elementAt(currentIndex));
        }
    }


}
