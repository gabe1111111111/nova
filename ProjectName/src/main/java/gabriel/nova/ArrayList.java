package gabriel.nova;

import java.util.Iterator;
/**
 * this class emulates the java ArrayList class.
 * only the necessary functions are implemented
 * @author Gabriel Lacey
 */
public class ArrayList<T> implements Iterable<T> {
    private T[] data;
    private  int size;
    /**
     * constructor initializes data and size
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
    data = (T[]) new Object[1];
    size = 0;
    }
    
    /**
     * adds addend to the array
     * @param addend what is getting added to the array
     */
    public void add(T addend){
        ensureCapacity(size+1);
        size++;
        data[size] = addend;
    }
    /**
     * if the data length is not already at least as long as the length
     * it will ensure the data length is either length or double the current size witch ever one is greater
     * @param length minimum length of the array
     */
    @SuppressWarnings({"unchecked", "ManualArrayToCollectionCopy"})
    private void ensureCapacity(int length){
        if(size >= length)return;
        int max = length;
        if(size*size > max)max = size*size;
        T[] temp = (T[])new Object[max];
        
        for(int i = 0; i < size; i++){
            temp[i] = data[i];
        }  
        data = temp;
    }
    /**
     * returns data[location]
     * @param location index of the array
     * @return data at location
     * @throws IndexOutOfBoundsException if location >= size of the array
     */
    public T get(int location)throws IndexOutOfBoundsException{
        if(location >= size) throw new IndexOutOfBoundsException();
        return data[location];
    }
    @Override
    public Iterator<T> iterator() {
        // TO DO Auto-generated method stub
        return new ArrayListIterator<>();
       // throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
   /**
    * removes null objects from visible part of the array
    */
    @SuppressWarnings("unchecked")
    public void clean(){
        T[] temp = (T[])new Object[size];
        int newSize = size;
        for(int i = 0; i < size; i++){
            if(data[i] != null) temp[i] = data[i];
            else newSize--;
        
        }  
        size = newSize;
        data = temp;
    }
    private class ArrayListIterator<E> implements Iterator<E>{
        private int current;
        @Override
        public boolean hasNext() {
            // TO DO Auto-generated method stub
            if(current + 1 >= size) return false;

            return data[current + 1]  != null;
            //throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            // TO DO Auto-generated method stub
            current++;
            return (E)data[current];
            //throw new UnsupportedOperationException("Unimplemented method 'next'");
        }

       
    
        
    }
    /**
     * 
     * @return number of elements stored
     */
    public int size(){
        return size;
    }
}
