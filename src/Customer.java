/*
 * Customer class for a bank
 * Luke Zambella
 */
public class Customer implements java.io.Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String aptNumber;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAptNumber() {
        return aptNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    private String accountNumber;
    private String branch;
    private double loanAmount;

    public Customer(String firstName, String lastName, String phoneNumber, String email, String aptNumber, String address, String city, String state, String zipCode, String accountNumber, String branch, double loanAmount) {
        this.firstName = firstName.isEmpty() ? "default" : firstName;
        this.lastName = lastName.isEmpty() ? "default" : lastName;
        this.phoneNumber = phoneNumber.isEmpty() ? "000-000-0000" : phoneNumber;
        this.email = email.isEmpty() ? "default_email@default_domain.com" : email;
        this.aptNumber = aptNumber.isEmpty() ? "000" : aptNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.loanAmount = loanAmount;
    }

    public void updateDetails(String firstName, String lastName, String phoneNumber, String email, String aptNumber, String address, String city, String state, String zipCode, String accountNumber, String branch, double loanAmount) {
        this.firstName = firstName.isEmpty() ? "default" : firstName;
        this.lastName = lastName.isEmpty() ? "default" : lastName;
        this.phoneNumber = phoneNumber.isEmpty() ? "000-000-0000" : phoneNumber;
        this.email = email.isEmpty() ? "default_email@default_domain.com" : email;
        this.aptNumber = aptNumber.isEmpty() ? "000" : aptNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.loanAmount = loanAmount;
    }

    /**
     * Returns a string array for datatable in mainWindow
     * @return
     */
    public String[] dataToArray() {
        return new String[] {firstName, lastName, phoneNumber, email, address, aptNumber, city, state, zipCode, accountNumber, branch, String.format("%.2f",loanAmount)};
    }
}
