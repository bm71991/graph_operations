package graph;

public class RepeatedLocationException extends RuntimeException
{
	public RepeatedLocationException()
	{
		super ("No two nodes can have the same coordinates.");
	}
}