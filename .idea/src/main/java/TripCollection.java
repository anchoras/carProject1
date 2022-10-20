import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TripCollection {
    private HashMap<Integer, Trip> trips;
    private int personnelTripNumber;

    public TripCollection() {
        this.trips = new HashMap<Integer, Trip>();
        this.personnelTripNumber = 0;
        System.out.println("This trips collection was created empty");
    }

    public TripCollection(HashMap<Integer, Trip> trips, int personnelTripNumber) {
        this.trips = trips;
        this.personnelTripNumber = personnelTripNumber;
    }

    public TripCollection(HashMap<Integer, Trip> trips) {
        this.trips = trips;
        this.personnelTripNumber = trips.size();
    }


    public HashMap<Integer, Trip> getTrips() {
        return trips;
    }

    public int getPersonnelTripNumber() {
        return personnelTripNumber;
    }


    public void addTrip(Trip trip) {
        boolean alreadyTripped = false;
        for (Map.Entry entry : trips.entrySet()) {
            if (entry.getValue().equals(trip)) {
                System.out.println("Trip already in list");
                break;
            }
        }
        if(!alreadyTripped) { trips.put(++personnelTripNumber, trip); }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripCollection)) return false;
        TripCollection that = (TripCollection) o;
        return Objects.equals(this.getTrips(), that.getTrips());
    }

    @Override
    public int hashCode() {
        return Objects.hash(trips);
    }

    @Override
    public String toString() {
        return "TripCollection{" +
                "trips=" + trips +
                ", personnelTripNumber=" + personnelTripNumber +
                '}';
    }
}
