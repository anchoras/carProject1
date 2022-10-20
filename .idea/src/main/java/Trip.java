import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

public class Trip {
    private LocalDate tripStart;
    private LocalDate tripEnd;
    private double costPerDay;
    private double discount;
    private double totalCost;
    private CarInService usedCar;
    private Customer customer;


    public Trip() {
        this.tripStart = LocalDate.now();
        this.tripEnd = LocalDate.now();
        this.costPerDay = 10.0;
        this.discount = 0;
        this.totalCost = calculateTotalCost();
        this.usedCar = new CarInService();
        this.customer = new Customer();
    }

    public Trip(LocalDate tripStart, LocalDate tripEnd, double costPerDay, double discount, double totalCost, CarInService usedCar, Customer customer) {
        this.tripStart = tripStart;
        this.tripEnd = tripEnd;
        this.costPerDay = costPerDay;
        this.discount = discount;
        this.totalCost = totalCost;
        this.usedCar = usedCar;
        this.customer = customer;
    }


    public LocalDate getTripStart() {
        return tripStart;
    }

    public void setTripStart(LocalDate tripStart) {
        this.tripStart = tripStart;
    }

    public LocalDate getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(LocalDate tripEnd) {
        this.tripEnd = tripEnd;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public CarInService getUsedCar() {
        return usedCar;
    }

    public void setUsedCar(CarInService usedCar) {
        this.usedCar = usedCar;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return Double.compare(trip.costPerDay, costPerDay) == 0 && Double.compare(trip.discount, discount) == 0 && tripStart.equals(trip.tripStart) && tripEnd.equals(trip.tripEnd) && usedCar.equals(trip.usedCar) && customer.equals(trip.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripStart, tripEnd, costPerDay, discount, usedCar, customer);
    }

    private double calculateTotalCost() {
        double totalCost = this.costPerDay * this.discount *
                (Math.abs(Duration.between(this.tripStart.atStartOfDay(), this.tripEnd.atStartOfDay()).toDays()) + 1);
        return totalCost;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripStart=" + tripStart +
                ", tripEnd=" + tripEnd +
                ", costPerDay=" + costPerDay +
                ", discount=" + discount +
                ", totalCost=" + totalCost +
                ", usedCar=" + usedCar +
                ", customer=" + customer +
                '}';
    }
}
