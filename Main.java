package mschoolSystem;

public class Main {

    public Main()
    {
        CustomerUtil.database = CustomerUtil.readObject();
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        while (true) {
            m.run();
        }
    }

    private void run() {
        boolean terrible;
        do {
            terrible = false;
            String action = Prompt.getInput(
                    "Would you like to add someone to the database, view the current database, or save the database");
            if (action.equalsIgnoreCase("enroll")) {
                Customer customer = new Customer(getName(), getEmail(), getID(), getProffession());
                Customer existingCustomer = CustomerUtil.checkCustomer(customer);
                if (existingCustomer == (null)) {
                    CustomerUtil.addCustomer(customer);

                } else {
                    boolean bad;
                    do {
                        bad = false;
                        String errorPrompt = Prompt.getInput(
                                "It looks like there is someone else within the database who shares the same information. "
                                        + "Would you like to edit your information or would you like to view the already existing information");
                        if (errorPrompt.equalsIgnoreCase("view")) {
                            CustomerUtil.print(existingCustomer);
                            bad = true;
                        }
                        if (errorPrompt.equalsIgnoreCase("edit")) {
                            customer.name = getName();
                            customer.idNumber = getID();
                            customer.proffession = getProffession();
                            customer.email = getEmail();
                            existingCustomer = CustomerUtil.checkCustomer(customer);
                            if (!(existingCustomer == (null))) {
                                bad = true;
                            }
                        }
                    } while (bad);

                }
            }
            if (action.equalsIgnoreCase("view")) {
                CustomerUtil.print(null);

            }
            if (action.equalsIgnoreCase("save")) {
                CustomerUtil.writeObject();

            }
            if (action.equalsIgnoreCase("exit")) {
                String pString = Prompt.getInput("Do you want to save the database");
                if (pString.equalsIgnoreCase("yes")) {
                    CustomerUtil.writeObject();
                } else {
                    System.out.println("WARNING, DATABASE WILL NOT BE SAVED");
                }
                System.exit(0);
            } else {
                System.out.println("PLEASE ENTER A RECOGNIZED ACTION");
                terrible = true;
            }
        } while (terrible);
    }

    private String getProffession() {
        boolean badInput;
        String prof;
        do {
            badInput = false;
            prof = Prompt.getInput("Are you a student, teacher administrator or parent");
            if (!(prof.equalsIgnoreCase("student")) && !(prof.equalsIgnoreCase("parent"))
                    && !(prof.equalsIgnoreCase("admin")) && !(prof.equalsIgnoreCase("teacher"))) {
                System.out.println("The recognized roles are Parent, Student, Teacher or Admin");
                badInput = true;
            }
        } while (badInput);
        return prof;
    }

    private int getID() {
        boolean badInput;
        int id = 0;
        do {
            badInput = false;
            try {
                id = Integer.parseInt(Prompt.getInput("What is your school ID"));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
                badInput = true;
            }
            if (id <= 0) {
                System.out.println("Please enter a valid ID");
                badInput = true;
            }
        } while (badInput);
        return id;
    }

    private String getEmail() {
        return Prompt.getInput("What is your email for the school");
    }

    private String getName() {
        String firstName = Prompt.getInput("What is your first name").toUpperCase();
        String lastName = Prompt.getInput("What is your last name").toUpperCase();
        return firstName + " " + lastName;
    }
}
