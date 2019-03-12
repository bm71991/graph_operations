package operations.euler_cycle;

import graph.*;
import java.util.ArrayList;

/****************************************************
Lists the cycle of nodes from the origin in which 
every edge in the graph is traversed exactly once.
***************************************************/
public class Hierholzer
{
	public static void getEulerCycle(Graph g, Node origin)
	{
		if (!g.hasEulerCycle())
			throw new NoEulerCycleException(g.isConnected(), 
				                              g.isEveryNodeEvenDegree());

		ArrayList<String> eulerCycle = getCycle(origin); // get first cycle.

		// for every node in the current cycle
		for (int index = 0; index < eulerCycle.size(); index++)
		{
			Node currentNode = g.getNode(eulerCycle.get(index));
			ArrayList<Edge> unvisitedEdges = currentNode.getUnvisitedEdges();

			while (!unvisitedEdges.isEmpty())
			{
				ArrayList<String> newCycle = getCycle(currentNode);
				newCycle.remove(newCycle.size() - 1); // remove the last item in the cycle.
				eulerCycle.addAll(index, newCycle);
			}
		}
		
		for (String nodeName : eulerCycle)
			System.out.println(nodeName);
	}


	private static ArrayList<String> getCycle(Node origin)
	{
		ArrayList<String> cycle = new ArrayList<>();
		boolean cycleFound = false;

		Node currentNode = origin;
		ArrayList<Edge> currentEdges = currentNode.getUnvisitedEdges();

		cycle.add(currentNode.getName());

		while (!cycleFound)
		{
			Edge currentEdge = currentEdges.get(0);
			Node nextNode = currentEdge.getDest();
			ArrayList<Edge> nextEdges = nextNode.getUnvisitedEdges();

			if (nextNode.equals(origin))
				cycleFound = true;

			cycle.add(nextNode.getName()); 

			currentEdges.remove(0);

			boolean edgeRemoved = false;
			for (int index = 0; index < nextEdges.size() && !edgeRemoved; index++)
			{
				if (nextEdges.get(index).getDest().equals(currentNode))
				{
					nextEdges.remove(index);
					edgeRemoved = true;
				}
			}

			currentNode = nextNode;
			currentEdges = nextEdges;
		}
		return cycle;
	}
}