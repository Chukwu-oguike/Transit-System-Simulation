import java.util.*;

public class BoardEvent implements Event {
    public final Passenger p; public final Train t; public final Station s;
    public BoardEvent(Passenger p, Train t, Station s) {
        this.p = p; this.t = t; this.s = s;
    }
    public boolean equals(Object o) {
        if (o instanceof BoardEvent e) {
            return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(p, t, s);
    }
    public String toString() {
        return "Passenger " + p + " boards " + t + " at " + s;
    }
    public List<String> toStringList() {
        return List.of(p.toString(), t.toString(), s.toString());
    }
    public void replayAndCheck(MBTA mbta) {

        //check train exist
        //check passenger exists
        //check passenger at station
        //check train at station

        if(mbta.getLines().get(this.t) == null){
            throw new UnsupportedOperationException("Can't board train because no trains exist");
        }else if(!(mbta.getLines().get(this.t).contains(this.s))){
            throw new UnsupportedOperationException("Can't board because station does not exist");
        }else{
            this.t.addPassenger(this.p);
            this.s.removePassenger(this.p);
        }
    }
}
