/* uses insertion sort algo --> slower O(n^3) */

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

public class insertionsort {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int noNames = fio.nextInt();
        StringComparator sc = new StringComparator();
        while (noNames != 0) {
            String[] names = new String[noNames];
            for (int i = 0 ; i < noNames ; i++) {
                names[i] = fio.nextLine();
                String next = names[i];
                int j;
                for (j = i-1 ; j>=0 && (sc.compare(names[j] , next) > 0 ); j--) {
                    names[j+1] = names[j];
                }
                names[j + 1] = next;
            }
            for (String name : names) {
                fio.println(name);
            }
            noNames = fio.nextInt();
            if (noNames != 0 ) {
                fio.println();
            }
        }
    fio.close();
    }
}



class StringComparator implements Comparator<String> {

    public int compare(String n1, String n2) {
        String n1sub = n1.substring(0,2).toLowerCase();
        String n2sub = n2.substring(0,2).toLowerCase();
        return n1sub.compareTo(n2sub);

    }

}

/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
