package Logic_Model;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Log {
    private static Log instance; //Singleton Pattern
    private List<String>events;



    public Log() {
        events = new ArrayList<>();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEvent(String event) {
        events.add(event);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Log Events: \n");
        for (String event : events) {
            sb.append("- ").append(event).append("\n");
        }
        return sb.toString();
    }


    public void clearFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {

            writer.write("");
        } catch (IOException e) {

            System.err.println("Error while clearing the log file: " + e.getMessage());
        }
    }


    public void writeToFile(String filename) {
        try {
            File logFile = new File(filename);
            if (!logFile.exists()) {
                clearFile(filename);
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.write("Log Output:\n");
            writer.write(this.toString());
            writer.newLine();

            writer.close();

        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

}