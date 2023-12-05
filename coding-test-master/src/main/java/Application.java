import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) throws IOException {
        new Application().run(Util.readFileFromClasspath("icd-codes.txt"));
    }

    private void run(String data) {
    	// You can ignore these lines for the task from here
    	// 
        List codes = new ArrayList();
        for (String line : data.split("\r?\n")) {
            String[] parts = line.split(";");
            String patient = parts[0];
            for (String code : parts[1].split(",")) {
                if (code.length() > 0) {
                    codes.add(code);
                }
            }
        }
        //
        // to here
        
        Map r = hugo(codes);
        System.out.println(StringUtils.join(r.keySet(), ", "));
        System.out.println(StringUtils.join(r.values(), ", "));
    }
/*
  This functionality has been set to iterate over the list, which its values has been extracted from file,
  to set key and value pair for each and every element in the list then return a map to the main method (run).
 */
    private Map hugo(Collection c) {
       Iterator list= c.iterator();
       Map<Object,Integer> map=new HashMap<>();
       while (list.hasNext())
       {
           Object x = list.next();
           if(map.containsKey(x))
               map.put(x,map.getOrDefault(x,map.get(x))+1);
           else
           map.put(x,map.getOrDefault(x,0)+1);
       }
       return map;
    }

//    private static Integer M = 1;
//
//    public static Map hugo(Collection c) {
//        Iterator q = c.iterator();
//        Map a = new HashMap();
//        while (q.hasNext()) {
//            Object x = q.next(); Integer b = (Integer) a.get(x);
//            if (null == b) a.put(x, new Integer(M)); else a.put(x, new Integer(b.intValue() + M));
//        }
//        return a;
//    }

}