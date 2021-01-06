import java.util.TreeSet;

public class kattisquest {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int noCases = fio.nextInt();
        TreeSet<EnergyGold> bst = new TreeSet<>();
        for (int i = 0 ; i < noCases ; i++) {
            String command = fio.next();

            if (command.equals("add")) {
                long energy = fio.nextLong();
                long gold = fio.nextLong();
                EnergyGold curr = new EnergyGold(energy,gold);
                bst.add(curr);
            } else {
                long energy = fio.nextLong();
                EnergyGold curr = new EnergyGold(energy,10000000);
                long cumGold = 0;
                while (curr.energy > 0) {
                        EnergyGold pred = bst.floor(curr);
                        // no pred
                        if (pred == null) {
                            break;
                        } else {
                            curr.energy -= pred.energy;
                            cumGold += pred.gold;
                            bst.remove(pred);
                            }


                        }
                fio.println(cumGold);

                    }

            }



        fio.close();
    }
}



class EnergyGold implements Comparable<EnergyGold>{
    static long sID;
    long energy;
    long gold;
    long id;

    public EnergyGold(long energy , long gold) {
        this.energy = energy;
        this.gold = gold;
        sID++;
        this.id = sID;
    }


    public int compareTo(EnergyGold other) {
        long diffE = this.energy - other.energy;
        if (diffE < 0 ){
            return -1;
        } else if (diffE > 0){
            return 1;
        } else {
            long diffG = this.gold - other.gold;
            if (diffG < 0 ){
                return -1;
            } else if (diffG > 0){
                return 1;
            } else {
                long diffID = this.id - other.id;
                if (diffID < 0 ){
                    return -1;
                } else if (diffID > 0){
                    return 1;
                } else {
                    return 0;
                }
            }

        }

        }


}
