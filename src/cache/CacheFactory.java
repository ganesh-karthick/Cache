package cache;

/*
 * CacheFactory returns the type of cache wanted given the cache size and 
 * eviction strategy. Also defines enum values for cache type
 */

/**
 *
 * @author gk
 */
enum CacheType { LRU , RANDOM };
public class CacheFactory {
    
    public static<Key,Value> Cache createCache(int capacity, CacheType ct){
        if(ct == CacheType.LRU)
            return new LRUCache<Key,Value>(capacity);
        else if(ct == CacheType.RANDOM)
            return new RandomCache<Key,Value>(capacity);
        else
             throw new  IllegalArgumentException("Invalid CacheType");
    }
    
}
