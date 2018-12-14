package se.kth.lab1;

/**
 *
 * @author Adrian
 */
public class Driver 
{
    public static void main(String[] args)
    {
       RecursivePascal RPfalse = new RecursivePascal(false);
       RecursivePascal RPtrue = new RecursivePascal(true);
       
       IterativePascal IPtrue = new IterativePascal(true);
       IterativePascal IPfalse = new IterativePascal(false);
    
       
       //Printing some triangles
       
       RPfalse.printPascal(3);
       System.out.println();
       
       RPtrue.printPascal(5);
       System.out.println();
       
       
       IPtrue.printPascal(10);
       System.out.println();
       
       IPfalse.printPascal(11);
      
    }
}
