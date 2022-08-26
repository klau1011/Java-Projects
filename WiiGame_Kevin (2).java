/* This pgm reads a text file holding wii game info, converting it into
a table. User can sort and search dependingon specific categories.
 Pgm sorts or searches and outputs the table. Repeats
until terminated. */

import java.io.*;
public class WiiGame_Kevin
{
    public static void main (String[] args)

    {

	//declarations
	String[] [] all = new String [20] [6];
	String[] [] printArr = new String [20] [6];
	int i = 0;
	String inp1, inp2;
	String[] [] sortedArray = new String [20] [6];
	String[] [] sortedArray2 = new String [20] [6];
	String userSearch = "";
	String searchOrSort;


	try
	{
	    //read file
	    FileReader fr = new FileReader ("WiiGames.txt");
	    BufferedReader br = new BufferedReader (fr);

	    String line;

	    //read while the line isnt null
	    while (true)
	    {
		line = br.readLine ();

		if (line == null)
		{
		    //break if empty
		    break;
		}

		//get rid of empty spaces
		String line2 = line.replaceAll (" ", "");
		//split the line into 2d array
		String[] aLine = line2.split ("\\s+");
		all [i] = aLine;
		i++;
	    }
	    //close the file
	    br.close ();
	}
	//catch error
	catch (IOException e)
	{
	    System.out.println ("Error reading file");
	}

	//set another array equal to the 2d array
	for (i = 0 ; i < 20 ; i++)
	{
	    for (int j = 0 ; j < 6 ; j++)
	    {
		printArr [i] [j] = all [i] [j];
	    }
	}

	//print header, initial table
	printHeader ();
	printInitial (printArr);

	System.out.println ();

	//loop pgm
	while (true)
	{
	    //user input search or sort
	    System.out.println ("Search (A) or Sort (B)? [-1 to exit]");
	    searchOrSort = In.getString ();

	    //terminate on -1 input
	    if (searchOrSort.equals ("-1"))
	    {
		System.out.println ("Terminated.");
		System.exit (0);
	    }

	    //if user selects option B -> sort
	    if (searchOrSort.equalsIgnoreCase ("B"))
	    {
		System.out.println ("Sort by game(ga), genre(ge) or release(r)?");
		inp1 = In.getString ();

		//sort by game
		if (inp1.equals ("ga"))
		    sortedArray = sortAlphabetical (all, 0);

		//sort by genre
		else if (inp1.equals ("ge"))
		    sortedArray = sortAlphabetical (all, 1);

		//sort by release
		else
		    sortedArray = sortAlphabetical (all, 3);

		//print header
		printHeader ();
		printTable (sortedArray, 20);
	    }
	    //else user -> option A
	    else
	    {
		//user input type of search
		System.out.println ("Search by game(g), release(r), or price?(p)");
		inp2 = In.getString ();
		if (inp2.equals ("g"))
		{
		    //input search game
		    System.out.println ("Search game: ");
		    userSearch = In.getString ();
		    sortedArray2 = searchGame (userSearch, all);
		}
		//input search year
		else if (inp2.equals ("r"))
		{
		    System.out.println ("Search release year: ");
		    userSearch = In.getString ();
		    sortedArray2 = searchRelease (userSearch, all);
		}
		//input search price
		else if (inp2.equals ("p"))
		{
		    System.out.println ("Search price : ");
		    userSearch = In.getString ();
		    sortedArray2 = searchPrice (userSearch, all);
		}

		//# of results
		int numberOfResults = numResults (sortedArray2);

		if (sortedArray2 [0] [0] == "No Results Found")
		    numberOfResults = 0;

		System.out.println (numberOfResults + " results found for " + userSearch);

		//if there are results, print the table
		if (numberOfResults != 0)
		{
		    printHeader ();
		    printTable (sortedArray2, numberOfResults);
		}
	    }
	}
    }

    //sort game method
    public static String[] [] sortAlphabetical (String[] [] a, int col)
    {

	String[] temp;
	int n = 20;

	//bubble sort -> iterates and compares each pair and swap if needed
	for (int j = 0 ; j < n - 1 ; j++)
	{
	    for (int i = j + 1 ; i < n ; i++)
	    {
		if (a [j] [col].compareTo (a [i] [col]) > 0)
		{
		    temp = a [j];
		    a [j] = a [i];
		    a [i] = temp;

		}
	    }
	}
	//return sorted game array
	return a;
    }
    //method to search for a game
    public static String[] [] searchGame (String searchTerm, String[] [] array)
    {
	//declarations
	String[] [] sortedArray;
	String[] [] searchedArray = new String [20] [6];

	//initialize initial array to blank
	for (int i = 0 ; i < 20 ; i++)
	{
	    for (int j = 0 ; j < 6 ; j++)
	    {
		searchedArray [i] [j] = "";
	    }
	}

	int j = 0;

	//sort the array before searching
	sortedArray = sortAlphabetical (array, 0);
	//iterate through each row
	for (int i = 0 ; i < 20 ; i++)
	{
	    //replace the underscore with a space
	    String noSpace = array [i] [0].replaceAll ("_", " ");
	    String lower = array [i] [0].toLowerCase ();

	    //if the search term matches array item
	    if (sortedArray [i] [0].equalsIgnoreCase (searchTerm) || noSpace.equalsIgnoreCase (searchTerm) ||
		    noSpace.indexOf (searchTerm) != -1 || lower.indexOf (searchTerm) != -1
		    )
	    {
		//add the row to the searched array
		searchedArray [j] = sortedArray [i];
		j++;
	    }
	}
	//if no rows, empty array
	if (j == 0)
	    searchedArray [0] [0] = ("No Results Found");
	
	//return searched array by game
	return searchedArray;
    }

