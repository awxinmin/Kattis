import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class lostmap {

    public static ArrayList<ArrayList<IntegerTriple>> AdjList = new ArrayList < ArrayList < IntegerTriple > >();
    public static ArrayList<Boolean> taken = new ArrayList<>();
    public static PriorityQueue< IntegerTriple > pq = new PriorityQueue<>();

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int V = fio.nextInt();
        int[] p = new int[V];  // 0 index empty

        for (int i = 0 ; i < V; i++) {
            ArrayList < IntegerTriple > Neighbor = new ArrayList < IntegerTriple >(); // create vector of Integer pair
            AdjList.add(Neighbor); // store blank vector first
            p[i] = -1;
        }

        for (int i = 0; i < V; i++) { // store graph information in Adjacency List
            for (int j = 0 ; j < V ; j++) {
                int w = fio.nextInt();
                if (w != 0) {
                    int u = i , v = j;
                    AdjList.get(u).add(new IntegerTriple(v, w, u));   //u is dummy for p array
                }
            }
        }

        taken.addAll(Collections.nCopies(V, false));
        // take any vertex of the graph, for simplicity, vertex 1, to be included in the MST
        process(0);

        while (!pq.isEmpty()) { // we will do this until all V vertices are taken (or E = V-1 edges are taken)
            IntegerTriple front = pq.poll();

            if (!taken.get(front.second())) { // we have not connected this vertex yet
                p[front.second()] = front._third;
                process(front.second());
            }

        }

        for (int i = 1 ; i < V ; i++) {
            fio.print(p[i] + 1);
            fio.print(" ");
            fio.println(i+1);
        }

        fio.close();

    }



    public static void process(int vtx) {
        taken.set(vtx, true);
        for (int j = 0; j < AdjList.get(vtx).size(); j++) {   //go through all neighbours of that vertex.
            IntegerTriple v = AdjList.get(vtx).get(j);
            if (!taken.get(v.first())) {    // if neighbour is not in MST yet
                pq.offer(new IntegerTriple(v.second(), v.first(), vtx));  // we sort by weight then by adjacent vertex
            }
        }
    }
}


/*
class IntegerTriple implements Comparable<IntegerTriple> {
    public Integer _first, _second, _third;

    public IntegerTriple(Integer f, Integer s, Integer t) {
        _first = f;
        _second = s;
        _third = t;
    }

    public int compareTo(IntegerTriple o) {
        if (!this.first().equals(o.first()))
            return this.first() - o.first();
        else
            return this.second() - o.second();
    }

    Integer first() { return _first; }

    Integer second() { return _second; }
}
*/
