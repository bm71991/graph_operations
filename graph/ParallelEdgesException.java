package graph;

public class ParallelEdgesException extends RuntimeException
{
	public ParallelEdgesException()
	{
		super ("No parallel edges are allowed in this graph.");
	}
}