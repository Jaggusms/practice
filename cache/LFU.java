// package cache;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.Map;

// public class LFU {
//     public static HashMap<String, Integer> cache = new HashMap<>();
//     public static final Integer maxSize=3;
//     public static void main(String[] args) {
//         LFU cacheAlgo = new LFU();
//         cacheAlgo.addCache("a");
//         cacheAlgo.addCache("b");
//         cacheAlgo.addCache("b");
//         cacheAlgo.addCache("c");
//         cacheAlgo.addCache("c");
//         // cacheAlgo.addCache("c");
//         // cacheAlgo.addCache("d");

//     }
//     public boolean removeCache(){
//         HashMap<Integer, String > newCache= new HashMap<>();

//         for (Map.Entry<String,Integer> entry: cache.entrySet()){
//             newCache.put(entry.getValue(), entry.getKey());
//         }

//         ArrayList<Integer> couters = new ArrayList<>(cache.values());
//         couters.sort(Comparator.comparingInt(a->a));
//         // couters.sort(Comparator.comparingInt(Integer::intValue));

//         // // Using a 2-argument lambda (beware of potential integer overflow with a - b)
//         // couters.sort((a, b) -> a - b);

//         // // Safer alternative using Integer.compare (avoids overflow)
//         // couters.sort((a, b) -> Integer.compare(a, b));

//         // // Or even simpler:
//         // couters.sort(Integer::compareTo);
//         System.out.println(cache);
//         cache.remove(newCache.get(couters.get(0)));
//         System.out.println(cache);
//         return  true;

//     }
//     public boolean addCache(String cacheValue){
//         if (cache.size() >= maxSize){
//             removeCache();
//             // this.removeCache();
//         }
//         cache.put(cacheValue, cache.getOrDefault(cacheValue,0)+1);
//         System.out.println(cache);
//         return true;
//     }


// }
