package operations.searches.frontier;

import graph.*;

/****************************
Base class for frontier items.
*****************************/
abstract class FrontierItem
{
	private String name;
	private Node previousNode;

	public FrontierItem(String name, Node previousNode)
	{
		this.name = name;
		this.previousNode = previousNode;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setPrevious(Node previousNode)
	{
		this.previousNode = previousNode;
	}

	public Node getPrevious()
	{
		return previousNode;
	}
}