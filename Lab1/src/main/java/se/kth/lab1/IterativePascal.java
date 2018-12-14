package se.kth.lab1;
import java.math.BigInteger;

/**
 * Class that prints Pascal's triangle with iterative methods.
 *
 */
public class IterativePascal extends ErrorPascal implements Pascal
{
   private boolean reverse;
   
   public IterativePascal(boolean reverse)
   {
       if(reverse == true)
           this.reverse = true;
       else
           this.reverse = false;
   }
  
   /**
    * Prints n amount of rows in Pascal's triangle.
    * 
    * @param n The amount of rows to be printed.
    */
   @Override
   public void printPascal(int n)
   {
       printPascalCheck(n);
       
       if(reverse == false)
       {
           
        for(int i = 0; i < n; i++)
        {
          System.out.println();
          for(int j = 0; j <= i; j++)
              System.out.print(this.binom(i, j) + " ");
        }
        
       }
       
       else
       
        for(int i = n - 1; i >= 0; i--)
        {
          System.out.println();
          for(int j = 0; j <= i; j++)
             System.out.print(this.binom(i, j) + " ");
        }
       
   }
   
   /**
    * Calculates the binomial coefficient.
    * 
    * @param n The specified row
    * @param k The specified index
    * @return The value of the element at [row][index]
    */
   @Override
   public int binom(int n, int k)
   {
      binomCheck(n,k);
      BigInteger nFactorial = this.factorial(n);
      BigInteger kFactorial = this.factorial(k);
      BigInteger nkFactorial = this.factorial (n-k);
       
      BigInteger binomResult = nFactorial.divide((kFactorial.multiply(nkFactorial)));
      
      return binomResult.intValue();
      
   }
   
   /**
    * A factorial method.
    * 
    * @param n to be calculated.
    * @return the value of n!
    */
   private BigInteger factorial (int n)
   {
       BigInteger factorialsum = BigInteger.ONE;
       BigInteger currentFactorial = BigInteger.valueOf(n);
       
       while(currentFactorial.intValue() > 0)
       {
           factorialsum = currentFactorial.multiply(factorialsum);
           currentFactorial = currentFactorial.subtract(BigInteger.ONE);
       }
       
       return factorialsum;
   }
}
