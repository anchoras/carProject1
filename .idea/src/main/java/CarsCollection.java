import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CarsCollection {
    private HashMap<Integer, CarInService> cars;
    private int personnelCarNumber;

    public CarsCollection() {
        this.cars = null;
        this.personnelCarNumber = 0;
        System.out.println("This collection was created empty");
    }

    public CarsCollection(HashMap<Integer, CarInService> cars, int personnelCarNumber) {
        this.cars = cars;
        this.personnelCarNumber = personnelCarNumber;
    }

    public CarsCollection(CarsCollection carsCollection) {
        this.cars = carsCollection.getCars();
        this.personnelCarNumber = carsCollection.getCurrentEndPersonnelCarNumber();
    }


    public HashMap<Integer, CarInService> getCars() {
        return cars;
    }

    public void setCars(HashMap<Integer, CarInService> cars) {
        this.cars = cars;
    }

    public int getCurrentEndPersonnelCarNumber() {
        return personnelCarNumber;
    }

    public int getCurrentOldestPersonnelCarNumber() {
        return this.cars.entrySet().stream()
                .filter(c -> c.getValue().isInService())
                .map(Map.Entry::getKey)
                .min(Comparator.comparing(Integer::valueOf))
                .get();
    }

    public int getCurrentNumberCarInService() {
        Long longCurCarsInService = this.cars.entrySet().stream()
                .filter(c -> c.getValue().isInService())
                .collect(Collectors.counting());
        return Math.toIntExact(longCurCarsInService);
    }

    public int getActivePersonnelCarNumber(CarInService carInService) {
        if (carInService.isInService()) {
            return this.cars.entrySet().stream()
                    .filter(c -> c.getValue().isInService())
                    .filter(c -> c.getValue().equals(carInService))
                    .map(Map.Entry::getKey)
                    .findFirst().get();
        }
        else {
            System.out.println("This car is not currently in service");
            return 0;
        }
    }

    public CarsCollection getActiveCarsInService() {
        Integer carsCount = 0;
        HashMap<Integer, CarInService> activeCars = new HashMap<Integer, CarInService>();
        for (Integer personnelNumber : this.cars.keySet()) {
            if (cars.get(personnelNumber).isInService()) {
                activeCars.put(personnelNumber, cars.get(personnelNumber));
            }
        }   //TODO rewrite to stream way
        /*
        activeCars = this.cars.entrySet().stream()
                .filter(c -> c.getValue().isInService())
                .collect(Collectors.toCollection(Map.Entry::getKey, Map.Entry::getValue, HashMap::new)));
        */
        carsCount = Math.toIntExact(this.cars.entrySet().stream()
                .filter(c -> c.getValue().isInService())
                .collect(Collectors.counting()));
        CarsCollection activeCarsClctn = new CarsCollection(activeCars, carsCount);
        return activeCarsClctn;
    }

    public HashSet<Integer> getOldPersonnelNumbersForTheCar(CarInService carInService) {
        return this.cars.entrySet().stream()
                .filter(Predicate.not(c -> c.getValue().isInService()))
                .filter(c -> c.equals(carInService))
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(HashSet<Integer>::new));
    }

    public void addCar(CarInService carInService) {
        boolean alreadyInService = false;
        for (Map.Entry entry : cars.entrySet()) {
            if (entry.getValue().equals(carInService)) {
                System.out.println("Car is already in service");
                break;
            }
        }
        if (!alreadyInService) { cars.put(++personnelCarNumber, carInService); }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarsCollection)) return false;
        CarsCollection that = (CarsCollection) o;
        return Objects.equals(this.getCars(), that.getCars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCars());
    }

    @Override
    public String toString() {
        if (this.cars==null) {
            return "This collection is empty";
        } else return "CarsCollection{" +
                "cars=" + cars +
                ", personnelCarNumber=" + personnelCarNumber +
                '}';
    }
}