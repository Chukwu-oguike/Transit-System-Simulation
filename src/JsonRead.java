import java.util.HashMap;
import java.util.List;

public class JsonRead {

    public JsonRead(HashMap<String, List<String>> line, HashMap<String, List<String>> trip){
        this.lines = line;
        this.trips = trip;
    }

    public HashMap<String, List<String>> lines;
    public HashMap<String, List<String>> trips;
}
