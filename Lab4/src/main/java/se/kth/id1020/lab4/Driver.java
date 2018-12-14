package se.kth.id1020.lab4;
import java.util.*;
import edu.princeton.cs.introcs.In;
import java.net.URL;

/**
 *
 * @author Adrian
 */
public class Driver 
{
    public static void main(String[] args) 
    {
        
      URL url = ClassLoader.getSystemResource("kap1.txt");
      Trie wordsTrie = new Trie();

      if (url != null) {
         System.out.println("Reading from: " + url);
      } else {
         System.out.println("Couldn't find file: kap1.txt");
      }

      In input = new In(url);

      while (!input.isEmpty()) {
         String line = input.readLine().trim();
         String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
         String lastOfLine = words[words.length - 1];

         if (lastOfLine.endsWith(".")) {
            words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
         }
         
         for (String word : words) {
            String word2 = word.replaceAll("\"|\\(|\\)|\\'|\\-|\\.|\\_|\\*", "");
            String word3 = word2.replaceAll("\"|\\é", "e");
            String word4 = word3.replaceAll("\"0|1|2|3|4|5|6|7|8|9", "");

            if (word4.isEmpty()) {
               continue;
            }

            System.out.println(word4);
            // Add the word to the trie
            wordsTrie.Put(word4.toLowerCase());
         }
      }
      
      //Perform analysis
      System.out.println("");
      System.out.println("it:" + wordsTrie.Count("it"));
      System.out.println("th:" +wordsTrie.Count("th"));
      System.out.println("if:" +wordsTrie.Count("if"));
      System.out.println("an:" +wordsTrie.Count("an"));
      System.out.println("");
     
      System.out.println("i:" +wordsTrie.Distinct("I"));
      System.out.println("s:" +wordsTrie.Distinct("S"));
      System.out.println("a:" +wordsTrie.Distinct("A"));
      System.out.println("t:" +wordsTrie.Distinct("T"));
      System.out.println("");
      
      Iterator it = wordsTrie.Iterator("");
      List<Map.Entry<String,Integer>> wordsList = new ArrayList<Map.Entry<String,Integer>>();
      List<Map.Entry<String,Integer>> wordsList2 = new ArrayList<Map.Entry<String,Integer>>();
      
      while(it.hasNext())
      {
         Map.Entry<String,Integer> Entry = (Map.Entry)it.next();
         wordsList.add(Entry);
         wordsList2.add(Entry);
      }
      
      int currentLargestIndex = 0;
      int maxValue = 0;  
      
      for(int j = 0; j < 10; j++)
      {
        maxValue = 0;
        for(int i = 0; i < wordsList.size(); i++)
        {
            Map.Entry<String,Integer> Entry  = wordsList.get(i);
            if(Entry.getValue() > maxValue)
            {
                maxValue = Entry.getValue();
                currentLargestIndex = i;
            }
        }
         
         System.out.println(j+1 + ": " + wordsList.get(currentLargestIndex));
         wordsList.remove(currentLargestIndex);
      }
      
      System.out.println();
      int currentSmallestIndex = 0;
      int minValue = Integer.MAX_VALUE;
      
      for(int j = 0; j < 10; j++)
      {   
          
        minValue = Integer.MAX_VALUE;
        for(int i = 0; i < wordsList2.size(); i++)
        {
            Map.Entry<String,Integer> Entry  = wordsList2.get(i);
            if(Entry.getValue() < minValue)
            {
                minValue = Entry.getValue();
                currentSmallestIndex = i;
            }
        }
       
        System.out.println(j+1 + ": " + wordsList2.get(currentSmallestIndex));
        wordsList2.remove(currentSmallestIndex);
        
      }

   }

}
