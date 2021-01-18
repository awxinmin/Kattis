import java.util.*;

public class cc2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String[] input = sc.nextLine().split(" ");
        HashMap<List<Integer>,Integer> allPrime = new HashMap<>();
        for (String s : input) {
            int i = Integer.parseInt(s);
            List<Integer> prime = primeFactors(i);
            allPrime.put(prime,i);
        }

        TreeMap<List<Integer>,Integer> sorted = new TreeMap<>();
        sorted.putAll(allPrime);
        for (Map.Entry<List<Integer>,Integer>entry : sorted.entrySet()) {  // Itrate through hashmap
            System.out.println(entry.getValue());
            }


    }

    public static List<Integer> primeFactors(int n) {
        List<Integer> prime = new MyList();
        while (n%2==0) {
            prime.add(2);
            n /= 2;
        }

        // n must be odd at this point.  So we can
        // skip one element (Note i = i +2)
        for (int i = 3; i <= Math.sqrt(n); i+= 2) {
            // While i divides n, print i and divide n
            while (n%i == 0) {
                prime.add(i);
                n /= i;
            }
        }

        // This condition is to handle the case whien
        // n is a prime number greater than 2
        if (n > 2) {
            prime.add(n);
        }
        return prime;
    }

}

class MyList extends ArrayList<Integer> implements Comparable<MyList> {

    @Override
    public int compareTo(MyList integers) {
        int n = Math.min(this.size(), integers.size());
        for (int i = 0; i < n; i++) {
            if (this.get(i) != integers.get(i)) {
                return this.get(i).compareTo(integers.get(i));
            }
        }
        if (this.size() == integers.size()) return 0;
        if (this.size() == n) return -1;
        else return 1;
    }
}
