import java.util.*;

public class baconeggsandspam {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int customers = fio.nextInt();
        while (customers != 0) {
            HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
            for (int w = 0 ; w < customers ; w++ ) {
                String[] order = fio.nextLine().split(" ");
                for (String item : Arrays.copyOfRange(order,1,order.length)) {
                    if (!hm.containsKey(item)) {
                        hm.put(item, new ArrayList<>(Arrays.asList(order[0])));
                    } else {
                        ArrayList<String> value = hm.get(item);
                        value.add(order[0]);
                        hm.put(item, value);
                    }
                }
            }
            // Treemap to store values of hashmap.
            // Treemap is naturally sorted so the keys of the hashmap will be sorted
            TreeMap<String, ArrayList<String>> sorted = new TreeMap<>();
            sorted.putAll(hm);
            for (Map.Entry<String, ArrayList<String>> entry : sorted.entrySet()) {  // Itrate through hashmap
                String s = entry.getKey() + " ";
                ArrayList<String> values = entry.getValue();
                Collections.sort(values);
                for (String st : values) {
                    s = s + " " + st;
                }
                fio.println(s);
            }
            fio.println();

            customers = fio.nextInt();
        }
        fio.close();
    }
}
