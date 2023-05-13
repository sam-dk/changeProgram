import java.util.List;
import java.util.ArrayList;

/**
   ChangeCalculator class that is mainly used as a superclass for the Change class. The methods
   provided in this class calculate the smallest number of coins needed to reach a target value.
   This class provides to ways to find the smallest number of coins, dynamic or greedy.
*/
public class ChangeCalculator
{
    //no-arg initializer
    public ChangeCalculator()
    {}

    /** 
        Returns the string of coins that are used to reach the target goal using the greedy approach
        @param l The array list of coins
        @param t The target value that we are counting to
        @return The string of coins needed to reach the target value
    */
    public String greedy(List<Integer> l, int t)
    {
        List<Integer> c = new ArrayList<>(l.size()); //initialize an array list to count number of coins
        int n = 0; //loop variable

        for(int i = 0; i < l.size(); i++)
        {
            int value = l.get(i); //value set to integer at index i in l
            while(t >= value) //while t >= value
            {
                t -= value; //value subtracted from t
                n++; //increment n
            }
            c.add(n); //add n to c
            n = 0; //set n to 0
        }

        //displaying the solution
        String ans = "Greedy - ";
        for(int i = 0; i < l.size(); i++)
        {
            //if index in c = 0 --> the coin was not used, so we find values where index of c > 0
            if(c.get(i) != 0)
                ans += c.get(i) + "x" + l.get(i) + ", ";
        }
        ans = ans.substring(0, ans.length()-2); //gets rid of ", " at end of string
        return ans;
    }

    /**
        Returns the string of coins that are used to reach the target goal using the dynamic programming approach
        @param l The array list of coins
        @param t The target value that we are counting to
        @return The string of coins needed to reach the target value 
    */
    public String dynamic(List<Integer> l, int t)
    {
        //initialize c[] and d[], set index 0 of both to 0
        int[] c = new int[t+1];
        int[] d = new int[t+1];
        c[0] = 0; d[0] = 0;

        int j = l.size()-1; //j set to end of l, count backwards --> need to count from lowest integer
        int weight = 0; //value of most recently used coin
        int lastWeight = 0; //value of second most recently used coin

        for(int i = 1; i < t+1; i++)
        {
            if(i == l.get(j)) //if index i = the coin value at j
            {
                lastWeight = weight; //lastWeight set to weight
                c[i] = 1;            //c[i] = 1
                d[i] = l.get(j);     //d[i] = the new weight --> new coin reached
                weight = d[i];       //weight is set to the most newest coin
                if(j != 0)           //if we are not at the start of l --> decrement j
                    j--;
            }
            if(i > weight) //if i > weight --> we are counting to next highest coin
            {
                //we find the lowest possible # of coins we can use to reach this index i --> c[i] to equal the minimum
                c[i] = Math.min(c[i-1] + 1, c[i-weight]+1);
                c[i] = Math.min(c[i], c[i-lastWeight]+1);

                //depending on what value c[i] gets --> we grab the denomination of coin that that index in d
                if(c[i] == c[i-1] + 1)
                    d[i] = d[i-1];
                else if(c[i] == c[i-weight]+1)
                    d[i] = d[i-weight];
                else
                    d[i] = d[i-lastWeight];
            }
        }

        int[] ansArray = new int[l.size()]; //initialize array to count the number of coins of each denomination
        while(t > 0) //while our target goal has not been reached
        {
            //start at end of d[], if d[t] > 0, subtract t by the value at d[t]
            if(d[t]>0)
                ansArray[l.indexOf(d[t])]++; //increment the value at the corresponding index in ansArray by 1
            t = t - d[t]; //substract value at d[t] from t
        }

        //displaying the solution
        String ans = "Dynamic - ";
        for(int i = 0; i < l.size(); i++)
        {
            //if index in ansArray = 0 --> the coin was not used, so we find values where index of ansArray > 0
            if(ansArray[i] != 0)
                ans += ansArray[i] + "x" + l.get(i) + ", ";
        }
        ans = ans.substring(0, ans.length()-2); //gets rid of ", " at end of string
        return ans;
    }

}
