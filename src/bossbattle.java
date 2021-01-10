import java.util.ArrayList;

public class bossbattle {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int pillars = fio.nextInt();
        StringBuilder room = new StringBuilder("B".repeat(pillars));
        int noBombs = 0;
        int idxBomb = 0;
        while (room.chars().filter(ch -> ch == 'B').count() != 3) {
            idxBomb +=  room.length();
            room.setCharAt((idxBomb - 1) % room.length(), 'X');
            room.setCharAt(idxBomb  % room.length(), 'X');
            room.setCharAt((idxBomb + 1)  % room.length(), 'X');
            noBombs++;
            for (int i = 0; i < room.length(); i++) {
                if (room.charAt(i) != room.charAt(((i + 1) % room.length()))) {
                    i +=  room.length();
                    room.setCharAt((i - 1)  % room.length(), 'B');
                    room.setCharAt(i  % room.length(), 'B');
                    room.setCharAt((i + 1)  % room.length(), 'B');
                    i -=  room.length();
                }
                i +=  room.length();
                if (room.charAt(i  % room.length()) == room.charAt((i + 1)  % room.length()) && room.charAt(i  % room.length()) == room.charAt((i - 1)  % room.length()) && room.charAt(i  % room.length()) =='B') {
                    idxBomb = i;
                }
                i -= room.length();
            }

        }
        fio.println(noBombs);
        fio.close();

    }
}
