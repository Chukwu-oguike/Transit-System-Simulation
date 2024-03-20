import java.util.*;

public class DeboardEvent implements Event {
    public final Passenger p; public final Train t; public final Station s;
    public DeboardEvent(Passenger p, Train t, Station s) {
        this.p = p; this.t = t; this.s = s;
    }
    public boolean equals(Object o) {
        if (o instanceof DeboardEvent e) {
            return p.equals(e.p) && t.equals(e.t) && s.equals(e.s);
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(p, t, s);
    }
    public String toString() {
        return "Passenger " + p + " deboards " + t + " at " + s;
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
            throw new UnsupportedOperationException("Can't deboard train because no trains exist");
        }else if(!(mbta.getLines().get(this.t).contains(this.s))){
            throw new UnsupportedOperationException("Can't deboard because station does not exist");
        }else if(!(this.s.getPassengerList().contains(this.p))){
            throw new UnsupportedOperationException("Can't deboard because passenger not at Station");
        }else if(this.s.getTrain() != this.t){
            throw new UnsupportedOperationException("Can't board because train not currently at Station");
        }else if(!this.t.getPassengerList().contains(this.p)){
            throw new UnsupportedOperationException("Can't board because passenger not currently in train");
        }else if(mbta.getTrips().get(this.p).contains(this.t)){
            if(mbta.getTrips().get(this.p).indexOf(this.t)>0){
                this.t.removePassenger(this.p);
                this.s.addPassenger(this.p);
            }else {
                throw new UnsupportedOperationException("Can't deboard because station not passenger's stop");
            }

        }
    }
}
