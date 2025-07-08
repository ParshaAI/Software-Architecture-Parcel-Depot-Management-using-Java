package Presentation_View;

import Logic_Model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class GUIView {
    private Manager manager;
    private JFrame frame;
    private JTextArea outputArea;
    private JComboBox<String> parcelList;
    private JButton loadCustomersButton, loadParcelsButton, displayCustomersButton,
            displayParcelsButton, processCustomerButton, logButton, exitButton, clearLogButton;


    public GUIView(Manager manager) {
        this.manager = manager;
        frame = new JFrame("Parcel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new FlowLayout());

        initalizeUIComponents();
        setUpActionListeners();

    }






    private void initalizeUIComponents() {
        outputArea = new JTextArea(50, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        loadCustomersButton = new JButton("Load Customers");
        loadParcelsButton = new JButton("Load Parcels");
        displayCustomersButton = new JButton("Display Customers");
        displayParcelsButton = new JButton("Display Parcels");
        processCustomerButton = new JButton("Process Current Customer");
        logButton = new JButton("Show Log");
        clearLogButton = new JButton("Clear Log");
        exitButton = new JButton("Exit");

        parcelList = new JComboBox<>();

        frame.add(loadCustomersButton);
        frame.add(loadParcelsButton);
        frame.add(displayCustomersButton);
        frame.add(displayParcelsButton);
        frame.add(processCustomerButton);
        frame.add(logButton);
        frame.add(clearLogButton);
        frame.add(exitButton);
        frame.add(parcelList);
        frame.add(scrollPane);

    }

    private void setUpActionListeners() {
        loadCustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadCustomers();
                ;
            }
        });


        loadParcelsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadParcels();
            }
        });

        displayCustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayCustomers();
            }
        });

        displayParcelsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayParcels();
            }
        });

        processCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processCustomer();
            }
        });

        logButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLog();
            }
        });


        clearLogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearLog();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });

    }

    private void loadCustomers() {
        manager.loadCustomers();
        outputArea.append("Customers loaded successfully.\n");
    }

    private void loadParcels() {
        manager.loadParcels();
        outputArea.append("Parcels loaded successfully.\n");
    }

    public void displayCustomers() {
        outputArea.append("Displaying Customers.\n");
        outputArea.append(manager.getCustomerQueue().toString() + "\n");
    }


    public void displayParcels() {
        outputArea.append("Displaying Parcels.\n");
        outputArea.append(manager.getParcelMap().toString() + "\n");
    }

    public void processCustomer() {
        String selectedParcel = (String) parcelList.getSelectedItem();
        String res = manager.processCustomer();
        if (res != null) {
            outputArea.append(res + "\n");
        } else {
            outputArea.append("No customer or parcel found.\n");
        }
    }

    public void showLog() {
        // Write the log to a text file
        try {
            File logFile = new File("src/Logic_Model/log.txt");
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.write("Log Output:\n");
            writer.write(manager.getLog().toString());
            writer.newLine();

            writer.close();

            outputArea.append("Log has been written to log.txt.\n");
        } catch (IOException e) {
            outputArea.append("Error writing log to file.\n");
            e.printStackTrace();
        }
    }



    private void clearLog() {
        String logFilePath = "src/Logic_Model/log.txt";
        manager.getLog().clearFile(logFilePath);

        outputArea.append("Log has been cleared.\n");
    }

    private void exitApplication() {
        System.exit(0);
    }


   public void show() {
        frame.setVisible(true);
   }

}