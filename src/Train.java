import java.util.*;

public class Train extends Entity {

    private static HashMap<String, Train> map = new HashMap<>();
    private List<Passenger> commuters = new LinkedList<>();
    private Train(String name) { super(name); }

    public static Train make(String name) {
        // Change this method!
        if(map.containsKey(name)){
            return map.get(name);
        }else{
            map.put(name, new Train(name));
            return map.get(name);
        }
    }

    public void addPassenger(Passenger P){
        commuters.add(P);
    }

    public List<Passenger> getPassengerList(){
        return commuters;
    }

    public void removePassenger(Passenger P){
        commuters.remove(P);
    }
}
