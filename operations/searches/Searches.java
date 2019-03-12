package operations.searches;

import graph.*;
import operations.searches.frontier.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.Comparator;


/***********************************************************************
Contains static methods for breadth-first, uniform cost,
pre-order depth first, and A* search algorithms.
***********************************************************************/
public class Searches
{
	public static boolean breadthFirst(Graph g, String start, String goal)
	{
		/******************************************
		Initialize the frontier queue and explored
		set.
		******************************************/
		ArrayQueue<Node> frontier = new ArrayQueue<>();
		HashSet<String> explored = new HashSet<>();
		boolean goalFound = false;

		if (start.equals(goal))
		{
			System.out.println("PATH:\n" + start);
			System.out.println("COST: 0");
			goalFound = true;
			return goalFound;
		}
		
		/**************************
		Add the start node to the 
		frontier.
		*************************/
		Node startNode = g.getNode(start);
		startNode.setPrevious(null);
		frontier.enqueue(startNode);


		while (!goalFound && !frontier.isEmpty())
		{
			/***********************************************
			Get the next node in the frontier queue, add it
			to the explored set, and get a list of its edges.
			***********************************************/
			Node currentNode = frontier.dequeue();
			explored.add(currentNode.getName());
			ArrayList<Edge> currentEdges = currentNode.getEdges();

			/**************************************************
			Iterate through all of the edges in the list.
			**************************************************/
			if (!currentEdges.isEmpty())
			{
				for (Edge currentEdge : currentEdges)
				{
					Node successor = currentEdge.getDest();

					/****************************************************
					If the destination of the edge is not in the explored
					set and the frontier, check if it is the goal node.
					If it is not, add it to the frontier.
					****************************************************/
					if ( !explored.contains(successor.getName()) &&
							 !frontier.contains(successor) )
					{
						if (successor.getName().equals(goal))
						{
							goalFound = true;
							successor.setPrevious(currentNode);
							printResults(g.getNode(goal), goalFound);
							return goalFound;
						}
						else
						{
							successor.setPrevious(currentNode);
							frontier.enqueue(successor);
						}
					}
				}
			}
		}
		printResults(g.getNode(goal), goalFound);
		return goalFound;
	}


	/*********************************************************************
	Iterative depth-first search which does a preorder traversal of the
	graph to find a target node. Nodes are checked as to whether they 
	are the target node when they are added to the frontier.
	*********************************************************************/
	public static boolean preorderDFS(Graph g, String start, String goal)
	{
		Stack<Node> frontier = new Stack<>();
		HashSet<String> visited = new HashSet<>();
		Node predecessor = null;
		boolean goalFound = false;
		Node startNode = g.getNode(start);

		frontier.push(startNode); // push the start node onto the stack.

		while (!frontier.isEmpty())
		{
			Node currentNode = frontier.pop();

			// if the node popped from the stack has not been processed already:
			if ( !visited.contains(currentNode.getName()) )
			{
				visited.add(currentNode.getName()); // put currentNode in visited
				// currentNode is the predecessor to the nodes being added to the frontier.
				predecessor = currentNode;

				for (Edge incidentEdge : currentNode.getEdges())
				{
					Node destNode = incidentEdge.getDest();
					String destNodeName = destNode.getName();

					/* if the predecessor is the start node OR the destination node is
					   not equal to the previously processed node. */
					if ( !visited.contains(destNodeName) ) 
					{
						// set destNode's 'previous' field to the predecessor node.
						destNode.setPrevious(predecessor); 

						// if goal found:
						if (destNodeName.equals(goal))
						{
							goalFound = true;
							printResults(destNode, goalFound);
							return goalFound;
						}
						else
							frontier.push(destNode);
					}
				}
			}
		}
		System.out.println("Goal was not found.");
		return goalFound;
	}


