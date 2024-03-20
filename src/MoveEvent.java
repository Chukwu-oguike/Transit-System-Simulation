import java.util.*;
import java.lang.*;

public class MoveEvent implements Event {
    public final Train t; public final Station s1, s2;
    public MoveEvent(Train t, Station s1, Station s2) {
        this.t = t; this.s1 = s1; this.s2 = s2;
    }
    public boolean equals(Object o) {
        if (o instanceof MoveEvent e) {
            return t.equals(e.t) && s1.equals(e.s1) && s2.equals(e.s2);
        }
        return false;
    }
    public int hashCode() {
        return Objects.hash(t, s1, s2);
    }
    public String toString() {
        return "Train " + t + " moves from " + s1 + " to " + s2;
    }
    public List<String> toStringList() {
        return List.of(t.toString(), s1.toString(), s2.toString());
    }
    public void replayAndCheck(MBTA mbta) {

        if(mbta.getLines().get(this.t) == null){
            throw new UnsupportedOperationException("Can't move train because no trains exist");
        }else if(!(mbta.getLines().get(this.t).contains(this.s1) && mbta.getLines().get(this.t).contains(s2))) {
            throw new UnsupportedOperationException("Train stations in different lines");
        }else if( Math.abs(mbta.getLines().get(this.t).indexOf(this.s1) - mbta.getLines().get(this.t).indexOf(this.s2)) > 1){
            throw new UnsupportedOperationException("Train cannot move more than one station at a time");
        }else{
            this.s2.addTrain(this.t);
            this.s1.removeTrain();
        }
        //check for moving across train lines
        // check for one stop move
        // check to see if there are other trains currently at the station 2
        // check if train is at station 1


    }
}
