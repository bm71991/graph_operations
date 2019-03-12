package graph;

/**************************************
Edge objects are added to the edges field
in a Node object to represent edges of a
graph.
Records the destination of the edge, 
the weight (which is the Euclidean straight-line
distance between the two nodes) and contains 
the static method which calculates the 
Eucidean straight-line distance.
***************************************/

public class Edge
{
	private Node destination;
	private double weight;

	Edge(Node source, Node dest)
	{
		destination = dest;
		weight = euclideanDistance(source, destination);
	}

	public Node getDest()
	{
		return destination;
	}

	public double getWeight()
	{
		return weight;
	}

	public static double euclideanDistance(Node sourceNode, Node destNode)
	{

	Coordinates sourceLocation = sourceNode.getLocation();
	Coordinates destLocation = destNode.getLocation();

	double xAxisDifferenceSquared = Math.pow(sourceLocation.getxAxis() - destLocation.getxAxis(), 2.0);
	double yAxisDifferenceSquared = Math.pow(sourceLocation.getyAxis() - destLocation.getyAxis(), 2.0);

	return Math.sqrt(xAxisDifferenceSquared + yAxisDifferenceSquared);
	}
}