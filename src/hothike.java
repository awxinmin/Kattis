public class hothike {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int days = fio.nextInt();
        int[] temps = new int[days];
        int max = 50;
        int day = -1;
        for (int i = 0 ; i < days ; i++) {
            temps[i] = fio.nextInt();
        }
        for (int i = 0 ; i < days-2 ; i++) {
            int m = Math.max(temps[i],temps[i+2]);
            if (m < max) {
                max = m;
                day = i+1;
            }
        }
        fio.println(String.format("%d %d",day,max));
        fio.close();
    }
}
