package se.kth.id1020;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.Stack;

/**
 *
 * @author Adrian
 */
public class Dijkstra 
{
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;
    
    /**
     * Sets all vertexes with a distance from s to infinity.
     * 
     * @param G a graph 
     * @param s id of starting vertex s
     */
    public Dijkstra(Graph G, int s, boolean withLengths)
    {
        edgeTo = new Edge[G.numberOfVertices()];
        distTo = new double[G.numberOfVertices()];
        
        pq = new IndexMinPQ<Double>(G.numberOfVertices());
        
        for(int v = 0; v < G.numberOfVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        
        distTo[s] = 0.0;
        
        pq.insert(s, 0.0);
        
        
        if(withLengths)
            while(!pq.isEmpty())
                relax(G, pq.delMin());
        
        else
            while(!pq.isEmpty())
                relaxWithoutLength(G, pq.delMin());  
    }

    /**
     * Test whether the best known way from s to w is to go from s to v, 
     * then take the edge from v to w, and, if so, 
     * update the data structures to indicate that to be the case.
     * 
     * @param G a graph
     * @param v 
     */
    private void relax(Graph G, int v) 
    {
        for(Edge e : G.adj(v))
        {
            int w = e.to;
            
            if(distTo[w] > distTo[v] + e.weight)
            {
                distTo[w] = distTo[v] + e.weight;
                edgeTo[w] = e;
                
                if(pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }
    
    /**
     * Test whether the best known way from s to w is to go from s to v, 
     * then take the edge from v to w, and, if so, 
     * update the data structures to indicate that to be the case.
     * 
     * @param G a graph
     * @param v 
     */
    private void relaxWithoutLength(Graph G, int v) 
    {
        for(Edge e : G.adj(v))
        {
            int length = 1;
            int w = e.to;
            
            if(distTo[w] > distTo[v] + length)
            {
                distTo[w] = distTo[v] + length;
                edgeTo[w] = e;
                
                if(pq.contains(w))
                    pq.changeKey(w, distTo[w]);
                
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }
    
    /**
     * Distance from vertex s to vertex v
     * 
     * @param v id of vertex v 
     * @return distance from s to v
     */
    public double distTo(int v)
    {
        return distTo[v];
    }
    
    /**
     * Returns true or false whether there is a path from source vertex s
     * to vertex v.
     * 
     * @param v vertex v
     * @return true if there is a path from s to v. Otherwise false.
     */
    public boolean hasPathTo(int v)
    {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    /**
     * The path from vertex s to v. Returns null if there is no path.
     * 
     * @param v id of vertex v
     * @return path from vertex s to v.
     */  
    public Iterable<Edge> pathTo(int v) 
    {
        if (!hasPathTo(v)) 
            return null;
        
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from]) 
        {
            path.push(e);
        }
        
        return path;
    }
    
    /**
     * Finds the id associated with a label.
     * 
     * @param G a graph.
     * @param label label for vertex v.
     * @return id associated with label
     */
    public static int helper(Graph G, String label)
    {
        int id = 0;
        for(Vertex x: G.vertices())
        {
            if(x.label.equals(label))
               id = x.id;
        }
        
        return id;
    }
}

