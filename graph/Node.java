package graph;

import java.util.ArrayList;

/**************************************
Node object stores the state of a node
by using the inner class State, which 
provides its name, location, and cost 
to reach. It also allows for the creation
of edges by keeping a list of Edge objects
as a data field.
***************************************/

public class Node
{
	private ArrayList<Edge> edges;
	private ArrayList<Edge> unvisitedEdges;
	private int degree;
	private State nodeState;
	private Node previousNode;

	Node(double xAxis, double yAxis, String name)
	{
		edges = new ArrayList<>();
		unvisitedEdges = new ArrayList<>();

		nodeState = new State(xAxis, yAxis, name);
		degree = 0;
		previousNode = null;
	}
	
	public Coordinates getLocation()
	{
		return nodeState.location;
	}
	
	public String getName()
	{
		return nodeState.name;
	}

	public void addEdge(Node dest)
	{
		Edge newEdge = new Edge(this, dest);
		edges.add(newEdge);
		unvisitedEdges.add(newEdge);
	}

	void incrementDegree()
	{
		degree++;
	}

	void decrementDegree()
	{
		degree--;
	}

	/************************************************
	Removes directed edge which is the ordered pair
	(calling node, destNodeName).
	************************************************/ 
	void removeEdge(String destNodeName)
	{
		for (int index = 0; index < edges.size(); index++)
		{
			if (edges.get(index).getDest().getName().equals(destNodeName))
				edges.remove(index);
		}
	}

	public int getDegree()
	{
		return degree;
	}

	public ArrayList<Edge> getEdges()
	{
		return edges;
	}

	public ArrayList<Edge> getUnvisitedEdges()
	{
		return unvisitedEdges;
	}

	public void setPrevious(Node previousNode)
	{
		this.previousNode = previousNode;
	}

	public Node getPrevious()
	{
		return previousNode;
	}

	public void setCostToReach(double cost)
	{
		nodeState.costToReach = cost;
	}

	public double getCostToReach()
	{
		return nodeState.costToReach;
	}

  public boolean equals(Object anObject) {
  if (anObject == this) {
    return true;
  }
  if (!(anObject instanceof Node)) {
    return false;
  }
  Node aNode = (Node)anObject;
  
  return aNode.getName().equals(this.nodeState.name) &&
  			 aNode.getLocation().equals(this.nodeState.location);
  }

	private class State 
	{
		private Coordinates location;
		private String name;
		private double costToReach;

		private State(double xAxis, double yAxis, String name)
		{
			location = new Coordinates(xAxis, yAxis);
			this.name = name;
			costToReach = -1;
		}
	}
}