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


/**
 * Fast I/O
 * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 */
class FastIO extends PrintWriter
{
    BufferedReader br;
    StringTokenizer st;

    public FastIO()
    {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    long nextLong()
    {
        return Long.parseLong(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}
