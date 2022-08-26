/*  This pgm simulates a user specified amount of dice rolls. The data is stored
in a 2D array where the user can choose to display it in raw or percent output.
Repeats until terminated.    */


import java.text.*;

public class Roll_Kevin
{
    public static void main (String[] args)
    {

	//loop pgm
	while (true)
	{

	    //array/variables declarations
	    double[] [] dice = new double [6] [6];
	    double[] diceCount = new double [11];
	    double[] dicePercent = new double [11];
	    char rawPercent = 'r';
	    char repeat;
	    int rolls;

	    //rounding format
	    DecimalFormat x = new DecimalFormat ("#0.00");


	    //initalize every 2d array cell to 0
	    for (int i = 0 ; i < 6 ; i++)
	    {
		for (int j = 0 ; j < 6 ; j++)
		{
		    dice [i] [j] = 0;
		}
	    }

	    //user input number of rolls
	    System.out.println ("Number of rolls? :");
	    rolls = In.getInt ();

	    //simulate dice roll, add one to the corresponding cell in the 2d array for the roll
	    for (int i = 0 ; i < rolls ; i++)
	    {
		//random dice roll (1 to 6)
		int dice1 = (int) Math.floor (Math.random () * (6) + 1);
		int dice2 = (int) Math.floor (Math.random () * (6) + 1);


		//add the specific dice roll to the 2d array
		dice [dice1 - 1] [dice2 - 1]++;

		//increment the sum of the dice roll in the running total count
		diceCount [dice1 + dice2 - 2]++;


	    }


	    System.out.println ("Array: (number inside cell represents how many times it was rolled)");


	    //line break
	    System.out.println ("");


	    //output 2d array
	    System.out.print ("Dice    ");

	    //loop array length
	    for (int i = 0 ; i < 6 ; i++)
	    {
		//output the column numbers for header
		System.out.print ((i + 1) + "\t");
	    }
	    //line break
	    System.out.println ();

	    //output each cell
	    for (int i = 0 ; i < 6 ; i++)
	    {
		//header line
		System.out.println ("-----------------------------------------------------");
		for (int j = 0 ; j < 6 ; j++)
		{
		    //columns border output
		    if (j == 0)
			System.out.print ((i + 1) + "   |" + "\t" + (int) dice [i] [j] + "\t");
		    else
			//non columns output
			System.out.print ((int) dice [i] [j] + "\t");
		}

		//line break
		System.out.println ();

	    }


	    //ask user if they want raw or percent
	    System.out.println ("Part A (roll combination): raw(r) or percent(p)?");
	    rawPercent = In.getChar ();


	    //raw data
	    if (rawPercent == 'r')
	    {

		//loop rows
		for (int i = 0 ; i < 6 ; i++)
		{
		    //loop col
		    for (int j = 0 ; j < 6 ; j++)
		    {
			//output times rolled for each roll
			System.out.println ("Rolled " + "(" + (i + 1) + "-" + (j + 1) + "): " + (int) dice [i] [j] + " times");
		    }
		}
	    }

	    //percent data
	    else
	    {
		//loop rows
		for (int i = 0 ; i < 6 ; i++)
		{
		    //loop col
		    for (int j = 0 ; j < 6 ; j++)
		    {
			//output percentages for each roll
			System.out.println ("Rolled " + "(" + (i + 1) + "-" + (j + 1) + "): " + x.format (dice [i] [j] / rolls * 100) + "%");
		    }
		}

	    }

	    //ask user if they wannt raw or percentages for running totals
	    System.out.println ("Part B (running total from 2-12): Raw (r) or percent (p) ?");
	    rawPercent = In.getChar ();


	    //raw data
	    if (rawPercent == 'r')
	    {
		//loop diceCount array length
		for (int i = 0 ; i < 11 ; i++)
		{
		    //output how many times each sum was rolled
		    System.out.println ("Rolled " + (i + 2) + "\t" + (int) diceCount [i] + " times");
		}

	    }

	    //percent data
	    else
	    {

		//loop through the dicepercent array
		for (int i = 0 ; i < 11 ; i++)
		{
		    //calculate percentage
		    dicePercent [i] = diceCount [i] / rolls * 100;
		}
		for (int i = 0 ; i < 11 ; i++)
		    //output the percent of each sum rolled
		    System.out.println ("Rolled " + (i + 2) + "\t" + x.format (dicePercent [i]) + "% ");

	    }

	    //ask user to repeat
	    System.out.println ("Repeat (y/n)?");
	    repeat = In.getChar ();

	    //terminate on no
	    if (repeat == 'n' || repeat == 'N')
	    {
		System.out.println ("Terminated.");
		System.exit (0);
	    }

	}

    } // main method
} // Roll_Kevin class
