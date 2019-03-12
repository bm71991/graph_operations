package operations.searches.frontier;

import java.util.Comparator;

/***********************************************
Compares two FrontierItem objects, where the 
FrontierItem with a smaller f(n) is 'less than'
the other. Passed to the FrontierPriorityQueue 
constructor.
************************************************/
public class HeuristicFrontierItemComparator implements Comparator<HeuristicFrontierItem>
{
	public int compare(HeuristicFrontierItem firstItem, HeuristicFrontierItem secondItem)
	{
		double estSolutionDifference = firstItem.getEstSolutionCost() - secondItem.getEstSolutionCost();

		if (estSolutionDifference < 0)
			return -1;
		else if (estSolutionDifference == 0)
			return 0;
		else 
			return 1; 
	}
}