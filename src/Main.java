import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    //TODO: Have the submit button show the main window
    //TODO: Set main windows minimum size
    //TODO: Test that everything works
    //TODO: add edit employee details gui
    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static ArrayList<Employee> employees = new ArrayList<Employee>();

    /**
     * The current user logged into the system
     */
    public static Employee authenticatedUser;

    // to bootstrap administrator accounts
    private static String emergencyUserName = "root";
    private static String emergencyPassword = "toor";

    private static boolean debugMode = false;
    enum accountType {employee, manager, administrator};
    /**
     * Display user authentication first before anything
     */
    private static void initAuthentication() {
        JFrame frame = new JFrame("Login");
        frame.setMinimumSize(new Dimension(300,100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField uName = new JTextField();
        JPasswordField pWord = new JPasswordField();
        JButton submit = new JButton("Submit");

        JLabel label1 = new JLabel("Username:");
        JLabel label2 = new JLabel("Password:");

        submit.addActionListener(e -> {
            // If the built in account has been used
            if (uName.getText().equalsIgnoreCase(emergencyUserName) && Arrays.equals(emergencyPassword.toCharArray(), pWord.getPassword())) {
                System.out.println("Emergency mode enabled");
                bootstrap();
            } else {
                try {
                    Employee temp = employees.stream().filter(x -> x.getUserName().equalsIgnoreCase(uName.getText())).findFirst().get();
                    String pass = Arrays.toString(pWord.getPassword());
                    boolean b = temp.authenticate(pass);
                    if (b) {
                        authenticatedUser = temp;
                        System.out.printf("Successfully logged in as %s", temp.getUserName());
                        MainWindow gui = new MainWindow();
                        frame.setVisible(false);

                    } else System.out.println("Wrong username/password");
                } catch (Exception z) {
                    System.err.println(z);
                }
            }
        });

        frame.setLayout(new GridLayout(3,2));
        frame.add(label1);
        frame.add(uName);

        frame.add(label2);
        frame.add(pWord);

        frame.add(submit);
        frame.add(new JSeparator(SwingConstants.VERTICAL));
        frame.setVisible(true);
    }

    /**
    First time logging on with the top level credentials, force user to make a new account
     */
    private static void bootstrap() {
        JFrame frame = new JFrame("Create new account");
        frame.setLayout(new GridLayout(5,2));

        frame.setMinimumSize(new Dimension(300,100));

        JTextField field1 = new JTextField();
        JPasswordField pWord = new JPasswordField();
        JPasswordField pWordConfirm = new JPasswordField();

        JComboBox accTyp = new JComboBox<>(accountType.values());

        JLabel label1 = new JLabel("Username:");
        JLabel label2 = new JLabel("Password;");
        JLabel label3 = new JLabel("Confirm:");

        JButton button = new JButton("Submit");

        button.addActionListener(e -> {
            // If password match then create and save the new account
            try {
                if (Arrays.equals(pWord.getPassword(), pWordConfirm.getPassword())) {
                    Employee temp;
                    switch (accTyp.getSelectedIndex()) { // hack because combobox selection cant be set to enum
                        case 2: // Administrator
                            temp = new Administrator(field1.getText(), Arrays.toString(pWord.getPassword()));
                            break;
                        case 1: // manager
                            temp = new Manager(field1.getText(), Arrays.toString(pWord.getPassword()));
                            break;
                        case 0: // employee
                            temp = new Employee(field1.getText(), Arrays.toString(pWord.getPassword()));
                            break;
                        default:
                            temp = new Employee("error", "password");
                            break;
                    }

                    // Check if the user already exists
                    if (employees.stream().anyMatch(p -> p.getUserName().equals(temp.getUserName()))) {
                        System.out.println("User already exists, ignoring");
                        frame.setVisible(false);
                        return;
                    }
                    // Add the employee and save to file
                    employees.add(temp);
                    saveData();
                    // Close the frame but display a message
                    JOptionPane.showMessageDialog(frame, String.format("User \"%s\" created, returning to login.", temp.getUserName()));
                    frame.setVisible(false);
                }
            } catch (Exception z) {
                System.out.println(z);
            }
        });

        frame.add(label1);
        frame.add(field1);

        frame.add(label2);
        frame.add(pWord);

        frame.add(label3);
        frame.add(pWordConfirm);

        frame.add(accTyp);
        frame.add(button);

        frame.setVisible(true);
    }
    /**
     * Reads employee and customer data into an array
     */
    private static void loadData() {
        try {
            FileInputStream stream = new FileInputStream("UserData.dat");
            ObjectInputStream i = new ObjectInputStream(stream);

            employees = (ArrayList<Employee>) i.readObject();
            i.close();
            stream.close();

            stream = new FileInputStream("CustomerData.dat");
            i = new ObjectInputStream(stream);

            customers = (ArrayList<Customer>) i.readObject();

            i.close();
            stream.close();

        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
        }
    }

    /**
     * save the user and employee data into the database
     */
    public static void saveData() {
        try {
            FileOutputStream stream = new FileOutputStream("UserData.dat");
            ObjectOutputStream i = new ObjectOutputStream(stream);

            i.writeObject(employees);
            i.close();
            stream.close();

            stream = new FileOutputStream("CustomerData.dat");
            i = new ObjectOutputStream(stream);

            i.writeObject(customers);

            i.close();
            stream.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        // Load data into a local database
        loadData();

        // Initialize authentication dialog
        initAuthentication();
    }
}


