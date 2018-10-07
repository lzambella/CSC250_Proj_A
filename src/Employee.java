/*
 *  Class for a generic User in the system
 *  Only contains information for logging into the system
 */

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.Serializable;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class Employee implements Serializable {
    private String username;
    private byte[] hashedPassword;
    private byte[] salt = new byte[32];

    /**
     *
     * @param uName Username for the new user
     * @param pWord Password for the new user
     */
    public Employee(String uName, String pWord) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom gen = new SecureRandom();
        gen.nextBytes(salt); // Generate a new salt for the hash

        username = uName;
        hashedPassword = hashFunction(pWord);
    }

    public Employee() throws NoSuchAlgorithmException, InvalidKeySpecException {
        username = "defaultUser";
        hashedPassword = hashFunction("password123");
    }
    /**
     * One way function generator for generating and authenticating passwords
     * @param input input string
     * @return Hashed output
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private byte[] hashFunction(String input) throws NoSuchAlgorithmException, InvalidKeySpecException {
        /*
        Generate a salt to use for our hash
        then hash the plaintext password
         */
        KeySpec s = new PBEKeySpec(input.toCharArray(),salt, 65536, 1024);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHMacSHA1");
        return f.generateSecret(s).getEncoded(); // Store the salted hash as our password
    }

    /**
     * Check the input password against the hash for a particular user
     * @param password Password input
     * @return Whether password matches has
     */
    public boolean authenticate (String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] b = hashFunction(password);
        return Arrays.equals(hashedPassword,b);
    }

    /**
     * Change the users password
     * @param newPWord
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public void updatePassword(String newPWord) throws InvalidKeySpecException, NoSuchAlgorithmException {
        hashedPassword = hashFunction(newPWord);
    }

    public String getUserName() {
        return username;
    }
}
