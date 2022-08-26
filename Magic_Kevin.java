
/* This pgm asks the user to input a set of numbers from (1 to n^2). The pgm determines and outputs
to the user the magic square and whether they form a magic square or not. 
Pgm is looped until user wants to terminate.*/

public class Magic_Kevin
{
    public static void main (String[] args)
    {
        //loop pgm 
        while (true) {

        //variable 
        int side = 0;
        char repeat;

        //user input magic square length
        System.out.println ("Magic square length: ");
        side = In.getInt ();

        //call popuate method and store values in an array
        int[] [] square = populate (side);

        System.out.println();

        //print square
        printSquare(square);

        //if isMagic method returns true, print that it is a magic square
        if (isMagic (square))
            System.out.println ("It is a magic square.");

        //if isMagic method returns false, print that it is not a magic square
        else
            System.out.println ("It is not a magic square.");

            System.out.println();

            //ask if repeat
          System.out.println("Repeat? (y/n)");
        repeat = In.getChar();

        //terminate if no
        if (repeat == 'n' || repeat == 'N')
        {
            System.out.println("Terminated.");
            System.exit(0);
        }
}
}
    //populate method to recieve and create 2d array
    public static int[] [] populate (int side)
    {
        //create new 2d array (magic square) with side length
        int[] [] magicSquare = new int [side] [side];

        //user input magic square #
        System.out.println ("Magic table values (enter from left -> right then top -> bottom): ");

        //loop rows
        for (int i = 0 ; i < side ; i++)
        {
            //loop col
            for (int j = 0 ; j < side ; j++)
            {
                //recieve input
                magicSquare [i] [j] = In.getInt ();
            }
        }
        //return the inputted array
        return magicSquare;
    }

    //isMagic method to check if 2d array is a magic square
    public static boolean isMagic (int[] [] magicSquare)
    {
        //store sum of each diag
        int diag1 = 0;
        int diag2 = 0;

        //side length of square
        int side = magicSquare.length;

        //calculate sum of each diagional
        for (int i = 0 ; i < side ; i++)
        {
            diag1 += magicSquare [i] [i];
            diag2 += magicSquare [i] [side - 1 - i];
        }

        //if diagional sums arent equal, it is not a magic square
        if (diag1 != diag2)
            return false;

        //loop through rows
        for (int i = 0 ; i < side ; i++)
        {
            //reset row and col sum after each loop
            int row = 0, col = 0;

            //loop through columns
            for (int j = 0 ; j < side ; j++)
            {
                //calculate row and col sum
                row += magicSquare [i] [j];
                col += magicSquare [j] [i];
            }

            //if row and col sum arent equal, it is not a magic square
            if (row != col || col != diag1)
                return false;
        }
        //return true if bypass all non-magic square cases
        return true;
    }

    //method to print magic square values
    public static void printSquare (int [][] square)
    {
        //side length of square
        int side = square.length;

        System.out.println("Magic square: ");

        //loop rows
        for (int i = 0; i < side; i++)
        {
            //loop col
            for (int j = 0; j< side; j++)
            {
                //print #
                System.out.print(square[i][j] + " ");
            }
            //line break
            System.out.println();
        }
    }
}

