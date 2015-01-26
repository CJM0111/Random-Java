import java.io.*;
import java.util.*;
// Camp Object Class
// @author Chris McDonald
public class camp implements Comparable
{
    char command;
    String camper;
    int age;
    String gender;
    String blank;
    
    /** 
     Constructor to read form a file
     @param r Scanner object to read from
     */
    public camp(Scanner r)
    {
        if(r.hasNext()) {
            camper = r.next();
            if(r.hasNextInt()) {
                age = r.nextInt();
                if(r.hasNext()) {
                    gender = r.next();
                    blank = r.nextLine();
                } // end if
            } // end if
        } // end if
        else{
            blank = r.nextLine();
        } // end else
        
    }
    /**
     Print - observer
     @return a string representing the info
     */
    public String toString()
    {
        return command + " " + camper + " " + age + " " + gender;
    }
    /**
     Get the command - observer
     @return the command
     */
    public char getCommand()
    { return command; }
    /**
     Get the camper's name - observer
     @return the camper's name
     */
    public String getCamper()
    { return camper; }
    /**
     Get the age of the camper - observer
     @return the camper's age
     */
    public int getAge()
    { return age; }
    /**
     Get the gender of the camper - observer
     @return the camper's gender
     */
    public String getGender()
    { return gender; }
    /**
     CompareTo function
     Uses built-in comparable interface
     */
    public int compareTo(Object x)
    {
        camp b=(camp) x;
        return camper.compareTo(b.camper);
    }
}
