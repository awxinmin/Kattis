public class ladice {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int noItems = fio.nextInt();
        int noDrawers = fio.nextInt();
        UnionFind disjointSet = new UnionFind(noDrawers); // create 5 sets {0},{1},{2},{3},{4}
        for (int i = 0 ; i < noItems ; i++) {
            int A = fio.nextInt();
            int B = fio.nextInt();
            int result = disjointSet.unionSet(A,B);
            if (result == 1 ){
                fio.println("LADICA");
            }  else {
                fio.println("SMECE");
            }
        }

        fio.close();

    }
}





class UnionFind {
    public int[] p;
    public int[] rank;
    public int[] items;
    public int[] size;
    public int numSets;

    public UnionFind(int N) {
        p = new int[N+1];
        rank = new int[N+1];
        numSets = N;
        items = new int[N+1];
        size = new int[N+1];
        for (int i = 1; i <= N; i++) {
            p[i] = i;
            rank[i] = 0;
            items[i] = 0;
            size[i] = 1;
        }
    }


    public int findSet(int i) {
        if (p[i] == i) return i;
        else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public int unionSet(int i, int j) {
        if (size[findSet(i)] > items[findSet(i)] || size[findSet(j)] > items[findSet(j)]) {
            if (!isSameSet(i, j)) {
                numSets--;
                int x = findSet(i), y = findSet(j);
                // rank is used to keep the tree short
                if (rank[x] > rank[y]) {
                    p[y] = x;
                    items[x] = items[x] + items[y] + 1;
                    size[x] = size[x] + size[y];
                } else {
                    p[x] = y;
                    items[y] = items[x] + items[y] + 1;
                    size[y] = size[x] + size[y];
                    if (rank[x] == rank[y])
                        rank[y] = rank[y] + 1;
                }
            } else {
                int x = findSet(i);
                items[x] += 1;
            }
            return 1;
        } else {
            return 0;
        }
    }

    public int numDisjointSets() { return numSets; }

}


