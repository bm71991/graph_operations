package graph;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Stack;

/********************************************
A simple and undirected graph in which 
every node has a x-y coordinate. Edge weights
are assigned automatically by finding the 
straight-line distance between the incident
nodes.
********************************************/
public class Graph
{
	HashMap<String, Node> graph;

	public Graph()
	{
		graph = new HashMap<>();
	}

	public void addNode(double xAxis, double yAxis, String name)
	{
		Node newNode = new Node(xAxis, yAxis, name);

		for (Node node : graph.values())
		{
			// Repeated name check.
			if (node.getName().equals(name))
				throw new RepeatedNameException();
			// Repeated coordinates check.
			else if ( newNode.getLocation().equals(node.getLocation()) )
				throw new RepeatedLocationException();
		}

		graph.put(name, newNode);
	}

	/*********************************************
	Adds an undirected edge (2 directed edges) 
	between two nodes. Increments the degrees of the 
	nodes incident on the edges.
	**********************************************/
	public void connect(String node1, String node2)
	{
		Node firstNode = graph.get(node1);
		Node secondNode = graph.get(node2);

		// Loop check.
		if (node1.equals(node2))
			throw new LoopException();

		// Parallel edge check.
		for (Edge edge : firstNode.getEdges())
		{
			if (edge.getDest().getName().equals(node2))
				throw new ParallelEdgesException();
		}

		firstNode.addEdge(secondNode);
		secondNode.addEdge(firstNode);

		firstNode.incrementDegree();
		secondNode.incrementDegree();
	}

	/************************************************************
	Removes an undirected edge incident on the two nodes passed as
  arguments. Internally, it removes directed edge (node1, node2) 
	from the first node's edge list and removes the directed edge
	(node2, node1) from the second node's edge list. Decrements 
	the degree of both nodes by 1.
	************************************************************/
	public void removeEdge(String node1, String node2)
	{
		Node firstNode = graph.get(node1);
		Node secondNode = graph.get(node2);

		firstNode.removeEdge(node2);
		secondNode.removeEdge(node1);

		firstNode.decrementDegree();
		secondNode.decrementDegree();

		// System.out.println(node1 + " edges");
		// for (Edge edge : firstNode.getEdges())
		// 	System.out.println(edge.getDest().getName());

  //   System.out.println();

  //   System.out.println(node2 + " edges");
		// for (Edge edge : secondNode.getEdges())
		// 	System.out.println(edge.getDest().getName());
	}

	public Node getNode(String nodeName)
	{
		return graph.get(nodeName);
	}

	public boolean hasEulerCycle()
	{
		return isEveryNodeEvenDegree() && isConnected();
	}

	public boolean isEveryNodeEvenDegree()
	{
		boolean allNodesAreEven = true;

		for (Node aNode : graph.values())
		{
			if ((aNode.getDegree() % 2) != 0)
			{
				// System.out.println("Node " + aNode.getName() + " has degree " + aNode.getDegree());
				allNodesAreEven = false;
				return allNodesAreEven;
			}
		}

		return allNodesAreEven;
	}

	/******************************************************************
	Returns a boolean as to whether or not the graph is connected.
	******************************************************************/
	public boolean isConnected()
	{
		boolean connected = true;

		// a String list of all of the node names in the entire graph.
		ArrayList<String> allNodesInGraph = getNodeList();

		// a String list of all of the nodes (names) that can be reached from a certain node in the graph.
		ArrayList<String> nodesInComponent = depthFirstTraversal(allNodesInGraph.get(0));

		/* Loop through all of the names of nodes included in the entire graph:
		   if there is a node in the graph that is not
		   in the component containing allNodesInGraph.get(0),
		   then the graph is not connected.
		*/
		for (String nodeName : allNodesInGraph)
		{
			if (!nodesInComponent.contains(nodeName))
				connected = false;
		}
		return connected;
	}

	/*********************************************
	Returns a list of all of the node names in the
	graph.
	*********************************************/
	private ArrayList<String> getNodeList()
	{
		ArrayList<String> nodeList = new ArrayList<>();

		for (String nodeName : graph.keySet())
			nodeList.add(nodeName);

		return nodeList;
	}

	private ArrayList<String> depthFirstTraversal(String origin)
	{
		Stack<Node> frontier = new Stack<>();
		ArrayList<String> visited = new ArrayList<>();

		Node originNode = getNode(origin);

		frontier.push(originNode); // push the start node onto the stack.

		while (!frontier.isEmpty())
		{
			Node currentNode = frontier.pop();

			// if the node popped from the stack has not been processed already:
			if ( !visited.contains(currentNode.getName()) )
			{
				visited.add(currentNode.getName()); // add currentNode to visited

				for (Edge incidentEdge : currentNode.getEdges())
				{
					Node destNode = incidentEdge.getDest();
					String destNodeName = destNode.getName();

					/* If the destination node is not equal 
					   to a previously processed node: */
					if ( !visited.contains(destNodeName) ) 
							frontier.push(destNode);
				}
			}
		}
		return visited;
	}
}