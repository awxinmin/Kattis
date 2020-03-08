import java.util.*;

public class conformity {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int noFrosh = fio.nextInt();
        HashMap<String,Integer> hm = new HashMap<>();
        for (int i = 0 ; i < noFrosh ; i++) {
            int[] combi = new int[5];
            for (int j = 0 ; j < 5 ; j++) {
                combi[j] = fio.nextInt();
            }
            Arrays.sort(combi);
            String key = Arrays.toString(combi);
            if (hm.containsKey(key)) {
                int value = hm.get(key);
                hm.put(key, ++value);
            } else {
                hm.put(key,1);
            }

        }
        ArrayList<Integer> noKeys = new ArrayList<>();
        int maxValueInMap=(Collections.max(hm.values()));
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                noKeys.add(entry.getValue());     // Print the key with max value
            }
        }
        int sum = 0;
        for (int i: noKeys) {
            sum += i;
        }
        fio.println(sum);






        fio.close();
    }
}
