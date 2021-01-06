import java.util.ArrayList;
import java.util.Collections;

public class weekvertices {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        while (true) {
            int noVertices = fio.nextInt();
            if (noVertices == -1) {
                break;
            }
            if (noVertices <= 2) {
                String f = "";
                for (int r = 0; r < noVertices; r++) {
                    f = f + Integer.toString(r) + " ";
                }
                fio.println(f);
                continue;
            }
            int[][] adjMatrix = new int[noVertices][noVertices];
            for (int r = 0; r < noVertices; r++) {
                for (int c = 0; c < noVertices; c++) {
                    adjMatrix[r][c] = fio.nextInt();
                }
            }
            ArrayList<Integer> noTri = new ArrayList<>();
            for (int r = 0; r < noVertices; r++) {
                if (noTri.contains(r)) {
                    continue;
                }
                int[] currVertice = adjMatrix[r];
                for (int j = 0; j < noVertices; j++) {
                    if (currVertice[j] == 0) {
                        continue;
                    }
                    for (int k = 0; k < noVertices; k++) {
                        if (currVertice[k] == 0) {
                            continue;
                        }
                        if (j != k & adjMatrix[j][k] == 1) {
                            noTri.add(r);
                            noTri.add(j);
                            noTri.add(k);
                            continue;

                        }
                    }
                }
            }
            String f = "";
            for (int i = 0; i < noVertices; i++) {
                if (!noTri.contains(i)) {
                    f = f + Integer.toString(i) + " ";
                }
            }
            fio.println(f);


        }
        fio.close();

    }
}
