import java.io.*;
import java.util.*;

/** 
 * A game based on a room layout, movement, shooting and monsters which can kill the user
 * @author Chris McDonald - The Program: Hunt the Wumpus!
 */

public class mainwumpus
{
    public static void main(String [] args)
    throws IOException
    {
        // reads in the text file with the cave layout
        Scanner roomfile=new Scanner(new FileReader("rooms.txt"));
        Scanner cin= new Scanner(System.in);
        // gets the total number of rooms
        int numberofrooms = roomfile.nextInt();
        // creates an array for the room class/object
        room [] layout = new room [numberofrooms+1];
        for(int i=1; i<numberofrooms+1; i++)
        {
            layout[i] = new room(roomfile);
            //System.out.println(layout[i]);
        }
        // variables
        int arrow = 3;
        int rooms = 1;
        int shoot = 0;
        int n = 0;
        char c = ' ';
        char play = ' ';
        int wumpus = 0;
        int spiders = 0;
        int pit1 = 0;
        int pit2 = 0;
        int bats = 0;
        // loop to determine if the user wishes to play again
        do
        {
            // loop to place the wumpus, spiders, pit1, pit2 and bats all in different rooms
            do
            {
                wumpus = (int)(1+10*Math.random());
                if(wumpus == 1)
                { wumpus = (int)(1+10*Math.random()); }
                spiders = (int)(1+10*Math.random());
                if(spiders == 1 || spiders == wumpus)
                { spiders = (int)(1+10*Math.random()); }
                pit1 = (int)(1+10*Math.random());
                if(pit1 == 1 || pit1 == wumpus || pit1 == spiders)
                { pit1 = (int)(1+10*Math.random()); }
                pit2 = (int)(1+10*Math.random());
                if(pit2 == 1 || pit2 == pit1 || pit2 == wumpus || pit2 == spiders)
                { pit2 = (int)(1+10*Math.random()); }
                bats = (int)(1+10*Math.random());
                if(bats == 1 || bats == wumpus || bats == spiders || bats == pit1 || bats == pit2)
                { bats = (int)(1+10*Math.random()); }
            }
            while(wumpus == spiders || wumpus == pit1 || wumpus == pit2 || wumpus == bats || spiders == pit1 || spiders == pit2 || spiders == bats || pit1 == pit2 || pit1 == bats  || pit2 == bats || spiders == 1 || pit1 == 1 || pit2 == 1 || bats == 1);
            System.out.println("Welcome to **Hunt The Wumpus!**" + "\n");
            // loop to play the game
            while(rooms != 0)
            {
                // method for displaying the main game information
                display(layout, arrow, rooms, wumpus, spiders, pit1, pit2, bats);
                System.out.println("(M)ove or (S)hoot?");
                c = cin.next().charAt(0);
                // allows the user to move between rooms
                if(c == 'M' || c == 'm')
                {
                    System.out.println("Which room?");
                    n = cin.nextInt();
                    // if the user goes to room one without 3 arrows, his/her arrows are refilled
                    if(n == 1)
                    {
                        if(arrow != 3)
                        { System.out.println("You're arrows are refilled.");
                          arrow = 3; }
                    }
                    if(n == layout[rooms].getAdj1() || n == layout[rooms].getAdj2() || n == layout[rooms].getAdj3())
                    {
                        // uses n to check the rooms adjacency for movement
                        rooms = n;
                        // user runs into the wumpus
                        if(n == wumpus)
                        {
                            System.out.println("You ran into the Wumpus! You're dead.");
                            break;
                        }
                        // user runs into spiders
                        if(n == spiders)
                        {
                            System.out.println("You run into some spiders and die.");
                            break;
                        }
                        // user runs into a pit, 1 or 2
                        if(n == pit1 || n == pit2)
                        {
                            System.out.println("You fall into a bottomless pit and die.");
                            break;
                        }
                        // user runs into bat
                        if(n == bats)
                        {
                            System.out.println("Bats pick you up and carry you off somewhere.");
                            rooms = (int)(1+10*Math.random());
                        }
                        
                    }
                    // stops user from going into non-adjacent rooms
                    else
                    {
                        System.out.println("You can't walk through walls. Try again.");
                    }
                }
                // allows user to shoot
                else
                {
                    if(c == 'S' || c == 's')
                    {
                        System.out.println("Which room?");
                        shoot = cin.nextInt();
                        if(shoot == layout[rooms].getAdj1() || shoot == layout[rooms].getAdj2() || shoot == layout[rooms].getAdj3())
                        {
                            // user shoots into the room with the wumpus in it
                            if(shoot == wumpus)
                            {
                                System.out.println("Your arrow goes down the tunnel and finds its mark!" +
                                                   "\n" + "You shot the Wumpus! ** You Win! **");
                                break;
                            }
                            // user shoots into a room without the wumpus in it
                            else
                            {
                                System.out.println("Your arrow goes down the tunnel and is lost. You missed.");
                                arrow--;
                            }
                        }
                        // informs user that he/she cannot shoot into that room
                        else
                        {
                            System.out.println("You can't shoot there, that's a wall. Try again.");
                        }
                    }
                    // informs user of incorrect input
                    else
                    {
                        System.out.println("Invalid command, try again.");
                    }
                }
            }
            // allows user to have the option to play again
            System.out.println("\n" + "Play again? (Y/N)");
            play = cin.next().charAt(0);
            System.out.println("********************************");
            // resets the room to 1 and the arrows to 3
            if(play == 'Y' || play == 'y')
            {
                rooms = 1;
                arrow = 3;
            }
        }
        while(play == 'Y' || play == 'y');
    }
    // method for displaying information for the user
    public static void display(room [] layout, int arrow, int rooms, int wumpus, int spiders, int pit1, int pit2, int bats)
    {
        // shows the user his/her current room
        System.out.println("You are in room " + layout[rooms].getRoomNumber() + ".");
        // shows the user his/her arrow count
        System.out.println("You have " + arrow + " arrows left.");
        // shows the room description
        System.out.println(layout[rooms].getName());
        // room one resupplies the user with arrows
        if(layout[rooms].getRoomNumber() == 1)
        {
            System.out.println("The floor is scattered with arrows.");
        }
        // bats are in an adjacent room
        if(layout[rooms].getAdj1() == bats || layout[rooms].getAdj2() == bats || layout[rooms].getAdj3() == bats)
        { System.out.println("You hear wings flapping in the distance."); }
        // a wumpus is in an adjacent room
        if(layout[rooms].getAdj1() == wumpus || layout[rooms].getAdj2() == wumpus || layout[rooms].getAdj3() == wumpus)
        { System.out.println("The smell of a wumpus lingers in the air. "); }
        // spiders are in an adjacent room
        if(layout[rooms].getAdj1() == spiders || layout[rooms].getAdj2() == spiders || layout[rooms].getAdj3() == spiders)
        { System.out.println("You hear a faint clicking noise. "); }
        // a pit is in one or two adjacent rooms
        if(layout[rooms].getAdj1() == pit1 || layout[rooms].getAdj1() == pit2 || layout[rooms].getAdj2() == pit1 || layout[rooms].getAdj2() == pit2 || layout[rooms].getAdj3() == pit1 || layout[rooms].getAdj3() == pit2)
        { System.out.println("You smell a dank odor. "); }
        // shows user the adjacent rooms
        System.out.println("There are tunnels to rooms " + layout[rooms].getAdj1() + ", " + layout[rooms].getAdj2() + ", and " + layout[rooms].getAdj3() + ".");
    }
}
