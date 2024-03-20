import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import com.google.gson.*;




public class MBTA {

    private HashMap<HashMap<String, List<String>>, HashMap<String, List<String>>> gsonRead;

    private HashMap<Train, List<Station>> lines = new HashMap<>();
    private HashMap<Passenger, List<Station>> trips = new HashMap<>();

    private Boolean startState = false;
    private Boolean endState = false;

    // Creates an initially empty simulation
    public MBTA() { }

    // Adds a new transit line with given name and stations
    public void addLine(String name, List<String> stations) {

        ArrayList<Station> l1 = new ArrayList<Station>();

        for(String s : stations){
            l1.add(Station.make(s));
        }

        lines.put(Train.make(name), l1);
    }

    // Adds a new planned journey to the simulation
    public void addJourney(String name, List<String> stations) {

        ArrayList<Station> l1 = new ArrayList<Station>();

        for(String s : stations){
            l1.add(Station.make(s));
        }

        trips.put(Passenger.make(name), l1);
    }

    // Return normally if initial simulation conditions are satisfied, otherwise
    // raises an exception
    public void checkStart() {

        this.startState = true;

        // you may want to check if lines and trips are empty
        if(lines.isEmpty() || trips.isEmpty())
            this.startState = false;


        for (Map.Entry<Train, List<Station>> entry : lines.entrySet()) {
            if (!(entry.getKey() == entry.getValue().get(0).getTrain()))
                this.startState = false;
        }

        for (Map.Entry<Passenger, List<Station>> entry : trips.entrySet()) {
            if(!(entry.getValue().get(0).getPassengerList().contains(entry.getKey())))
                this.startState = false;
        }

    }

    // Return normally if final simulation conditions are satisfied, otherwise
    // raises an exception
    public void checkEnd() {

        this.endState = true;

        for (Map.Entry<Passenger, List<Station>> entry : trips.entrySet()) {
            if(!(entry.getValue().get(entry.getValue().size() - 1).getPassengerList().contains(entry.getKey())))
                this.endState = false;
        }
    }

    // reset to an empty simulation
    public void reset() {
        lines.clear();
        trips.clear();
    }

    // adds simulation configuration from a file
    public void loadConfig(String filename) {

        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            Reader reader = new BufferedReader(new FileReader(filename));

            // convert JSON file to map
            JsonRead map = gson.fromJson(reader, JsonRead.class);

            for (Map.Entry<String, List<String>> entry : map.lines.entrySet()) {
                this.addLine(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<String, List<String>> entry : map.trips.entrySet()) {
                this.addJourney(entry.getKey(), entry.getValue());
            }

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void init_sim(){
        for (Map.Entry<Train, List<Station>> entry : this.lines.entrySet()) {
            entry.getValue().get(0).addTrain(entry.getKey());
        }

        for (Map.Entry<Passenger, List<Station>> entry : this.trips.entrySet()) {
            entry.getValue().get(0).addPassenger(entry.getKey());
        }
    }


    public HashMap<Train, List<Station>> getLines(){
        return this.lines;
    }

    public HashMap<Passenger, List<Station>> getTrips(){
        return this.trips;
    }

    public Boolean getStartState(){
        return startState;
    }

    public Boolean getEndState() {
        return endState;
    }
}
