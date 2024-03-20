import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;


public class Station extends Entity {

    private static HashMap<String, Station> map = new HashMap<>();
    private Station(String name) { super(name); }

    private Lock l = new ReentrantLock();

    private Condition c = l.newCondition();

    private List<Passenger> passengers = new LinkedList<>(); // passengers are at stations until deboard event

    private Train trains = null; // trains are at station until move event

    public static Station make(String name) {
        // Change this method!
        if(map.containsKey(name)){
            return map.get(name);
        }else{
            map.put(name, new Station(name));
            return map.get(name);
        }
    }

    public void addTrain(Train T){

        this.trains = T;

    }

    public void addPassenger(Passenger P){
        passengers.add(P);
    }

    public void removeTrain(){
        this.trains = null;
    }

    public void removePassenger(Passenger P){
        passengers.remove(P);
    }

    public Train getTrain(){
        return trains;
    }

    public List<Passenger> getPassengerList(){
        return passengers;
    }

    public Lock getLock(){
        return l;
    }

    public Condition getC() {
        return c;
    }
}
