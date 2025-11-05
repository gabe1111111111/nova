package gabriel.nova;

public class HashMap<T> {
    public ArrayList<T>[] data;
    public int dataSize;
    public int size;

    public HashMap() {
    }
    public boolean  contains(T target){
        if(data[target.hashCode() % dataSize] == null)return false;
        return data[target.hashCode() % dataSize].contains(target);
    }
    public void add (T addend){

    }
    private void resize(){

    }
}
