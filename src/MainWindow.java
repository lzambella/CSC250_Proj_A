/*
Main window of application that contains customer data and option to add more data
Emloyees can update user data while only administrators can add data
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {
    private JTable dataTable;
    private String[] columnNames = {"First name", "Last name", "Phone", "Email", "Address", "Apartment", "City", "State", "Zipcode", "Account Number", "Branch", "Loan amount"};
    private DefaultTableModel model;

    public MainWindow() {
        super();
        if (Main.authenticatedUser instanceof Administrator) {
            setTitle("Logged on as Administrator");
        } else if (Main.authenticatedUser instanceof Manager) {
            setTitle("Logged on as Manager");
        } else if (Main.authenticatedUser != null) {
            setTitle("Logged on as Employee");
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600,400));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2)); // have two buttons at the bottom

        dataTable = new JTable(new DefaultTableModel(getData(Main.customers), columnNames));
        dataTable.removeEditor();
        model = (DefaultTableModel)  dataTable.getModel(); // tabel model for adding new rows dynamically

        JButton createNewCustomer = new JButton("New Customer");
        JButton editCustomer = new JButton("Edit customer");

        buttonPanel.add(editCustomer);
        buttonPanel.add(createNewCustomer);

        add(dataTable.getTableHeader(), BorderLayout.PAGE_START);
        add(dataTable, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
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
        // button to edit existing customer
        editCustomer.addActionListener(e -> editCustomerWindow());

        setVisible(true);


    }

    /**
     * Creates a window that allows the admin to add a new customer
     */
    private void addCustomerWindow() {
        JDialog dialog = new JDialog(this, true);
        dialog.setMinimumSize(new Dimension(600,400));
        JTextField[] fields = new JTextField[12];
        JLabel[] labels = {new JLabel("First name"), new JLabel("Last name"), new JLabel("Phone"), new JLabel("email"), new JLabel("Apartment"), new JLabel("Address"), new JLabel("City"), new JLabel("State"), new JLabel("Zipcode"), new JLabel("Account numb"), new JLabel("Branch"), new JLabel("Loan amnt")};
        dialog.setLayout(new GridLayout(13, 2));
        for (int i = 0; i < 12; i++) {
            dialog.add(labels[i]);
            fields[i] = new JTextField();
            dialog.add(fields[i]);
        }

        JButton submit = new JButton("submit");

        dialog.add(submit);

        // give the submit button an action
        submit.addActionListener(e -> {
            try {
                Customer temp = new Customer(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(), fields[8].getText(), fields[9].getText(), fields[10].getText(), Double.parseDouble(fields[11].getText()));
                dialog.setVisible(false);
                Main.customers.add(temp);

                // save the data to a file
                Main.saveData();
                // Update the main table (hack)
                model.addRow(temp.dataToArray());

            } catch (Exception x) {
                System.out.println(x);
            }
            });
            dialog.setVisible(true);

    }

    /**
     * Creates a window that allows the manager to edit a new customer
     */
    private void editCustomerWindow() {
        JDialog dialog = new JDialog(this, true);
        dialog.setMinimumSize(new Dimension(600,400));
        JTextField[] fields = new JTextField[12];
        JLabel[] labels = {new JLabel("First name"), new JLabel("Last name"), new JLabel("Phone"), new JLabel("email"), new JLabel("Apartment"), new JLabel("Address"), new JLabel("City"), new JLabel("State"), new JLabel("Zipcode"), new JLabel("Account numb"), new JLabel("Branch"), new JLabel("Loan amnt")};
        dialog.setLayout(new GridLayout(13, 2));
        Customer selectedCust = Main.customers.get(dataTable.getSelectedRow());
        String[] s = selectedCust.dataToArray();

        for (int i = 0; i < 12; i++) {
            fields[i] = new JTextField();
            fields[i].setText(s[i]);
            dialog.add(labels[i]);
            dialog.add(fields[i]);
        }
        JButton submit = new JButton("submit");

        dialog.add(submit);

        // give the submit button an action
        submit.addActionListener(e -> {
            try {
                selectedCust.updateDetails(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(), fields[8].getText(), fields[9].getText(), fields[10].getText(),Double.parseDouble(fields[11].getText()));
                dialog.setVisible(false);
                int index = dataTable.getSelectedRow();
                // save the data to a file
                Main.saveData();
                // Update the main table (hack)
                model.removeRow(index);
                model.addRow(selectedCust.dataToArray());

            } catch (Exception x) {
                System.out.println(x);
            }
        });
        dialog.setVisible(true);

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
