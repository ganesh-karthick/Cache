/*
 * The main driver program that initializes and tests the cache behavior
 */
package cache;

/**
 *
 * @author gk
 */
public class DriverProgram {

    /**
     * @param args the command line arguments
     */
    
        public static void TestBehavior(Cache c) {
                
        //Initialize Cache 
        for(int i = 0;i < c.getCapacity();i++){
            c.add(new Integer(i), "val"+new Integer(i));
        }
        
        // Access each Value , key times except the lastValue
        
        for(int i=0;i < c.getCapacity()-1;i++)
            for(int j=0;j < i;j++)
                assert(("val"+new Integer(i))
                        .equalsIgnoreCase((String)c.get(new Integer(i))));
        
        // In LRU the last value would have been replaced , by this add
        // while in Random any one element would have been replaced
        c.add(new Integer(c.getCapacity()+2),
                "val"+ new Integer(c.getCapacity()+2));
        
        if(c.getCacheType() == CacheType.LRU)
            assert(c.get(new Integer(c.getCapacity()-1)) == null);
        else if(c.getCacheType() ==  CacheType.RANDOM) {
            
          //check anyone value is removed
          boolean isValueRemoved = false;
          for(int i = 0;i < c.getCapacity();i++){
            if(c.get(new Integer(i)) == null){
                isValueRemoved = true;
            }
          }
          assert(isValueRemoved);
        }
        
        //Cache Update and Test
         String value= "val"+new Integer(2);
         c.add(new Integer(1),value);
         assert(value==c.get(new Integer(1)));
         
        //Cache Null Value test
         try{
            c.add(null,null);
         }
         catch(NullPointerException ex) {
             assert(ex instanceof NullPointerException);
         }
         
         //Non-existent value
         assert(c.get(new Integer(-99)) == null);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Cache c = CacheFactory.<String,String>createCache(4, CacheType.LRU);
        TestBehavior(c);
        c =  CacheFactory.<String,String>createCache(4, CacheType.RANDOM);
        TestBehavior(c);
    }
}
