/*
 * Abstract class that defines common operations to any cache irrespective of
 * any cacheStrategy , using HashMap. It can be extended to implement other 
 * Cache strategies in future. 
 */
package cache;



import java.util.HashMap;


/**
 *
 * @author gk
 */


public abstract class Cache<Key,Value> {
    
    private int capacity;
    private CacheType ct;
    private final float LOAD_FACTOR = 0.75f;
    
    protected HashMap<Key,Value> hmap;
    
    public Cache(int capacity, CacheType ct){
        this.capacity = capacity;
        this.ct = ct;
        hmap = new HashMap<Key,Value>(this.capacity, LOAD_FACTOR);
    }
    
    boolean exists(Key key) {
        return hmap.containsKey(key);
    }

    CacheType getCacheType() {
        return ct;
    }

    public int getCapacity() {
        return capacity;
    }
    
    abstract void add(Key key, Value value);
    abstract Value get(Key key);

    @Override
    public String toString() {
        return "Cache{" + "ht=" + hmap.toString() + '}';
    }
    
    
}
