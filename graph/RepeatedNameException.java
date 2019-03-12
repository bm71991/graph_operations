package graph;

public class RepeatedNameException extends RuntimeException
{
	public RepeatedNameException()
	{
		super ("No names of existing nodes can be repeated.");
	}
}