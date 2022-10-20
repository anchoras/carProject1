import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomerCollection {
    private HashMap<Integer, Customer> customers;
    private int personnelCustomerNumber;

    public CustomerCollection() {
        this.customers = new HashMap<Integer, Customer>();
        this.personnelCustomerNumber = 0;
        System.out.println("This customers collection was created empty");
    }

    public CustomerCollection(HashMap<Integer, Customer> customers, int personnelCustomerNumber) {
        this.customers = customers;
        this.personnelCustomerNumber = personnelCustomerNumber;
    }

    public CustomerCollection(HashMap<Integer, Customer> customers) {
        this.customers = customers;
        this.personnelCustomerNumber = customers.size();
    }


    public HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    public int getPersonnelCustomerNumber() {
        return personnelCustomerNumber;
    }


    public void addCustomer(Customer customer) {
        boolean alreadyInList = false;
        for (Map.Entry entry : customers.entrySet()) {
            if (entry.getValue().equals(customer)) {
                System.out.println("Customer is already in list of our customers");
                break;
            }
        }
        if (!alreadyInList) { customers.put(++personnelCustomerNumber, customer); }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerCollection)) return false;
        CustomerCollection that = (CustomerCollection) o;
        return Objects.equals(this.getCustomers(), that.getCustomers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomers());
    }

    @Override
    public String toString() {
        return "CustomerCollection{" +
                "customers=" + customers +
                ", personnelCustomerNumber=" + personnelCustomerNumber +
                '}';
    }
}
