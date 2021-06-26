package mschoolSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is a class that gets input from the user.
 * This class will not be instantiated, and its only method is to be used as a way to request input from the user.
 * 
 * @author Rohith Muthukumar
 */
public class Prompt 
{
    /**
     * Private constructor to prevent the class from being instantiated     
     */
    private Prompt()
    {}
    /**
     * This method prints out a message and prompts the user to enter a response. 
     * If the resopnse is invalid, print the message and prompt input again.
     * @param ask The message to prompt to the user.
     * @return The user's response to the Prompt.
     */
    public static String getInput(String ask)
    {
        InputStreamReader i = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(i);
        String entry = "";
        System.out.println(ask + "?");
        boolean badInput;
        do
        {
            badInput = false;
            try
            {
                entry = in.readLine();
                if(entry.equals(null)) badInput = true;
            }
            catch (IOException e)
            {
                System.err.println("COULD NOT READ INPUT");
                badInput = true;
            }
        }
        while(badInput);
        return entry;
    }    
}