	public static boolean uniformCost(Graph g, String start, String goal)
	{
		boolean goalFound = false;
		Comparator<PathCostItem> ordering = new PathCostItemComparator();
		FrontierPriorityQueue<PathCostItem> frontier = new FrontierPriorityQueue<>(ordering);
		HashSet<String> explored = new HashSet<>();

		PathCostItem firstFrontierItem = new PathCostItem(start, null, 0); 
		frontier.enqueue(firstFrontierItem);

		if (g.getNode(start) == null)
		{
			System.out.println("The start node specified does not exist on the graph.");
			return goalFound;
		}
		else if (g.getNode(goal) == null)
		{
			System.out.println("The goal node specified does not exist on the graph.");
			return goalFound;
		}

		while (!goalFound && !frontier.isEmpty())
		{
			/***********************************************
			Processes the node specified by the top priority
			item in the priority queue.
			***********************************************/
			PathCostItem itemToProcess = frontier.dequeue();
			Node currentNode = g.getNode(itemToProcess.getName()); //gets current node to process

			//sets current node's previous field to Node specified in itemToProcess
			currentNode.setPrevious(itemToProcess.getPrevious());
			//sets current node's costToReach to that specified in itemToProcess 
			currentNode.setCostToReach(itemToProcess.getCostToReach()); 
			String currentNodeName = currentNode.getName();
			//put processed node into explored set.
			explored.add(currentNodeName);	

			/*************************************************
			If the current node is the goal, prints the path
			and cost from the start to the current node plus
			returns true.
			*************************************************/
			if (currentNode.getName().equals(goal))
			{
				goalFound = true;
				printPath(g.getNode(goal), goalFound);
				System.out.println("COST: " + currentNode.getCostToReach());
				return goalFound;
			}
			else
			{
				/****************************************************
				Iterate through each edge of the processed node and 
				determine whether to add it to the priority queue.
				****************************************************/
				ArrayList<Edge> currentEdges = currentNode.getEdges();

				for (Edge currentEdge : currentEdges)
				{
					Node adjacentNode = currentEdge.getDest();
					// cost from start to adjacentNode
					double costToReach = currentNode.getCostToReach() + currentEdge.getWeight(); 
					
					PathCostItem newFrontierItem = new PathCostItem(adjacentNode.getName(), // name
																													currentNode,            // reference to previous node
																												  costToReach);

					String newFrontierItemName = newFrontierItem.getName();
					/**********************************************************
					If the node the frontier item references is not in 
					the frontier and not in the explored set, add it to
					the frontier. 
					***********************************************************/
					if (!frontier.contains(newFrontierItemName) && !explored.contains(newFrontierItemName))
						frontier.enqueue(newFrontierItem);
					/***********************************************************
					Else if the node the new frontier item references
					is in the frontier with a higher costToReach, then replace the current
					frontier item with the newer item.
					************************************************************/
					else if (frontier.contains(newFrontierItemName))
					{
						// get the frontier item that references the same node currently in the frontier.
						PathCostItem currentFrontierItem = frontier.get(newFrontierItemName); 
						
						//if (newFrontierItem.getCostToReach() < currentFrontierItem.getCostToReach())
						if (ordering.compare(newFrontierItem, currentFrontierItem) < 0)
						{
							//replacement of the old frontierItem in the frontier with the new FrontierItem
							frontier.remove(newFrontierItemName);
							frontier.enqueue(newFrontierItem);
						}
					}
				}
			}
		}
		return goalFound;
	}


