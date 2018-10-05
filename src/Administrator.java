public class Administrator extends Manager {
    public Administrator(String uName, String pWord) throws Exception{
        super(uName, pWord);
    }

    /**
     * Creates a new customer based on all the data
     * @param firstName
     * @param lastName
     * @param phoneNumber
     * @param email
     * @param aptNumber
     * @param address
     * @param city
     * @param state
     * @param zipCode
     * @param accountNumber
     * @param branch
     * @param loanAmount
     * @return
     */
    public Customer createNewCustomer(String firstName, String lastName, String phoneNumber, String email, String aptNumber, String address, String city, String state, String zipCode, String accountNumber, String branch, int loanAmount) {
        return new Customer(firstName, lastName, phoneNumber,email,aptNumber,address,city,state,zipCode,accountNumber,branch,loanAmount);
    }
}
