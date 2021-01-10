import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class cardtricknew {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int testCases = fio.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = fio.nextInt();
            int[] arr = new int[n];
            LinkedList<Integer> ll = new LinkedList<>();
            for (int j = 0; j < n; j++) {
                ll.add(j);
            }
            for (int z = 1; z <= n; z++) {
                for (int j = 0; j < z; j++) {
                    ll.offer(ll.poll());
                }
                int index = ll.poll();
                arr[index] = z;
            }

            fio.println(Arrays.toString(arr));

        }
        fio.close();
    }
}



