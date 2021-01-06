import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class humancannonballrun {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        double[] start = new double[]{fio.nextDouble(),fio.nextDouble()};
        double[] end = new double[]{fio.nextDouble(),fio.nextDouble()};
        int noCannons = fio.nextInt();

        double[][] all = new double[noCannons + 2][2];
        all[0] = start;
        all[noCannons + 1] = end;
        for (int i = 1; i <= noCannons ; i++) {
            all[i] = new double[]{fio.nextDouble(),fio.nextDouble()};
        }

        ArrayList<ArrayList<IntegerPair>> AdjList = new ArrayList<>();


        for (int i = 0; i < noCannons + 2; i++)
            AdjList.add(new ArrayList < IntegerPair >()); // add neighbor list to Adjacency List


        // stores edge weight aka time

        // from start to every point by just running
        for (int i = 1; i < noCannons + 2; i++) {
            AdjList.get(0).add(new IntegerPair((distance(all[0], all[i]) / 5.0) , i));
        }

        // from cannon to any cannon or end point
        for (int r = 1; r < noCannons + 1; r++) {
            for (int c = 1; c < noCannons + 2; c++) {
                if (r == c) {continue;}
                else {
                    double time = Math.min((distance(all[r],all[c]) / 5.0), Math.abs(((distance(all[r],all[c])) - 50) / 5.0) + 2);
                    AdjList.get(r).add(new IntegerPair(time,c));
                }
            }
        }


        // Run Dijksta

        // initSSSP(int s)  // initialization phase
        ArrayList< Double > D = new ArrayList<>();
        D.addAll(Collections.nCopies(noCannons + 2, 10000000.0)); // use 1B to represent INF
        D.set(0, 0.0); // this is what we know so far

        PriorityQueue<IntegerPair> pq = new PriorityQueue <> ();
        pq.add(new IntegerPair(0.0,0));

        while (!pq.isEmpty()) { // main loop
            IntegerPair top = pq.poll(); // greedy: pick shortest unvisited vertex
            double d = top.first();
            int u = top.second();

            if (d == D.get(u)) {
                // go through all neighbours
                for (int i = 0 ; i < AdjList.get(u).size() ; i++) {
                    int v = AdjList.get(u).get(i).second();
                    double w = AdjList.get(u).get(i).first();
                    if (D.get(v) > D.get(u) + w ) {
                        D.set(v, D.get(u) + w);
                        pq.add(new IntegerPair(D.get(v),v));
                    }
                }
            }
        }

        fio.println(D.get(noCannons + 1));
        fio.close();


    }



    public static double distance(double[] start, double[] end) {
        return Math.sqrt(Math.pow(start[0] - end[0], 2) + Math.pow(start[1] - end[1], 2));
    }


}

class IntegerPair implements Comparable<IntegerPair> {
    Double _first;
    Integer _second;

    public IntegerPair(Double f, Integer s) {
        _first = f;    //time
        _second = s;  // node
    }

    public int compareTo(IntegerPair o) {
        if (!this.first().equals(o.first())) {
            if (this.first() > o.first()) {
                 return 1;
            }  else { return 0; }
        }
        else {
            if (this.second() > o.second()) {
                return 1;
            }  else { return 0; }

        }
    }

    Double first() { return _first; }
    Integer second() { return _second; }
}

