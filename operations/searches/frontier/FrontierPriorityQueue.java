package operations.searches.frontier;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import graph.*;

/****************************************************************************************************
A priority queue which takes in FrontierItem objects (or subclasses thereof) and arranges the 
smalles objects at the front and the largest objects at the back. The comparison is 
made by the Comparator passed into the constructor. Internally the queue is stored as a linked list, 
while a hashmap is used for efficient implementation of the contains and get method.
****************************************************************************************************/
public class FrontierPriorityQueue<T extends FrontierItem>
{
	private LinkedList<T> priorityQueue;
	private HashMap<String, T> hashTable;
	private Comparator<T> ordering;


	public FrontierPriorityQueue(Comparator<T> comparison)
	{
		priorityQueue = new LinkedList<>();
	  hashTable = new HashMap<>();
	  ordering = comparison;
	}

	public T dequeue()
	{
		T itemToRemove = priorityQueue.removeFirst();
		hashTable.remove(itemToRemove.getName());

		return itemToRemove;
	}

	public void enqueue(T newFrontierItem)
	{
		priorityQueue.add(newFrontierItem);
		hashTable.put(newFrontierItem.getName(), newFrontierItem);


		Collections.sort(priorityQueue, ordering);
	}

	public boolean contains(String frontierItemName)
	{
		boolean result = false;

		if (hashTable.containsKey(frontierItemName))
			result = true;

		return result;
	}

	public T get(String frontierItemName)
	{
		return hashTable.get(frontierItemName);
	}

	public void remove(String frontierItemName)
	{
		T frontierItemToRemove = hashTable.get(frontierItemName);

		hashTable.remove(frontierItemName);
		priorityQueue.remove(frontierItemToRemove);
	}

	public boolean isEmpty()
	{
		return priorityQueue.size() == 0;
	}
}