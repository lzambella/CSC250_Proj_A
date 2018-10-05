import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean emergencyMode = false;

    private static List<Customer> customers = new ArrayList<Customer>();
    private List<Employee> employees = new ArrayList<Employee>();

    /**
     * The current user logged into the system
     */
    private static Employee authenticatedUser;

    private String emergencyUserName = "root";
    private String energencyPassword = "toor";

    public static void main(String[] args) {
        // Load data into a local database


        // Begin user log in loop
        while (authenticatedUser == null) {

        }
    }
}


