package operations.searches.frontier;

import graph.*;

public class PathCostItem extends FrontierItem
{
	private double costToReach;

	public PathCostItem(String name, 
			    Node previousNode,
			    double costToReach)
	{
		super(name, previousNode);
		this.costToReach = costToReach;
	}

	public void setCostToReach(double cost)
	{
		costToReach = cost;
	}

	public double getCostToReach()
	{
		return costToReach;
	}
}
