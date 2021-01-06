import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class millionairemadness {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int row = fio.nextInt();
        int col = fio.nextInt();
        int[][] visited = new int[row][col];
        int[][] vault = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                visited[i][j] = 0;
                vault[i][j] = fio.nextInt();
            }
        }

        int n = 0;
        PriorityQueue<IntegerTriple> pq = new PriorityQueue<>();
        pq.add(new IntegerTriple(0,0,0));

        while(!pq.isEmpty()) {

            IntegerTriple front = pq.poll();
            int r = front.first();
            int c = front.second();
            visited[r][c] = 1;
            if (r == row - 1 && c == col - 1) {
                break;
            }
            if (front.third() > n) {
                n = front.third();
            }

            // go through neighbours
            if (c != vault[0].length - 1) {  //check right
                if (vault[r][c + 1] > vault[r][c] && visited[r][c + 1] == 0) {
                    pq.add(new IntegerTriple(r,c+1, vault[r][c + 1] - vault[r][c]));
                } else {
                    if (visited[r][c + 1] == 0) {
                        pq.add(new IntegerTriple(r, c + 1, 0));
                    }
                }
            }

            if (c != 0) {  //check left
                if (vault[r][c - 1] > vault[r][c] && visited[r][c - 1] == 0) {
                    pq.add(new IntegerTriple(r,c-1, vault[r][c - 1] - vault[r][c]));
                } else {
                    if (visited[r][c - 1] == 0) {
                        pq.add(new IntegerTriple(r, c - 1, 0));
                    }
                }
            }

            if (r != vault.length - 1) {  //check bottom
                if (vault[r + 1][c] > vault[r][c] && visited[r + 1][c] == 0) {
                    pq.add(new IntegerTriple(r + 1,c, vault[r + 1][c] - vault[r][c]));
                } else {
                    if (visited[r + 1][c] == 0) {
                        pq.add(new IntegerTriple(r + 1, c, 0));
                    }
                }
            }

            if (r != 0) {  //check top
                if (vault[r - 1][c] > vault[r][c] && visited[r - 1][c] == 0) {
                    pq.add(new IntegerTriple(r - 1,c, vault[r - 1][c] - vault[r][c]));
                } else {
                    if (visited[r - 1][c] == 0) {
                        pq.add(new IntegerTriple(r - 1, c, 0));
                    }
                }
            }

        }

        fio.println(n);
        fio.close();
    }
}


class IntegerTriple implements Comparable<IntegerTriple> {
    public Integer _first, _second, _third;

    public IntegerTriple(Integer f, Integer s, Integer t) {
        _first = f;
        _second = s;
        _third = t;
    }

    public int compareTo(IntegerTriple o) {
        return this.third() - o.third();
    }

    Integer first() { return _first; }
    Integer second() { return _second; }
    Integer third() { return _third; }

    public String toString() { return first() + " " + second() + " " + third(); }
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
