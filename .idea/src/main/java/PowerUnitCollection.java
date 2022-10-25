import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PowerUnitCollection {
    private HashMap<Integer, PowerUnit> punits;
    private int id;
    private PowerUnitDAO puDAO = new PowerUnitDAO();

    public PowerUnitCollection() {
        this.punits = new HashMap<Integer, PowerUnit>();
        this.id = 0;
        System.out.println("This collection was created empty");
    }

    public PowerUnitCollection(HashMap<Integer, PowerUnit> punits, int personnelPUNumber){
        this.punits = punits;     //TODO deep copy
        this.id = personnelPUNumber;
    }

    public PowerUnitCollection(HashMap<Integer, PowerUnit> punits) {
        this.punits = punits;      //TODO deep copy
        this.id = punits.size();
    }


    public HashMap<Integer, PowerUnit> getPunits() {
        refreshFromDB();
        HashMap<Integer, PowerUnit> punitsCopy = new HashMap<Integer, PowerUnit>(punits);
        return punitsCopy;
    }

    public int getIdNumber() {
        return id;
    }


    public void refreshFromDB() {
        try {
            this.punits = this.puDAO.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void add(PowerUnit punit) {
        try {
            refreshFromDB();
            if(!isExisting(punit)) {
                punits.put(++id, punit);
                puDAO.add(punit);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void delete(PowerUnit punit) {
        try {
            refreshFromDB();
            if(isExisting(punit)) {
                punits.entrySet().
                        removeIf(entry -> punit.equals(entry.getValue()));
                puDAO.delete(punit);
            } else {
                System.out.println("There is no such element for deleting...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void delete(int id) {
        try {
            refreshFromDB();
            if(punits.containsKey(id)) {
                puDAO.delete(punits.get(id));
                punits.remove(id);
            } else {
                System.out.println("There is no such element for deleting...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void update(int id, PowerUnit newPunit) {
        try {
            refreshFromDB();
            if(punits.containsKey(id)) {
                puDAO.update(id, newPunit);
            } else {
                System.out.println("There is no such element for updating...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    private boolean isExisting(PowerUnit punit) {
        boolean alreadyExists = false;
        for (Map.Entry entry : punits.entrySet()) {
            if (entry.getValue().equals(punit)) {
                alreadyExists = true;
                break;
            }
        }
        return alreadyExists;
    }

    public PowerUnit getById(int id) throws SQLException {
        refreshFromDB();
        return punits.get(id);
    }

    public void showAll() {
        refreshFromDB();
        System.out.println(punits.toString());
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
                "id=" + id +
                ", punits=" + punits +
                '}';
    }
}
