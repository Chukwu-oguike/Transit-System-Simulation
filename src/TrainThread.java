import java.util.HashMap;
import java.util.List;

public class TrainThread implements Runnable {

    private Integer ithStation;

    private Boolean direction;

    private Train T;

    private List<Station> stops;

    private Log log;
    private MBTA mbta;

    public TrainThread(MBTA mbta, Train T, Log log) {

        this.T = T;
        this.stops = mbta.getLines().get(T);
        this.ithStation = 0;
        this.log = log;
        this.direction = false;
        this.mbta = mbta;


    }

    public void run() {

       //move from one station to another

        //check to see if all passengers are at their destinations && this.stops.get(this.ithStation + 1) == null)

        // if you are at the end of the line, start moving in the opposite direction
        while(mbta.getEndState() != true) {
            //System.out.println(this.T + " "+this.stops.get(this.ithStation));
            if (stops.get(stops.size() - 1).getTrain() == this.T) {
                this.direction = true;
            }

            if (stops.get(0).getTrain() == this.T) {
                this.direction = false;
            }

            if (this.direction == false) {

                this.stops.get(this.ithStation + 1).getLock().lock();
                while(this.stops.get(this.ithStation + 1).getTrain() != null){
                    try {
                        this.stops.get(this.ithStation + 1).getC().await();
                    } catch(Exception e){}
                }
                this.log.train_moves(this.T, this.stops.get(this.ithStation), this.stops.get(this.ithStation + 1));

                this.stops.get(this.ithStation + 1).addTrain(this.T);
                this.stops.get(this.ithStation).removeTrain();
                this.ithStation = this.ithStation + 1;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                this.stops.get(this.ithStation).getC().signalAll();
                this.stops.get(this.ithStation).getLock().unlock();


            } else if (this.direction == true) {

                this.stops.get(this.ithStation - 1).getLock().lock();
                while(this.stops.get(this.ithStation - 1).getTrain() != null){
                    try {
                        this.stops.get(this.ithStation - 1).getC().await();
                    } catch(Exception e){}
                }

                this.log.train_moves(this.T, this.stops.get(this.ithStation), this.stops.get(this.ithStation - 1));

                this.stops.get(this.ithStation - 1).addTrain(this.T);
                this.stops.get(this.ithStation).removeTrain();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
                this.ithStation = this.ithStation - 1;

                this.stops.get(this.ithStation).getC().signalAll();
                this.stops.get(this.ithStation).getLock().unlock();

            }

            mbta.checkEnd();
        }


    }
}
