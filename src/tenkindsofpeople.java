import java.util.LinkedList;

public class tenkindsofpeople {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int r = fio.nextInt();
        int c = fio.nextInt();
        int[][] visited = new int[r][c];
        int[][] map = new int[r][c];

        for (int i = 0; i < r; i++) {
            String line = fio.nextLine();
            for (int j = 0; j < c; j++) {
                String sinput = Character.toString(line.charAt(j));
                int input = Integer.parseInt(sinput);
                visited[i][j] = 0;
                map[i][j] = input;
            }
        }


        int group = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j] == 0) {
                    group++;
                    BFS(map, visited, i, j, group);
                }
            }
        }

        int q = fio.nextInt();
        for (int i = 0; i < q; i++) {
            int startr = fio.nextInt() - 1;
            int startc = fio.nextInt() - 1;
            int endr = fio.nextInt() - 1;
            int endc = fio.nextInt() - 1;
            int bord = map[startr][startc];

            if (visited[startr][startc] == visited[endr][endc]) {
                String out = bord == 0 ? "binary" : "decimal";
                fio.println(out);
            } else {
                fio.println("neither");
            }

        }


        fio.close();
    }

    public static void BFS(int[][] map, int[][] visited, int i, int j, int group) {
        LinkedList<int[]> queue = new LinkedList<>();
        int source = map[i][j];
        visited[i][j] = group;
        queue.push(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] front = queue.poll();
            int r = front[0];
            int c = front[1];

            // go through neighbours
            if (r != 0) {  //check top
                if (map[r - 1][c] == source && visited[r - 1][c] == 0) {
                    visited[r - 1][c] = group;
                    queue.push(new int[]{r - 1, c});
                }
            }
            if (r != map.length - 1) {  //check bottom
                if (map[r + 1][c] == source && visited[r + 1][c] == 0) {
                    visited[r + 1][c] = group;
                    queue.push(new int[]{r + 1, c});
                }
            }
            if (c != map[0].length - 1) {  //check right
                if (map[r][c + 1] == source && visited[r][c + 1] == 0) {
                    visited[r][c + 1] = group;
                    queue.push(new int[]{r, c + 1});
                }
            }

            if (c != 0) {  //check left
                if (map[r][c - 1] == source && visited[r][c - 1] == 0) {
                    visited[r][c - 1] = group;
                    queue.push(new int[]{r, c - 1});
                }

            }

        }
    }
}





