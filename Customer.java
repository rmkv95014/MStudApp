package mschoolSystem;

public class Customer
{
    //field variables
    public String name, email, proffession;
    public int idNumber, grade;

    public Customer(String name, String email, int idNumber, String proffession)
    {
        this.name = name;
        this.email = email;
        this.proffession = proffession;
        this.idNumber = idNumber;
    }
}