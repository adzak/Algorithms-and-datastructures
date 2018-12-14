package se.kth.lab1;

/**
 * Class that prints Pascal's triangle with recursive methods.
 *  
 */
public class RecursivePascal extends ErrorPascal implements Pascal
{
   private boolean reverse;
   
   public RecursivePascal(boolean reverse)
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
        
        if(n == 1)
            System.out.println(1);
        
        else
        {
            if(reverse == false && n >= 2)
                printPascal(n - 1);
            
            int[] triangle = new int[n / 2];
            for(int i = 0; i < triangle.length; i++)
            {
                triangle[i] = this.binom(n - 1, i);
                System.out.print(triangle[i] + " ");
            }
            
            if(n % 2!= 0)
                System.out.print(this.binom(n - 1, (n / 2))+ " ");
            
            for(int i = 0; i < triangle.length; i++)
                System.out.print(triangle[triangle.length - 1 - i] + " ");
            
            System.out.println();
     
            if(reverse == true)
                printPascal(n - 1);   
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
       
       if(k == n || k == 0 )
         return 1;
       else return binom(n - 1,k - 1) + binom(n - 1, k);
    }
}
