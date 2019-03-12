package graph;

public class LoopException extends RuntimeException
{
	public LoopException()
	{
		super ("No loops are allowed in this graph.");
	}
}