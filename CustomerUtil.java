package mschoolSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CustomerUtil {
    
    public static ArrayList<Customer> database;

    public static ArrayList<Customer> readObject() {
        // initialize IO variables
        FileInputStream fs;
        ObjectInputStream ois;
        ArrayList<Customer> tempDatabase = new ArrayList<>();
        try {
            fs = new FileInputStream("database.db");
            ois = new ObjectInputStream(fs);
            // read the information in the file, the informtation should not be null
            Object tempObject = ois.readObject();
            if (!(tempObject.equals(null))) {
                tempDatabase = (ArrayList<Customer>) tempObject;
            }
            fs.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("NO EXISTING DATBASE WAS FOUND");
            return new ArrayList<Customer>();
        }
        return tempDatabase;
    }

    public static Customer checkCustomer(Customer customerToCheck) {
        String tempName = customerToCheck.name;
        String tempEmail = customerToCheck.email;
        int tempID = customerToCheck.idNumber;
        for (Customer customer : database) {
            if (tempName.equalsIgnoreCase(customer.name)
                    && (tempEmail.equalsIgnoreCase(customer.email) || tempID == customer.idNumber))
                return customer;
        }
        return null;
    }

    public static void writeObject() {
        try {
            // initialize IO Objects
            FileOutputStream foo = new FileOutputStream("database.db");
            ObjectOutputStream out = new ObjectOutputStream(foo);
            // write the database to save the file
            out.writeObject(database);
            // close the objects
            out.close();
            foo.close();
            System.out.println("SAVED DATABASE");
            System.exit(0);
        } catch (IOException e) {
            // print error message if database could not be saved
            System.err.println("COULD NOT SAVE DATABASE");
        }
    }

    public static void addCustomer(Customer customer) {
        database.add(customer);
        System.out.println("CUSTOMER HAS BEEN ADDED TO DATABASE");
    }

    public static void print(Customer customer) {
        System.out.print("+---------");
        for (int a = 0; a < 5; a++)
            System.out.print("-------+");
        System.out.println("-+-------------+");
        System.out.print("|      NAME      |");
        System.out.print("      ID       |");
        System.out.print("      EMAIL      |");
        System.out.println("    ROLE     |");
        if (database.size() == 0)
            return;
        if (customer.equals(null)) {
            for (int row = 0; row < database.size(); row++) {
                System.out.print("+---------");
                for (int a = 0; a < 5; a++)
                    System.out.print("-------+");
                System.out.println("-+-------------+");
                Customer current = database.get(row);
                // System.out.print(current.name);
                // System.out.print("\t");
                // System.out.print(current.idNumber);
                // System.out.print(current.email);
                // System.out.println(current.role);
                System.out.printf(" %s      %d      %s     %s \n", customer.name.toUpperCase(), customer.idNumber,
                        customer.email, customer.proffession.toUpperCase());
            }
        } else {
            System.out.printf(" %s      %d      %s     %s \n", customer.name.toUpperCase(), customer.idNumber,
                    customer.email, customer.proffession.toUpperCase());
        }
    }
}
