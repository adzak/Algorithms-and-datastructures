package se.kth.id1020;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import java.util.*;

/**
 *
 * @author Adrian
 */
public class Paths 
{
    public static void main(String[] args) 
    {
       Graph g = DataSource.load();
       
       // Number of subgraphs
       DepthFirstSearch a = new DepthFirstSearch(g,0);
       System.out.println("The number of subgraphs in g: "+ a.subgraphs());
       System.out.println("");
       System.out.println("Shortest path from Renyn to Parses with minimal distance");
              
       // SHORTEST DISTANCE FROM RENYN -> PARSES 
       int vertexSource = Dijkstra.helper(g, "Renyn");
       int vertexDestination = Dijkstra.helper(g, "Parses");
       
       Dijkstra c = new Dijkstra(g,vertexSource,true);

       if(c.hasPathTo(vertexDestination))
           for(Edge e: c.pathTo(vertexDestination))
           {
               System.out.print(e.to + "-");
               
               if(e.from == vertexSource)
                   System.out.print(vertexSource);
           }
       
       System.out.println("");
       System.out.println("Shortest path from Renyn to Parses  with minimal edges");
       Dijkstra d = new Dijkstra(g,vertexSource,false);

       if(d.hasPathTo(vertexDestination))
           for(Edge e: d.pathTo(vertexDestination))
           {
               System.out.print(e.to + "-");
               
               if(e.from == vertexSource)
                   System.out.print(vertexSource);
           }
    }
    
}
