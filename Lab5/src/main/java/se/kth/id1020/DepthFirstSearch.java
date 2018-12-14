package se.kth.id1020;
import java.util.*;

/**
 *
 * @author Adrian 
 */
public class DepthFirstSearch
{
    private boolean[] marked;
    int subgraphs;
    
    /**
     * Visits all the vertices in the graph and counts the number of subgraphs. 
     * 
     * 
     * @param G a graph.
     * @param s id for the starting vertex.
     */
    public DepthFirstSearch(Graph G, int s)
    {
        marked = new boolean[G.numberOfVertices()];
      
        for(int i = 0; i < marked.length; i++)
        {
            if(marked[i] == false)
            {
                dfs(G,i);
                subgraphs++;
            }
        }
    }
    
    /**
     * Depth first search implementation for a graph.
     * Recursively visits all related vertices to vertice of id v.
     * Every visited vertex is marked as visited.
     * 
     * @param G a graph 
     * @param v id for the vertex v.
     */
    private void dfs(Graph G, int v)
    {
       marked[v] = true;;
       
       Iterable iterable = G.adj(v);
       Iterator it = iterable.iterator();
       int w;
       
       while(it.hasNext())
       {
          Edge current = (Edge)it.next();
          w = current.to;
          
          if(!marked[w])
              dfs(G,w);
       }   
    }
    
    /**
     * Returns true or false whether vertex w has been visited.
     * 
     * @param w id of vertex w
     * @return if vertex w has been visited or not
     */
    public boolean marked(int w)
    {
        return marked[w];
    }
    
    /**
     * Returns the number of subgraphs.
     * 
     * @return the number of subgraphs.
     */
    public int subgraphs()
    {
        return subgraphs;
    }
}
