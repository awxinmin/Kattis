public class babybites {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int bites = fio.nextInt();
        String[] words = fio.nextLine().split(" ");
        int i = 1;
        for (int j = 0 ; j <bites ; j++) {
            if (!words[j].equals("mumble") && Integer.parseInt(words[j]) != j+1) {
                i = 0;
            }
        }
        String s = i == 1 ? "makes sense" : "something is fishy";
        fio.println(s);

        fio.close();
    }
}
