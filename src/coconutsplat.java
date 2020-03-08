import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class coconutsplat {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int syllabus = fio.nextInt();
        int noPlayers = fio.nextInt();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 1 ; i <= noPlayers ; i++) {
            players.add(new Player(i,"folded"));
        }
        int startPlayer = 0;
        while (players.size() > 1) {
            fio.println("Next Round");
            int result = (syllabus - 1) % players.size();
            int nextPlayer = (result + startPlayer) % players.size();
            fio.println("Next player index = " + nextPlayer);
            Player nextP = players.get(nextPlayer);

            if (nextP.getState().equals("folded")) {
                fio.println(nextP.getNumber() + " " + nextP.getState());
                nextP.changeState("fist");
                players.add(nextPlayer+1, new Player(nextP.getNumber(),"fist"));
                fio.println("changed to " + nextP.getNumber() + " " + nextP.getState());
                fio.println("Array size = " + players.size());
                startPlayer = nextPlayer;
            }
            else if (nextP.getState().equals("fist")) {
                fio.println("Array size = " + players.size());
                nextP.changeState("palm");
                fio.println(nextP.getNumber() + " " + nextP.getState());
                startPlayer = (nextPlayer + 1) % players.size();
            }
            else if (nextP.getState().equals("palm")) {
                if (nextPlayer == (players.size() - 1)) {
                    startPlayer = 0;
                } else {
                    startPlayer = nextPlayer;
                }
                players.remove(nextPlayer);
                fio.println(nextP.getNumber() + " " + nextP.getState());
                fio.println("Array size = " + players.size());
            }

        }
        fio.println(players.get(0).getNumber());
        fio.close();
    }
}



class Player {
    int number;
    String state;

    public Player(int number,String state) {
        this.number = number;
        this.state = state;
    }

    public int getNumber() {
        return this.number;
    }

    public String getState() {
        return this.state;
    }

    public void changeState(String newState) {
        this.state = newState;
    }


}

