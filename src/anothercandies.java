public class anothercandies {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int t = fio.nextInt();
        for (int i = 0 ; i < t ; i++) {
            int n = fio.nextInt();
            int sum = 0;
            for (int j = 0 ; j < n ; j++) {
                sum += fio.nextInt();
            }
            if (sum % n == 0) {
                fio.println("YES");
            } else {
                fio.println("NO");
            }
        }
        fio.close();
    }
}
