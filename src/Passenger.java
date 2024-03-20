import java.util.HashMap;

public class Passenger extends Entity {

    private static HashMap<String, Passenger> map = new HashMap<>();

    //private Station iten;
    private Passenger(String name) { super(name); }

    public static Passenger make(String name) {
        // Change this method!
        if(map.containsKey(name)){
            return map.get(name);
        }else{
            map.put(name, new Passenger(name));
            return map.get(name);
        }
    }
}
