/*
 * Specialized sub class that implements Random eviction strategy using an
 * ArrayList and Hashmap
 */
package cache;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gk
 */
public class RandomCache<Key,Value> extends Cache<Key, Value> {
    Random r;
    ArrayList<Key> list;
    
    public RandomCache(int capacity) {
        super(capacity, CacheType.RANDOM);
        list = new ArrayList<Key>(capacity);
        r = new Random(System.currentTimeMillis());
    }
    
    @Override
    synchronized void add(Key key, Value value) {
        if(key == null || value == null) {
            throw new NullPointerException("Null key or value");
        }
        //check for full capacity
        if(list.size() == getCapacity()){
            // remove random element from list 
            // abs makes sure index is always positive
            int index = Math.abs(r.nextInt())%(list.size());
            hmap.remove(list.remove(index));
        }
        list.add(key);
        hmap.put(key,value);
    }

    @Override
    Value get(Key key) {
        if(key == null){
            throw new NullPointerException("Key is Null");
        }
        
        if(hmap.containsKey(key)) {
            return hmap.get(key);
        }
        
        return null;
    }
    
}
