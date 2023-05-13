import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/** 
   Change class that is used to create a list of coins and a target value from a user. The methods
   provided in this class will help find the smallest amount of coins needed to reach a target value, along
   with providing the approach used to do so.
*/
public class Change extends ChangeCalculator
{
    //instance variables
    private List<Integer> list = new ArrayList<>();
    private int target = 0;
    private boolean greedy = true;

    /**
       Initializes the instance variables of Change object from a string
       @param numbers A string of integers
    */
    public Change(String numbers)
    {
        list = createArray(numbers);
        target = list.remove(list.size()-1);
        greedy = check(list);
    }

    /**
        Builds and returns an array list of coins from a string
        @param numbers String of containing coins
        @return An array list that is created from numbers
    */
    private List<Integer> createArray(String numbers)
    {
        List<Integer> l = new ArrayList<>(); //initialize array list 
        Scanner inline = new Scanner(numbers);
        while(inline.hasNext())
        {
            //while there is another integer in file, will add the integer to the array list
            int n = Integer.parseInt(inline.next());
            l.add(n);
        }
        return l; //returns the array list
    }

    /**
        Checks an array list of coins to determine if the values provided can be solved by a greedy approach
        @param l The array list that will be read from
        @return Returns true if a greedy approach can be used, false if a greedy approach is not possible
    */
    private boolean check(List<Integer> l)
    {
        boolean flag = true; //loop variable
        boolean cBit = false; //set to true if greedy approach is possible
        int i = 0; //index variable
        int j = 1; //index variable
        int c1 = 0; //first check variable
        int c2 = 0; //second check variable
        int goal = 0; //goal amount to count integers to

        while(flag)
        {
            goal = l.get(i) + l.get(j) - 1; //grabs two variables sequentially and calculates goal
            c1 = coinsNeeded(l, goal, i); //c1 = # of coins starting with i
            c2 = coinsNeeded(l, goal, j); //c2 = # of coins starting with j

            if(c1 > c2) //if c1 > c2, dynamic approach needs to be taken
                flag = false;

            if(c1 < c2 && l.get(j) == 1) //if c1 < c2 & we are at end of list
            {
                flag = false; //while loop ends
                cBit = true; //cBit set to true, greedy approach can be taken
            }
            i++; //increment i
            j++; //increment j
        }

        if(cBit)
            return true; //use greedy approach
        return false; //use dynamic programming approach
    }

    /**
        Checks and returns the number of coins needed to reach a given target
        @param l The array list of integers
        @param tar The target that the integers need to reach
        @param start The starting place where we begin counting in the list
        @return The amount of coins needed to reach the target
    */
    private int coinsNeeded(List<Integer> l, int tar, int start)
    {
        int n = 0; //counting variable
        for(int i = start; i < l.size(); i++)
        {
            int test = l.get(i); //sets test to integer at index i in list
            while(tar >= test) //if tar >= test
            {
                tar -= test; //subtract test from tar
                n++; //increment n
            }
        }
        return n; //return the amount of coins used to reach tar
    }

    /**
       Returns a string containing the number of coins and denominations that were used to reach
       the target value, along with whether a greedy or dynamic approach was taken
       @return The string of coins, and approach taken, to reach the target value
    */
    public String findNumCoins()
    {
        if(greedy)
            return super.greedy(list, target);
        return super.dynamic(list, target);
    }

}