	public static boolean aStar(Graph g, String start, String goal)
	{
		/*****************************************
		Place start node into the priority queue.
		*****************************************/
		boolean goalFound = false;
		Comparator<HeuristicFrontierItem> ordering = new HeuristicFrontierItemComparator();
		FrontierPriorityQueue<HeuristicFrontierItem> frontier = new FrontierPriorityQueue<>(ordering);
		HashSet<String> explored = new HashSet<>();
		//The start node has no previous, cost to reach is 0 and estCostToGoal is irrelevant
		HeuristicFrontierItem firstFrontierItem = new HeuristicFrontierItem(start, null, 0, 0); 
		frontier.enqueue(firstFrontierItem);

		if (g.getNode(start) == null)
		{
			System.out.println("The start node specified does not exist on the graph.");
			return goalFound;
		}
		else if (g.getNode(goal) == null)
		{
			System.out.println("The goal node specified does not exist on the graph.");
			return goalFound;
		}

		while (!goalFound && !frontier.isEmpty())
		{
			/***********************************************
			Processes the node specified by the top priority
			item in the priority queue.
			***********************************************/
			HeuristicFrontierItem itemToProcess = frontier.dequeue();
			Node currentNode = g.getNode(itemToProcess.getName()); //gets current node to process
			//sets current node's previous field to Node specified in itemToProcess
			currentNode.setPrevious(itemToProcess.getPrevious());
			//sets current node's costToReach to that specified in itemToProcess 
			currentNode.setCostToReach(itemToProcess.getCostToReach()); 
			String currentNodeName = currentNode.getName();
			//put processed node into explored set.
			explored.add(currentNodeName);	

			/*************************************************
			If the current node is the goal, prints the path
			and cost from the start to the current node plus
			returns true.
			*************************************************/
			if (currentNode.getName().equals(goal))
			{
				goalFound = true;
				printPath(g.getNode(goal), goalFound);
				System.out.println("COST: " + currentNode.getCostToReach());
				return goalFound;
			}
			else
			{
				/****************************************************
				Iterate through each edge of the processed node and 
				determine whether to add it to the priority queue.
				****************************************************/
				ArrayList<Edge> currentEdges = currentNode.getEdges();

				for (Edge currentEdge : currentEdges)
				{
					Node adjacentNode = currentEdge.getDest();
					Node goalNode = g.getNode(goal);
					// g(n): cost from start to adjacentNode
					double costToReach = currentNode.getCostToReach() + currentEdge.getWeight(); 
					// h(n): estimated straight line distance from adjacent node to goal
					double estCostToGoal = Edge.euclideanDistance(adjacentNode, goalNode);
					/* f(n) = h(n) + g(n) is calculated internally by the frontierItem constructor. */
					HeuristicFrontierItem newFrontierItem = new HeuristicFrontierItem(adjacentNode.getName(), // name
																																						currentNode, // reference to previous node
																																						costToReach,
																																						estCostToGoal);

					String newFrontierItemName = newFrontierItem.getName();
					/**********************************************************
					If the node the frontier item references is not in 
					the frontier and not in the explored set, add it to
					the frontier. 
					***********************************************************/
					if (!frontier.contains(newFrontierItemName) && !explored.contains(newFrontierItemName))
						frontier.enqueue(newFrontierItem);
					/***********************************************************
					Else if the node the new frontier item references
					is in the frontier with higher f(n), then replace the current
					frontier item with the newer item.
					************************************************************/
					else if (frontier.contains(newFrontierItemName))
					{
						// get the frontier item that references the same node currently in the frontier.
						HeuristicFrontierItem currentFrontierItem = frontier.get(newFrontierItemName); 
						
						//if (newFrontierItem.getEstSolutionCost() < currentFrontierItem.getEstSolutionCost())
						if (ordering.compare(newFrontierItem, currentFrontierItem) < 0)
						{
							//replacement of the old frontierItem in the frontier with the new FrontierItem
							frontier.remove(newFrontierItemName);
							frontier.enqueue(newFrontierItem);
						}
					}
				}
			}
		}
		return goalFound;
	}


	private static void printResults(Node goal, boolean goalFound)
	{
		printPath(goal, goalFound);
		printCost(goal, goalFound);
	}

	/***********************************************************************************
	printPath initially pushes the goal node onto the stack. It then 
	pushes the previous node of the path onto the stack, and then its 
	previous node, etc. When this is finished, the top of the stack is the initial node, 
	so when all of the items on the stack are popped, it prints the path in order.
	***********************************************************************************/
	private static void printPath(Node goal, boolean goalFound)
	{
		Stack<String> path = new Stack<>();
		Node currentNode = goal;

		if (goalFound == true)
		{	
			while (currentNode != null)
			{
				path.push(currentNode.getName());
				currentNode = currentNode.getPrevious();
			}

			System.out.println("PATH:");
			while (!path.isEmpty())
			System.out.println(path.pop());
	  }
	  else 
	  	System.out.println("No path since goal was not found");
	}

	/*****************************************************************************************
	Calculates the straight-line distance cost between each node and its previous on the path 
	while iteratively adding the distance to the costOfPath field.
	*****************************************************************************************/
	private static void printCost(Node goal, boolean goalFound)
	{
		Node currentNode = goal;
		double costOfPath = 0;

		if (goalFound == true)
		{
			while (currentNode != null)
			{
				if (currentNode.getPrevious() != null)
					costOfPath += Edge.euclideanDistance(currentNode, currentNode.getPrevious());

				currentNode = currentNode.getPrevious();
			}
			System.out.println("PATH COST: " + costOfPath);
		}
		else
			System.out.println("No cost since the goal was not found");
	}
}
