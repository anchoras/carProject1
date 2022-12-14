import java.util.Objects;

public class PowerUnit {
    private String type;    //TODO create ENUM?
    private String name;
    private double maxcapacity; //TODO where to store current capacity: in powerunit or in car


    //for default reasons
    public PowerUnit() {
        this.type = "gasolin";
        this.name = "engineDefault";
        this.maxcapacity = 80;
    }

    public PowerUnit(String type, String name, double maxcapacity) {
        this.type = type;
        this.name = name;
        this.maxcapacity = maxcapacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(double maxcapacity) {
        this.maxcapacity = maxcapacity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerUnit)) return false;
        PowerUnit powerUnit = (PowerUnit) o;
        return Double.compare(powerUnit.maxcapacity, maxcapacity) == 0 && type.equals(powerUnit.type) && name.equals(powerUnit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getName(), getMaxcapacity());
    }

    @Override
    public String toString() {
        return "PowerUnit{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", maxcapacity=" + maxcapacity +
                '}';
    }
}