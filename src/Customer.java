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
    private String accountNumber;
    private String branch;
    private int loanAmount;

    public Customer(String firstName, String lastName, String phoneNumber, String email, String aptNumber, String address, String city, String state, String zipCode, String accountNumber, String branch, int loanAmount) {
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

    public void updateDetails(String firstName, String lastName, String phoneNumber, String email, String aptNumber, String address, String city, String state, String zipCode, String accountNumber, String branch, int loanAmount) {
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
}
