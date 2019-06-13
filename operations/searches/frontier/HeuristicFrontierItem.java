package operations.searches.frontier;

import graph.*;

/*************************************************************
Nodes adjacent to one being processed
by the A* method have their properties at
that moment in the search (most importantly
costToReach and previousNode) stored in a FrontierItem 
object.The constructor then calculates the evaluation function
value, which is then used in the A* method to see whether it 
should be added to the frontier (or replace an existing member
of the frontier) 
**************************************************************/
public class HeuristicFrontierItem extends FrontierItem
{
	private double costToReach; // g(n)
	private double estCostToGoal; // h(n)
	private double estSolutionCost; // f(n) = g(n) + h(n)

	public HeuristicFrontierItem(String name,
				     Node previousNode,
				     double costToReach,
				     double estCostToGoal)
	{
		super(name, previousNode);
		this.costToReach = costToReach;
		this.estCostToGoal = estCostToGoal;
	  //    f(n)      =  g(n)       +     h(n)
		estSolutionCost = costToReach + estCostToGoal;
	}

	public void setCostToReach(double cost)
	{
		costToReach = cost;
	}

	public double getCostToReach()
	{
		return costToReach;
	}

	public void setEstCostToGoal(double estCost)
	{
		estCostToGoal = estCost;
	}

	public double getEstCostToGoal()
	{
		return estCostToGoal;
	}

	public void setEstSolutionCost(double cost)
	{
		estSolutionCost = cost;
	}

	public double getEstSolutionCost()
	{
		return estSolutionCost;
	}
}
