import java.io.*;
import java.util.*;
/**
 * Program to manage the data of a camp
 * @author Chris McDonald
 */
public class poisonivy
{
    // Global variables
    static camp camper;
    static camp camper2;
    static camp camper3;
    static camp name;
    static char command;
    static double sum;
    static double total;
    static double avg;
    static int boy;
    static int girl;
    /**
     * Main Method
     */
    public static void main(String [] args)
    throws IOException
    {
        Scanner campfile=new Scanner(new FileReader("camp.txt"));
        Scanner cin=new Scanner(System.in);
        // Creates an object for the binary search tree
        BST tree=new BST();
        System.out.println("Welcome to Camp Posanivee!!");
        while(command != 'Q') {
            command = campfile.next().charAt(0);
            System.out.print("\nCommand: " + command);
            help();
            enroll(campfile, tree);
            withdraw(campfile, tree);
            display(campfile, tree);
            average();
            list(tree);
            gender(tree);
            preorder(tree);
        } // end while loop
        System.out.println("\nEnd of program.");
        System.out.println("Bring plenty of calomine!");
    }
    /**
     * Displays the help command
     * @param The help command
     */
    public static void help()
    {
        if(command == 'H') {
            System.out.println("Here is a list of commands: ");
            System.out.println("H = Help: print a list of commands");
            System.out.println("E = Enroll a new camper(insert)");
            System.out.println("W = Withdraw a camper(delete)");
            System.out.println("D = Display the age and gender of a camper");
            System.out.println("A = Print the average age of the campers");
            System.out.println("L = List all campers names in alphabetical order");
            System.out.println("S = Print the number of boy and girl campers");
            System.out.println("P = List all campers names in preorder");
            System.out.println("Q = Quit");
        } // end if
    }
    /**
     * Enrolls the new campers in the camp
     * @param Enrolls the new camper
     */
    public static void enroll(Scanner campfile, BST tree)
    {
        if(command == 'E') {
        camper = new camp(campfile);
        tree.insert(camper);
        sum = sum + camper.getAge();
        total++;
        System.out.println(camper);
        System.out.println("New camper added.");
        } // end if
    }
    /**
     * Withdraws the campers already enrolled in the camp
     * @param Withdraws a camper
     */
    public static void withdraw(Scanner campfile, BST tree)
    {
        if(command == 'W') {
            camper = new camp(campfile);
            tree.delete(camper);
            sum = sum - camper.getAge();
            total--;
            System.out.println(" " + camper.getCamper());
            System.out.println("Camper withdrawn.");
        } // end if
    }
    /**
     * Displays the name, age and gender of a camper
     * @param Diplays name, age and gender
     */
    public static void display(Scanner campfile, BST tree)
    {
        if(command == 'D') {
            name = new camp(campfile);
            System.out.print(" " + name.getCamper());
            camper3 = (camp)tree.lookup(name);
            if(camper3 == null) {
                System.out.println("Camper not found.");
            } // end if
            else {
                System.out.println("\nName: " + camper3.getCamper() + "\nAge: " + camper3.getAge() + "\nGender: " + camper3.getGender());
            } // end else
        } // end if
    }
    /**
     * Calculates and displays the average age of the campers enrolled
     * @param Calculates and displays the average age
     */
    public static void average()
    {
        if(command == 'A') {
            if(total == 0) {
                System.out.println("\nThere are no campers.");
            } // end if
            else {
                avg = sum/total;
                System.out.println("\nAverage age = " + avg + ".");
            } // end else
        } // end if
    }
    /**
     * Displays the camper in alphbetical order by first their name
     * @param Displays campers in alphabetical order
     */
    public static void list(BST tree)
    {
        if(command == 'L') {
            System.out.println("\nAlphabetical List of Campers:");
            tree.reset(BST.INORDER);
            while(tree.hasNext()) {
                System.out.println(((camp)tree.getNext()).getCamper());
            } // end while loop
        } // end if
    }
    /**
     * Displays the # of campers based on gender
     * @param displays the # of M/F campers
     */
    public static void gender(BST tree)
    {
        if(command == 'S') {
            tree.reset(BST.POSTORDER);
            while(tree.hasNext()) {
                camper2 = ((camp)tree.getNext());
                if(camper2.getGender().charAt(0) == 'M') {
                    boy++;
                } // end if
                if(camper2.getGender().charAt(0) == 'F'){
                    girl++;
                } // end if
            } // end while loop
            System.out.println("\nCamper count by gender: ");
            System.out.println("Boys = " + boy);
            System.out.println("Girls = " + girl);
        }
    }
    /**
     * Displays the campers in a preorder traversal of the tree they are located in
     @param Does a preorder traversal of the tree
     */
    public static void preorder(BST tree)
    {
        if(command == 'P') {
            System.out.println("\nPreorder Traversal: ");
            tree.reset(BST.PREORDER);
            while(tree.hasNext()) {
                System.out.println(((camp)tree.getNext()).getCamper());
            } // end while loop
        } // end if
    }
}
