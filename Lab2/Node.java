package se.kth.lab2;

/**
 *
 * @author Adrian
 */
public class Node implements Comparable <Node>
{
    private int value;
    private Node next;

    public Node(int i)
    {
       value = i;
    }

    public void setNode(Node node)
    {
        this.next = node;
    }

    public Node getNextNode()
    {
        return next;
    }

    /**
     * Converts the object to a string representation.
     *
     * @return The strings representation of a node object.
     */
    public String toString()
    {
       StringBuilder sb = new StringBuilder();
       if(next == null)
          sb.append("<" + value + ">");
       else
          sb.append(value + " -->" + next.value);

       return sb.toString();
    }

    /**
     * Compares two nodes with each other.
     *
     * @param other The node to compare with.
     * @return (-0) if equal, (1) if greater and (-1) if smaller
     */
    public int compareTo(Node other)
    {
        if(value == other.value)
            return 0;

        return this.value > other.value ? 1 : -1;
    }

    /**
     * Swaps the values of the adjacent pairs
     */
    public void Swap()
    {
        int temp = value;
        value = next.value;
        next.value = temp;
    }

    /**
     * Returns the amount of nodes in the linked list.
     *
     * @param currNode the head of the linked list
     * @return
     */
    public static int size(Node currNode)
    {
       int count = 0;
       while (currNode!= null)
       {
          count++;
          currNode = currNode.getNextNode();
       }

        return count;
    }

    /**
     * Prints all of the nodes in the linked list from top to bottom.
     * @param currNode
     */
    public static void printLinkedList(Node currNode)
    {
       while(currNode!= null)
       {
          System.out.print(currNode + " ");
          currNode=currNode.getNextNode();
       }

       System.out.println("");
    }

}
