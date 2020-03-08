import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class cardtrick {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int testCases = fio.nextInt();
        for (int i = 0 ; i < testCases ; i++) {
            int n = fio.nextInt();
            if (n == 1) {
                fio.println("1");
                continue;
            } else if (n == 2) {
                fio.println("2 1");
                continue;
            } else {
                Integer[] sequence = new Integer[n];
                Arrays.setAll(sequence, (index) -> 14 + index);
                sequence[1] = 1; // create an array {14,1,15,16...} when i get 14 later i just sequence[14-14]
                Queue<Integer> queue = new LinkedList<>(Arrays.asList(sequence));
                for (int j = 1 ; j < n+1 ; j++) {
                    for (int k = 0 ; k < j ; k++) {
                        int first = queue.poll(); //remove from front of queue
                        queue.offer(first);      //add to back of queue
                    }
                    int first = queue.poll();
                    if (j == 1) {
                        continue; //1 is already in correct position in array so ignore
                    } else {
                        sequence[first-14] = j;
                    }

                    if (queue.size() == 1) {
                        int front = queue.poll();
                        sequence[front-14] = j + 1;
                        break;
                    }
            }   // to convert Integer[] to String[] to print out the values
                String str = Arrays.toString(sequence)		  // [1, 2, 3, 4, 5]
                        .replaceAll("\\s+", "");   // [1,2,3,4,5]

                String[] strArray = str.substring(1, str.length() - 1)  // 1,2,3,4,5
                        .split(",");
                fio.println(String.join(" ", strArray));

        }
        }
        fio.close();
    }
}




