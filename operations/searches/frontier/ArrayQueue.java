package operations.searches.frontier;

import java.util.ArrayDeque;
import graph.*;

/*****************************
Array Queue which is used to implement the frontier
for a breadth-first search. (An adapter class which
uses the operations of ArrayDeque).
****************************/
public class ArrayQueue<T>
{
	private ArrayDeque<T> arrayQueue = new ArrayDeque<>();

	public void enqueue(T element)
	{
		arrayQueue.add(element);
	}

	public T dequeue()
	{
		return arrayQueue.poll();
	}

	public boolean isEmpty()
	{
		return arrayQueue.size() == 0;
	}

	public boolean contains(T element)
	{
		return arrayQueue.contains(element);
	}
}
