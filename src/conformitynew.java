import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class conformitynew {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int frosh = fio.nextInt();
        HashMap<ArrayList<Integer>,Integer> hm = new HashMap<>();
        for (int j = 0 ; j < frosh ; j++) {
            ArrayList<Integer> combi = new ArrayList<>();
            for (int i = 0 ; i < 5 ; i++) {
                combi.add(fio.nextInt());
            }
            Collections.sort(combi);
            if (! hm.containsKey(combi)) {
                hm.put(combi,1);
            } else {
                hm.put(combi,hm.get(combi)+1);
            }
        }
        int max = Collections.max(hm.values());
        if (max != 1) {
            fio.println(max);
        } else {
            fio.println(frosh);
        }


        fio.close();


    }
}