    //method to search by release year
    public static String[] [] searchRelease (String searchTerm, String[] [] array)
    {
	//declarations
	String[] [] sortedArray;
	String[] [] searchedArray = new String [20] [6];

	//initalize array to empty
	for (int i = 0 ; i < 20 ; i++)
	{
	    for (int j = 0 ; j < 6 ; j++)
	    {
		searchedArray [i] [j] = "";
	    }
	}
	int j = 0;

	//sort array before searching
	sortedArray = sortAlphabetical (array, 0);

	//iterate each row
	for (int i = 0 ; i < 20 ; i++)
	{
	    //string to int of the search year and array elemeent
	    int yearSearch = Integer.parseInt (searchTerm);
	    int yearNum = Integer.parseInt (sortedArray [i] [3]);

	    //if search matches array year
	    if (yearSearch == yearNum)
	    {
		//add row to searched array
		searchedArray [j] = sortedArray [i];
		j++;
	    }
	}
	//if no rows, no results found
	if (j == 0)
	    searchedArray [0] [0] = ("No Results Found");
	//return searched array of release year
	return searchedArray;

    }


    //method to search price
    public static String[] [] searchPrice (String searchTerm, String[] [] array)
    {
	//declarations
	String[] [] sortedArray;
	String[] [] searchedArray = new String [20] [6];

	//initalize array to empty
	for (int i = 0 ; i < 20 ; i++)
	{
	    for (int j = 0 ; j < 6 ; j++)
	    {
		searchedArray [i] [j] = "";
	    }
	}
	int j = 0;

	//sort array before searching
	sortedArray = sortAlphabetical (array, 0);

	//iterating each row
	for (int i = 0 ; i < 20 ; i++)
	{
	    String item = sortedArray [i] [5];
	    //checking if search term equals array itemn
	    if (item.equalsIgnoreCase (searchTerm) || item.indexOf (searchTerm) != -1) //if game is same
	    {
		//if match, add row to searched array
		searchedArray [j] = sortedArray [i];
		j++;
	    }
	}
	//if no rows, no results
	if (j == 0)
	    searchedArray [0] [0] = ("No Results Found");
	//return searched table of prices
	return searchedArray;
    }

    //method to determine number of results after a search
    public static int numResults (String[] [] array)
    {
	//counter
	int i = 0;

	while (true)
	{ //replace all spaces
	    String item = array [i] [0].replaceAll ("\\s+", "");
	    //if row isnt empty, increment the count
	    if (!(item.equals ("")))
	    {
		i++;
	    }
	    //if row empty, break loop
	    else
	    {
		break;
	    }

	}
	//return counter
	return i;
    }

    //method print the initial table
    public static void printInitial (String[] [] array)
    {
	//loop row
	for (int z = 0 ; z < 20 ; z++)
	    {
		//loop col
		for (int j = 0 ; j < 6 ; j++)
		{
		    String spaced = array [z] [j];
		    if (j <= 2)
		    {
			//add spaces to align table
			while (spaced.length () < 23)
			{
			    spaced += " ";
			}
		    }
		    else
		    {
			//add spaces to align table
			while (spaced.length () < 10)
			{
			    spaced += " ";
			}
		    }
		    //output array elements
		    System.out.print (spaced);
		}
		//line break
		System.out.println ();

	    }
    }

    //method to print the header
    public static void printHeader ()
    {
	System.out.println ("Game                   Genre                  Developer             Release    Sales     Price  ");
	System.out.println ("                                                                            (in millions)");
	System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public static void printTable (String[] [] array, int num)
    {
	//loop rows
	for (int z = 0 ; z < num ; z++)

	    {
		//loop col
		for (int j = 0 ; j < 6 ; j++)
		{
		    String spaced = array [z] [j];
		    if (j <= 2)
		    {
			//add spaces to align
			while (spaced.length () < 23)
			{
			    spaced += " ";
			}
		    }

		    else
		    {
			//add spaces to align
			while (spaced.length () < 10)
			{
			    spaced += " ";
			}
		    }
		    //print table
		    System.out.print (spaced);
		}
		//line break
		System.out.println ();
	    }
	System.out.println ();
    }
}
// WiiGame_Kevin class
