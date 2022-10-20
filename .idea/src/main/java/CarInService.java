import java.util.Objects;

public class CarInService {
    private boolean isInService;
    private String plate;
    private Car car;

    public CarInService() {
        this.isInService = false;
        this.plate = "AAA-yourself42";
        this.car = new Car();
    }

    public CarInService(boolean isInService, String plate, Car car) {
        this.isInService = isInService;
        this.plate = plate;
        this.car = car;
    }

    public boolean isInService() {
        return isInService;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Car getCar() {
        return car;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarInService)) return false;
        CarInService that = (CarInService) o;
        return isInService() == true && isInService() == that.isInService() && getCar().equals(that.getCar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isInService(), getCar());
    }

    @Override
    public String toString() {
        return "CarInService{" +
                "isInService=" + isInService +
                ", plate='" + plate + '\'' +
                ", car=" + car +
                '}';
    }
}