/*
The user inputs two fractions, which are stored as objects. The pgm determines
and ouputs the size, larger fraction, product, sum, and reduced form of them.
*/
import java.text.*;
public class Fraction_Kevin
{
    public static void main (String[] args)
    {
	//loop pgm
	while (true)
	{
	    //round decimal format
	    DecimalFormat x = new DecimalFormat ("##.##");

	    //object/variable declarations
	    Fraction f1;
	    Fraction f2;
	    char repeat;

	    //user input
	    System.out.println ("First Fraction: ");
	    f1 = getFraction ();
	    System.out.println ("Second Fraction: ");
	    f2 = getFraction ();

	    //print out sizes of the fractions
	    System.out.println ("Size of first fraction: " + x.format (f1.size ()));
	    System.out.println ("Size of second fraction: " + x.format (f2.size ()));

	    //prints out larger frac
	    System.out.println ("Fraction " + f1.larger (f2) + " is larger");

	    //print out reduced product & sum of the fractions
	    System.out.println ("The reduced product of fraction " + f1 + " and " + f2 + " is " + (Fraction.product (f1, f2)).reduce ());
	    System.out.println ("The reduced sum of them of fraction " + f1 + " and " + f2 + " is " + (Fraction.sum (f1, f2)).reduce ());

	    //print whether the fractions are equal
	    if (f1.equals (f2))
		System.out.println ("They are equal");

	    else
		System.out.println ("They are not equal.");

	    //print reduced fractions
	    System.out.println ("Reduced fraction 1: " + f1.reduce ());
	    System.out.println ("Reduced fraction 2: " + f2.reduce ());

	    //exit if user inputs 'n'
	    System.out.println ("Repeat? (y/n)");
	    repeat = In.getChar ();
	    if (repeat == 'n' || repeat == 'N')
	    {
		System.out.println ("Terminated.");
		System.exit (0);
	    }
	}
    }


    //getFraction method to recieve inputs
    public static Fraction getFraction ()
    {
	int num, den;
	System.out.print ("Numerator: ");
	num = In.getInt ();
	System.out.print ("Denominator: ");
	den = In.getInt ();

	//return fraction object with users num and den
	Fraction f = new Fraction (num, den);
	return f;
    }
}


//fraction class
class Fraction
{

    private int num;
    private int den;

    //default constructor
    public Fraction ()
    {
	this.num = 1;
	this.den = 1;
    }


    //constructor on user's parameters
    public Fraction (int n, int d)
    {
	this.num = n;
	this.den = d;
    }


    //num getter
    public int getNum ()
    {
	return this.num;
    }


    //den getter
    public int getDen ()
    {
	return this.den;
    }


    //returns size of fraction (decimal)
    public double size ()
    {
	return (double) num / den;
    }


    //returns the object of the fraction that is larger
    public Fraction larger (Fraction f)
    {
    //compare frac sizes
	if (this.size () >= f.size ())
	    return this;
	else
	    return f;
    }


    //calculate product, return it in fraction object
    public static Fraction product (Fraction f1, Fraction f2)
    {
	Fraction p = new Fraction ();
	//product 
	p.num = f1.num * f2.num;
	p.den = f1.den * f2.den;
	return p;
    }


    //prints out the object in fraction form
    public String toString ()
    {
	String frac;
	//turn to positive if both are negative
	if (num < 0 && den < 0)
	    frac = Math.abs (num) + "/" + Math.abs (den);

	else
	    frac = num + "/" + den;

	return frac;
    }


    //returns the sum of two fractions in an fration object
    public static Fraction sum (Fraction f1, Fraction f2)
    {
	Fraction f = new Fraction ();
	//sum
	f.num = f1.num * f2.den + f2.num * f1.den;
	f.den = f1.den * f2.den;

	return f;
    }


    //returns a boolean, depending if fractions are equal
    public boolean equals (Fraction f1, Fraction f2)
    {

	return (f1.num / f1.den == f2.num / f2.den);
    }

    //reduces the fraction
    public Fraction reduce ()
    {
	int gcd = 1;
	Fraction f = this;

	//find gcd
	for (int i = 1 ; i <= Math.abs (f.num) && i <= Math.abs (f.den) ; i++)
	{
	    if (f.num % i == 0 && f.den % i == 0)
		gcd = i;
	}

	//divide frac by gcd
	f.num = f.num / gcd;
	f.den = f.den / gcd;

	//change to (+) if num && den (-)
	if (f.num < 0 && f.den < 0)
	{
	    f.num = Math.abs (f.num);
	    f.den = Math.abs (f.den);
	}

	return f;
    }
}


