package se.kth.id1020.lab4;
import java.util.*;

/**
 *
 * @author Adrian
 */
public class Trie 
{
    private TrieNode root;
    
    public Trie()
    {
       root = new TrieNode();
    }
    
    public Trie(TrieNode root)
    {
        this.root = root;
    }
    
    /**
     * Inserts k into the structure.  
     * If k was not associated with any value v before, associate it with 1,
     * otherwise with v+1 where v was the old value.
     * 
     * @param key  a key k to be inputted in the structure
     */
    public void Put(String key)
    {
        int index;
        TrieNode current;
      
        index = ASCIIConverter(key.charAt(0));
           
         if(root.links[index] == null)
         {
             root.links[index] = new TrieNode();
             current = root.links[index];
         }
         
         else
            current = root.links[index]; 
           
         
         for(int i = 1; i < key.length(); i++)
         {
             
             index = ASCIIConverter(key.charAt(i));
             
             if(current.links[index] == null)
             {
                 current.links[index] = new TrieNode();
                 current = current.links[index];
             }
             
             else
                 current = current.links[index];
             
         }
         
         current.value++;  
         current.key = key;
           
    }
    
    /**
     * Returns the associated value of the inputed key.
     * 
     * @param key a key k 
     * @return  the associated value for k or 0 if no value is associated. 
     */
    public int Get(String key)
    {
        int index;
        TrieNode current;
        int returnval = 0;
      
        index = ASCIIConverter(key.charAt(0));
           
         if(root.links[index] == null)
               return 0;
         
         else
            current = root.links[index]; 
           
         
         for(int i = 1; i < key.length(); i++)
         {
             
             index = ASCIIConverter(key.charAt(i));
             
             if(current.links[index] == null)
                return 0;
             
             else
                 current = current.links[index];
           
         }
         
         return current.value;
    }
    
    /**
     * Counts the number of words associated with a prefix k.
     * 
     * @param key
     * @return the sum of all associated values of the sub-tree starting at k.
     */
    public int Count(String key)
    {
        int index;
        TrieNode current;
      
        index = ASCIIConverter(key.charAt(0));
           
         if(root.links[index] == null)
               return 0;
         
         else
            current = root.links[index]; 
           
         
         for(int i = 1; i < key.length(); i++)
         { 
             index = this.ASCIIConverter(key.charAt(i));
             
             if(current.links[index] == null)
                return 0;
             
             else
                 current = current.links[index];     
         }
        
        return helpCount(current) + current.value;
         
    }
    
    /**
     * Counts the number of distinct keys associated with a prefix.
     * 
     * @param key a prefix
     * @return the number of distinct keys that have associated values on he sub-tree starting at k.
     */
    public int Distinct(String key)
    {
        int index;
        TrieNode current;
      
        index = this.ASCIIConverter(key.charAt(0));
           
         if(root.links[index] == null)
               return 0;
         
         else
            current = root.links[index]; 
           
         
         for(int i = 1; i < key.length(); i++)
         { 
             index = this.ASCIIConverter(key.charAt(i));
             
             if(current.links[index] == null)
                return 0;
             
             else
                 current = current.links[index];     
         }
        
        return helpDistinct(current);
    }
   
    /**
     * Recursively explores all of the associated nodes to a given node, and then
     * sums the values for the given nodes.
     * 
     * @param current The current TrieNode to sum the values of the linked nodes for.
     * @return the sum of all associaited values of the given TrieNode.
     */
    public int helpCount(TrieNode current)
    {
        int count = 0;
        for(int i = 0; i < current.links.length; i++)
        {
           if(current.links[i] != null)
           {
               count += current.links[i].value;
               count += helpCount(current.links[i]);
           }          
        }
        
        return count;
    }
    
    /**
     * Recursively explores all of the associated nodes to a given node, and then
     * adds +1 for each node that contains a value greater than zero.
     * 
     * @param current The current TrieNode to sum the values of the linked nodes for.
     * @return the number of distinct keys associated with a given TrieNode.
     */   
    public int helpDistinct(TrieNode current)
    {
        int distinct = 0;
        for(int i = 0; i < current.links.length; i++)
        {
           if(current.links[i] != null)
           {
               if(current.links[i].value > 0)
                   distinct++;
                   
              distinct += helpDistinct(current.links[i]);
              
           }
               
        }
        
        return distinct;
    }
    
    /**
     * Converts ASCII values to an array index appropriate for the TrieNode class.
     * 
     * @param value a given char value,
     * @return an int value to be used as an index.
     */
    public int ASCIIConverter(char value)
    {
        if(value >= 97 && value <= 122)
            return value - 97;
            
        else
            return value - 65;
            
    }
    
    /**
     * Compiles all of the keys associated with a given prefix in the Trie in an iterator.
     * 
     * @param key a prefix k for all keys in the entries of the iterator.
     * @return an iterator that goes trough objects of type Map.Entry<String,Integer> 
     */
    public Iterator <Map.Entry <String, Integer>> Iterator (String key)
    {
        TreeMap<String, Integer> map = new TreeMap<>();
        
        if(key == "")
           convertTrieToMap(root, map);
            
        else
        {  
           TrieNode current = root;
           int index = ASCIIConverter(key.charAt(0));
           current = root.links[index]; 
           
         for(int i = 1; i < key.length(); i++)
         {
             index = ASCIIConverter(key.charAt(i));
             current = current.links[index];
         }
   
            convertTrieToMap(current, map);
        }
 
        Iterator<Map.Entry <String, Integer>> mapIterator = map.entrySet().iterator(); 
        
        return mapIterator;
    }
    
    /**
     * Recursively explores all of the associated nodes to a given node, and then
     * inputs the the nodes to a map.
     * 
     * @param current The key and value of the TrieNode to be input in the map.
     * @param map The map to input the value and the key of the TrieNode to.
     */
    public void convertTrieToMap(TrieNode current, Map<String, Integer> map)
    {
        for(int i = 0; i < current.links.length; i++)
        {
            if (current.links[i] != null)
            {
                if(current.links[i].key != null)
                {
                    map.put(current.links[i].key, current.links[i].value);
                }
                
                convertTrieToMap(current.links[i], map);
            }
        }
    }

}

