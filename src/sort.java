/* uses Arrays.sort algo --> slower O(n^2log(n)) */

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/* sort using Arrays.sort */

public class sort {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int noNames = fio.nextInt();
        while (noNames != 0) {
            String[] names = new String[noNames];
            for (int j = 0; j < noNames; j++) {
                names[j] = fio.nextLine();
            }
            Arrays.sort(names, new StringComparator());
            for (String name : names) {
                fio.println(name);
            }
            noNames = fio.nextInt();
            if (noNames != 0) {
                fio.println();
            }

        }

        fio.close();
    }

}


