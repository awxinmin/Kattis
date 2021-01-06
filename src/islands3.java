import java.io.*;
import java.util.StringTokenizer;

public class islands3 {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int rows = fio.nextInt();
        int cols = fio.nextInt();
        String[][] adjMatrix = new String[rows][cols];
        int[][] visited = new int[rows][cols];
        for (int r = 0 ; r < rows ; r++) {
            String line = fio.nextLine();
            for (int c = 0 ; c < cols ; c++) {
                adjMatrix[r][c] = Character.toString(line.charAt(c));
                visited[r][c] = 0;
            }
        }
        int cc = 0;
        for (int r = 0 ; r < rows ; r++) {
            for (int c = 0 ; c < cols ; c++) {
                if (visited[r][c] == 0 && adjMatrix[r][c].equals("L")) {
                    DFSrec(visited,adjMatrix,r,c);
                    cc += 1;
                }
            }
        }
        fio.println(cc);

        fio.close();
    }


    public static void DFSrec(int[][] visited, String[][] adjMatrix, int r, int c) {
        visited[r][c] = 1;
        if (r != 0){  //check top
            if((adjMatrix[r - 1][c].equals("L") || adjMatrix[r - 1][c].equals("C")) && visited[r - 1][c] == 0) {
                DFSrec(visited,adjMatrix,r - 1,c);
            }
        }
        if (r != adjMatrix.length - 1){  //check bottom
            if((adjMatrix[r + 1][c].equals("L") || adjMatrix[r + 1][c].equals("C")) && visited[r + 1][c] == 0) {
                DFSrec(visited,adjMatrix,r + 1,c);
            }
        }
        if (c != adjMatrix[0].length - 1){  //check right
            if((adjMatrix[r][c + 1].equals("L") || adjMatrix[r][c + 1].equals("C")) && visited[r][c + 1] == 0) {
                DFSrec(visited,adjMatrix,r,c+1);
            }
        }

        if (c != 0){  //check left
            if((adjMatrix[r][c-1].equals("L") || adjMatrix[r][c - 1].equals("C")) && visited[r][c - 1] == 0) {
                DFSrec(visited,adjMatrix,r,c - 1);
            }
        }
    }
}

