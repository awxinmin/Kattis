import java.util.PriorityQueue;

public class assigningworkstations {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int noResearches = fio.nextInt();
        int m = fio.nextInt();
        PriorityQueue<AS> allArrivalStay = new PriorityQueue<AS>();
        for (int i = 0 ; i < noResearches ; i++) {
            allArrivalStay.add(new AS(fio.nextInt(),fio.nextInt()));
        }
        int noSaved = 0;
        PriorityQueue<WorkStation> allWorkStations = new PriorityQueue<WorkStation>();
        // create the first workstation
        AS curr = allArrivalStay.poll();
        allWorkStations.add(new WorkStation(curr.getArrival() + curr.getStay()));
        while (!allArrivalStay.isEmpty()) {
            AS currAS = allArrivalStay.poll();
            if (allWorkStations.isEmpty()) {
                allWorkStations.add(new WorkStation(currAS.getArrival() + currAS.getStay()));
                continue;
            }
            WorkStation firstWS = allWorkStations.peek();
            if (currAS.getArrival() < firstWS.getAvailTime()) {
                int nextAvailTime = currAS.getArrival() + currAS.getStay();
                allWorkStations.add(new WorkStation(nextAvailTime));
            }
            else if (currAS.getArrival() <= firstWS.getAvailTime() + m) {
                noSaved++;
                allWorkStations.poll();
                allWorkStations.add(new WorkStation(currAS.getArrival() + currAS.getStay()));

            }
            else {

                allWorkStations.poll();
                allArrivalStay.add(currAS);
            }


            }

        fio.println(noSaved);
        fio.close();

        }

}




class AS implements Comparable<AS> {
    int arrival;
    int stay;

    public AS(int arrival, int stay) {
        this.arrival = arrival;
        this.stay = stay;

    }

    public int getArrival() {
        return this.arrival;
    }

    public int getStay() {
        return this.stay;
    }

    public int compareTo(AS other) {
        return this.getArrival() - other.getArrival();
    }

}

class WorkStation implements Comparable<WorkStation> {
    int availTime;

    public WorkStation(int availTime) {
        this.availTime = availTime;
    }

    public int getAvailTime() {
        return availTime;
    }

    public void setNewAvail(int newAvailTime) {
        this.availTime = newAvailTime;
    }

    public int compareTo(WorkStation other) {
        return this.availTime - other.availTime;
    }
}


