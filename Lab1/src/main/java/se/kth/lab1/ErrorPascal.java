package se.kth.lab1;

/**
 * Sanity check for calculating values in Pascal's triangle.
 * 
 */
public abstract class ErrorPascal implements Pascal
{
    /**
     * Checks if the speicified value of n is valid.
     * 
     * @param n the specified row in Pascal's triangle.
     */
    public void printPascalCheck(int n)
    {
        if(n <= 0)
            throw new IllegalArgumentException("n has to be greater than 0: + Entered " + n);
    }

    /**
     * Checks if the specified value of n and k is valid.
     * 
     * @param n is the specified row in Pascal's triangle.
     * @param k is the speicified index in Pascal's triangle.
     */
    public void binomCheck(int n, int k)
    {
        if(n < 0 || k < 0 || k > n )
            throw new IllegalArgumentException("Invalid n and k input:");
    }
}
