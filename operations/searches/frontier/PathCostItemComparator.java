package operations.searches.frontier;

import java.util.Comparator;

public class PathCostItemComparator implements Comparator<PathCostItem>
{
	public int compare(PathCostItem firstItem, PathCostItem secondItem)
	{
		double pathCostDifference = firstItem.getCostToReach() - secondItem.getCostToReach();

		if (pathCostDifference < 0)
			return -1;
		else if (pathCostDifference == 0)
			return 0;
		else
			return 1;
	}
}