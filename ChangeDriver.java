import java.util.Scanner;

/**
    ChangeDriver class that is used as a driver for Change and ChangeCalculator. Takes an input
    of integers from the user, that represent coins, and will determine the smallest amount of coins to
    reach the target specified by the user, along with the approach taken.
*/
public class ChangeDriver
{
    //MAIN
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Change coins = new Change(line);
        System.out.println(coins.findNumCoins());
    }
}