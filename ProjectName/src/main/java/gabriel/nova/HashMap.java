package gabriel.nova;

public class HashMap<T> {
    public ArrayList<T>[] data;
    public int dataSize;
    public int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        data = (ArrayList<T>[])new ArrayList<?>[1000];
        dataSize = 1000;
        size = 0;
    }
    @SuppressWarnings("unchecked")
    private HashMap(int size){
        data = (ArrayList<T>[])new ArrayList<?>[size];
        dataSize = size;
        this.size = 0;
    }
    public boolean  contains(T target){
        int index = Math.absoluteValue(target.hashCode() % dataSize);
        if(data[index] == null)return false;
        return data[index].contains(target);
    }
    public void add (T addend){
        ArrayList<T> location = data[Math.absoluteValue(addend.hashCode() % dataSize)];
        if(location == null) location = new ArrayList<>();
        if(!location.contains(addend)){
            location.add(addend);
            size++;
        }
        
        data[Math.absoluteValue(addend.hashCode() % dataSize)] = location;
        if(size >= dataSize) resize();
    }
    private void resize(){
        HashMap<T> newMap = new HashMap<>(dataSize * 10);
        for(ArrayList<T> i : data){
            if(i == null) continue;
            for(T j : i){
                newMap.add(j);
            }
        }
        data = newMap.data;
        dataSize *= 10;
    }

}
