import java.io.*;
import java.util.*;

/** 
 * The Room Class creates an object of one room to be used in the Main Wumpus program
 * @author Chris McDonald
 */

public class room
{
    int mainroom;
    int adjacent1;
    int adjacent2;
    int adjacent3;
    String roomname;
    
    /**
     Constructor to read from a file
     @param r Scanner object to read from
     */
    public room(Scanner r)
    {
        mainroom = r.nextInt();
        adjacent1 = r.nextInt();
        adjacent2 = r.nextInt();
        adjacent3 = r.nextInt();
        roomname = r.nextLine();
        roomname = r.nextLine();
    }
    // print - observer
    // @return a string representing the room
    public String toString()
    {
        return mainroom + " " + adjacent1 + " " + adjacent2 + " " + adjacent3 + " " + roomname;
    }
    // get the room number - observer
    // @return the room number
    public int getRoomNumber()
    { return mainroom; }
    // get the first adjacent room - observer
    // @return the first adjacent room
    public int getAdj1()
    { return adjacent1; }
    // get the second adjacent room - observer
    // @return the second adjacent room
    public int getAdj2()
    { return adjacent2; }
    // get the third adjacent room - observer
    // @return the third adjacent room
    public int getAdj3()
    { return adjacent3; }
    // get the room description - observer
    // @return the room description
    public String getName()
    { return roomname; }
}
