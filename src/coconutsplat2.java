import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class coconutsplat2 {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int syllabus = fio.nextInt();
        int noPlayers = fio.nextInt();
        LinkedList<P> players = new LinkedList<>();
        for (int i = 1 ; i <= noPlayers ; i++) {
            players.add(new P(i,3));

        }
        while (players.size() > 1) {
            for (int i = 1 ; i < syllabus ; i++) {
                P player = players.poll();
                players.offer(player);
            }
            P chosen = players.peek();
            if (chosen.getLives() == 3) {
                chosen.reduceLives();
                players.add(1, new P(chosen.getNumber(),2));
            } else if (chosen.getLives() == 2) {
                chosen.reduceLives();
                players.offer(players.poll());
            } else if (chosen.getLives() == 1) {
                players.poll();
            }

        }
        fio.println(players.poll().getNumber());
    fio.close();
    }

}



class P {
    int number;
    int lives;

    public P(int number,int lives) {
        this.number = number;
        this.lives = lives;
    }

    public int getNumber() {
        return this.number;
    }

    public int getLives() {
        return this.lives;
    }

    public void reduceLives() {
        this.lives--;
    }


}

