package se.kth.lab2;
import java.util.Scanner;

/**
 *
 * @author Adrian
 */
public class BubbleSort
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("How many nodes do you want in your linked list?");

        int n = sc.nextInt();

        Node[] allnodes = new Node[n];
        Node[] allNodesInversion = new Node[n];

        for(int i = 0; i < n; i++)
        {
            System.out.println("Please enter the item (value) of node nr: " + (i+1));
            int value =  sc.nextInt();
            allnodes[i] = new Node(value);
            allNodesInversion[i] = new Node(value);
        }

        for(int i = 0; i < allnodes.length - 1; i++)
        {
            allnodes[i].setNode(allnodes[i+1]);
            allNodesInversion[i].setNode(allNodesInversion[i+1]);
        }

        System.out.println("----------------------------------------------");
        System.out.println("BEFORE SORT");
        Node.printLinkedList(allnodes[0]);
        System.out.println("----------------------------------------------");

        System.out.println("----------------------------------------------");
        System.out.println("AFTER SORT");
        Node sorted = BubbleSort(allnodes[0]);
        Node.printLinkedList(sorted);
        System.out.println("----------------------------------------------");

        System.out.println("Number of Inversions: "+ Inversion(allNodesInversion[0]));

    }

    /**
     * Sorts a linked list with the bubblesort algorithm.
     *
     * @param a The head of the linked list
     * @return The head of the linked list, now sorted from smallest to largest
     */
    public static Node BubbleSort(Node a)
    {
        int R = Node.size(a) - 2;
        boolean swapped = true;
        Node currentNode = a;
        Node nextNode;
        int numberOfSwaps = 0;

        while(R >= 0 && swapped == true)
        {
            swapped = false;
            for(int i = 0; i <= R; i++)
            {
                nextNode = currentNode.getNextNode();
                if(currentNode.compareTo(nextNode) == 1)
                {
                    currentNode.Swap();
                    swapped = true;
                    numberOfSwaps++;
                }

                currentNode = nextNode;
            }

            currentNode = a;
            R = R - 1;
        }

        System.out.println("Number of swaps: " + numberOfSwaps);
        return a;
    }

    /**
     * Counts the number of inversions present in the linked lists.
     *
     * @param a The head of a linked list.
     * @return The number of inversions in the linked list.
     */
    public static int Inversion(Node a)
    {
        int length = Node.size(a);
        Node currentNode = a;
        Node nextNode = a.getNextNode();
        int numberOfInversions = 0;

        for(int i = 0; i < length; i++)
        {
            if(i >= 1)
            {
                if(currentNode.getNextNode() == null)
                    break;

                else
                {
                    currentNode = currentNode.getNextNode();
                    nextNode = currentNode.getNextNode();
                }
            }

            for(int j = i + 1; j < length; j++)
            {
                if(currentNode.compareTo(nextNode) == 1)
                {
                    numberOfInversions++;
                }

                nextNode = nextNode.getNextNode();
            }
        }

        return numberOfInversions;
    }
}
