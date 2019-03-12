package operations.euler_cycle;

public class NoEulerCycleException extends RuntimeException
{
	public NoEulerCycleException(boolean isConnected, boolean everyNodeisEvenDegree)
	{
		super("No Euler cycle exists for this graph: " +
		       "Graph is connected: " + isConnected +
		       ".\nEvery node in graph is even degree: " + everyNodeisEvenDegree);
	}

	public NoEulerCycleException() 
	{
		super ("The graph is not a Euler cycle.");
	}
}