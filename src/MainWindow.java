/*
Main window of application that contains customer data and option to add more data
Emloyees can update user data while only administrators can add data
 */

import com.sun.jdi.InvalidTypeException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private JTable dataTable;
    private String[] columnNames = {"First name", "Last name", "Phone", "Email", "Address", "Apartment", "City", "State", "Zipcode", "Account Number", "Branch", "Loan amount"};

    public MainWindow() {
        super("System");
        setLayout(new GridLayout(2,1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2)); // have two buttons at the bottom

        dataTable = new JTable(getData(Main.customers), columnNames);

        JButton createNewCustomer = new JButton("New Customer");
        JButton editCustomer = new JButton("Edit customer");

        buttonPanel.add(editCustomer);
        buttonPanel.add(createNewCustomer);

        add(dataTable);
        add(buttonPanel);
        // disable creating a new user if the logged in user isnt an administrator
        if (!(Main.authenticatedUser instanceof Administrator)) {
            createNewCustomer.setVisible(false);
        }

        if (!(Main.authenticatedUser instanceof Manager)) {
            editCustomer.setVisible(false);
        }
        // button to create new customer
        createNewCustomer.addActionListener(e -> {
            addCustomerWindow();
        });

        setVisible(true);


    }

    /**
     * Creates a window that allows the admin to add a new customer
     */
    private void addCustomerWindow() {
        JDialog dialog = new JDialog(this, true);
        JTextField[] fields = new JTextField[12];
        JLabel[] labels = {new JLabel("First name"), new JLabel("Last name"), new JLabel("Phone"), new JLabel("email"), new JLabel("Apartment"), new JLabel("Address"), new JLabel("City"), new JLabel("State"), new JLabel("Zipcode"), new JLabel("Account numb"), new JLabel("Branch"), new JLabel("Loan amnt")};
        dialog.setLayout(new GridLayout(13, 2));
        for (int i = 0; i < 12; i++) {
            dialog.add(labels[i]);
            dialog.add(fields[i]);
        }

        JButton submit = new JButton("submit");

        dialog.add(submit);

        // give the submit button an action
        submit.addActionListener(e -> {
            try {
                Customer temp = new Customer(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(), fields[8].getText(), fields[9].getText(), fields[10].getText(), Integer.parseInt(fields[11].getText()));
                dialog.setVisible(false);
                Main.customers.add(temp);

                // save the data to a file
                Main.saveData();
                // Update the main table (hack)
                dataTable = new JTable(getData(Main.customers), columnNames);

            } catch (Exception x) {
                System.out.println(x);
            }
            });


    }

    /**
     * Creates a window that allows the manager to edit a new customer
     */
    private void editCustomerWindow() {
        JDialog dialog = new JDialog(this, true);
        JTextField[] fields = new JTextField[12];
        JLabel[] labels = {new JLabel("First name"), new JLabel("Last name"), new JLabel("Phone"), new JLabel("email"), new JLabel("Apartment"), new JLabel("Address"), new JLabel("City"), new JLabel("State"), new JLabel("Zipcode"), new JLabel("Account numb"), new JLabel("Branch"), new JLabel("Loan amnt")};
        dialog.setLayout(new GridLayout(13, 2));
        Customer selectedCust = Main.customers.get(dataTable.getY() - 1);
        String[] s = selectedCust.dataToArray();

        for (int i = 0; i < 12; i++) {
            fields[i].setText(s[i]);
            dialog.add(labels[i]);
            dialog.add(fields[i]);
        }
        JButton submit = new JButton("submit");

        dialog.add(submit);

        // give the submit button an action
        submit.addActionListener(e -> {
            try {
                selectedCust.updateDetails(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(), fields[8].getText(), fields[9].getText(), fields[10].getText(), Integer.parseInt(fields[11].getText()));
                dialog.setVisible(false);

                // save the data to a file
                Main.saveData();
                // Update the main table (hack)
                dataTable = new JTable(getData(Main.customers), columnNames);

            } catch (Exception x) {
                System.out.println(x);
            }
        });


    }
    private String[][] getData(ArrayList<Customer> customerList) {
        int dataAmount = customerList.size(); // Get number of rows datatable will have
        String[][] data = new String[dataAmount][12];

        for (int i = 0; i < dataAmount; i++) {
            data[i] = customerList.get(i).dataToArray();
        }
        return data;
    }
}
