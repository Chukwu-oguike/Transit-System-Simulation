import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class PassThread implements Runnable {

    private Passenger p;
    private List<Station> trips;
    private Train currentT;
    private Train previousT;
    private Integer ithStation;

    private Log log;

    private MBTA mbta;


    //what do we nee to access
    public PassThread(MBTA mbta, Passenger P, Log log) {

        this.p = P;
        this.trips = mbta.getTrips().get(P);
        this.currentT = null;
        this.previousT = null;
        this.ithStation = 0;
        this.log = log;
        this.mbta = mbta;

    }

    public void run() {

        //check to see status of passenger (is he on train or in station)


        // if in station wait for train to stop to board
        while(!this.trips.get(this.trips.size() - 1).getPassengerList().contains(this.p)) {


            Station tempStation = this.trips.get(this.ithStation);
            Train tempTrain = tempStation.getTrain();
            if(this.ithStation < this.trips.size() - 1) {
                if (this.currentT == null && tempTrain != null && mbta.getLines().get(tempTrain).contains(this.trips.get(this.ithStation + 1))) {
                    this.previousT = this.currentT;
                    this.currentT = tempTrain;
                    tempStation.removePassenger(this.p);
                    tempTrain.addPassenger(this.p);
                    this.ithStation = this.ithStation + 1;
                    this.log.passenger_boards(this.p, this.currentT, tempStation);

                }
            } else if (this.currentT != null && tempTrain == this.currentT) {
                this.previousT = this.currentT;
                this.currentT = null;
                tempTrain.removePassenger(this.p);
                tempStation.addPassenger(this.p);
                //this.ithStation = this.ithStation + 1;
                this.log.passenger_deboards(this.p, this.previousT, tempStation);

            }
        }

    }

}
