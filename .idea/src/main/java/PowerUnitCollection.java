import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PowerUnitCollection {
    private HashMap<Integer, PowerUnit> punits;
    private int personnelPUNumber;

    public PowerUnitCollection() {
        this.punits = new HashMap<Integer, PowerUnit>();
        this.personnelPUNumber = 0;
        System.out.println("This collection was created empty");
    }

    public PowerUnitCollection(HashMap<Integer, PowerUnit> punits, int number){
        this.punits = punits;
        this.personnelPUNumber = number;
    }

    public PowerUnitCollection(HashMap<Integer, PowerUnit> punits) {
        this.punits = punits;
        this.personnelPUNumber = punits.size();
    }


    public HashMap<Integer, PowerUnit> getPunits() {
        return punits;
    }

    public int getPersonnelPUNumber() {
        return personnelPUNumber;
    }


    public void addPUnit(PowerUnit punit) {
        boolean alreadyExists = false;
        for (Map.Entry entry : punits.entrySet()) {
            if (entry.getValue().equals(punit)) {
                System.out.println("Power unit already in list");
                break;
            }
        }
        if(!alreadyExists) { punits.put(++personnelPUNumber, punit); }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerUnitCollection)) return false;
        PowerUnitCollection that = (PowerUnitCollection) o;
        return punits.equals(that.punits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punits);
    }

    @Override
    public String toString() {
        return "PowerUnitCollection{" +
                "punits=" + punits +
                ", personnelPUNumber=" + personnelPUNumber +
                '}';
    }
}
