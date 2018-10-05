/**
 * Class blueprint for a manager object
 * A manager is a user with administrative functionality
 */

import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class Manager extends Employee {

    // A manager is the same as a user
    public Manager(String uName, String pWord) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(uName, pWord);
    }

    public Manager() throws Exception {

    }


    /**
     * instantiate and returns a new User class
     * @param uName
     * @param pWord
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public Employee createNewUser(String uName, String pWord) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new Employee(uName, pWord);
    }

    /**
     * Update a user's password
     * @param u
     * @param pWord
     * @throws Exception
     */
    public void changeUserPassword(Employee u, String pWord) throws Exception {
        u.updatePassword(pWord);
    }
}
