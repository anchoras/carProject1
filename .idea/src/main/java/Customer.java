import java.util.Objects;

public class Customer {
    private String firstName;
    private String secondName;
    private String passportNumber;
    private String callNumber;
    private String email;

    public Customer() {
        this.firstName = "default";
        this.secondName = "default";
        this.passportNumber = "default";
        this.callNumber = "default";
        this.email = "default";
    }

    public Customer(String firstName, String secondName, String passportNumber, String callNumber, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportNumber = passportNumber;
        this.callNumber = callNumber;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName) && secondName.equals(customer.secondName) && passportNumber.equals(customer.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, passportNumber);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", callNumber='" + callNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}