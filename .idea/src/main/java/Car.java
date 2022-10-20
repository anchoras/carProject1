import java.util.Objects;

public class Car {
    private PowerUnit powerUnit;
    private String model;
    private int year;
    private double price;
    private String vin;

    public Car() {
        this.powerUnit = new PowerUnit();
        this.model = "djiguli";
        this.year = 1990;
        this.price = 9.99;
        this.vin = "0123456789ABCDEFG";
    }

    public Car(PowerUnit powerUnit, String model, int year, double price, String vin) {
        this.powerUnit = powerUnit;
        this.model = model;
        this.year = year;
        this.price = price;
        this.vin = vin;
    }


    public PowerUnit getPowerUnit() {
        return powerUnit;
    }

    public void setPowerUnit(PowerUnit powerUnit) {
        this.powerUnit = powerUnit;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String id) {
        this.vin = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getVin().equals(car.getVin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVin());
    }

    @Override
    public String toString() {
        return "Car{" +
                "powerUnit=" + powerUnit +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", vin='" + '\'' + vin +
                '}';
    }
}