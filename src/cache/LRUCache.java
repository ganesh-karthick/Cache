/*
 * Specialized Sub class to handle implementation of LRU using Linked list and
 * HashMap
 */
package cache;

import java.util.LinkedList;

/**
 *
 * @author gk
 */
public class LRUCache<Key,Value> extends Cache<Key,Value> {
    
    LinkedList<Key> list;
    
    public LRUCache(int capacity){
        super(capacity,CacheType.LRU);
        //Keeps track of MRU values at first and LRU values at last
        list = new LinkedList<Key>();
    }

    @Override
    synchronized void add(Key key, Value value){
        
        if(key == null || value == null) {
            throw new NullPointerException("Null key or value");
        }
        //check for full capacity
        if(list.size() == getCapacity()){
            // remove LRU
           hmap.remove(list.removeLast());
        }
        // Count update as Access
        if(hmap.containsKey(key)){
            list.remove(key);
            list.addFirst(key);
        }
        list.add(key);
        hmap.put(key,value);
    }
    
    @Override
    synchronized Value get(Key key) {
        
        if(key == null){
            throw new NullPointerException("Key is Null");
        }
        
        if(hmap.containsKey(key)) {
            //Most recently used is put in Front, least recently used in last
            list.remove(key);
            list.addFirst(key);
            return hmap.get(key);
        }
        
        return null;
    }
    

}
