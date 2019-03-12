package graph;

/*****************************
Class used by the State Class 
(inner class of Node) to record
the coordinates of the node on
an X-Y graph.
*****************************/
class Coordinates
{
	private double xAxis;
	private double yAxis;

	Coordinates(double xAxis, double yAxis)
	{
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public double getxAxis()
	{
		return xAxis;
	}

	public double getyAxis()
	{
		return yAxis;
	}

	public void setxAxis(double newxAxis)
	{
		xAxis = newxAxis;
	}

	public void setyAxis(double newyAxis)
	{
		yAxis = newyAxis;
	}

  public boolean equals(Object anObject) {
	  if (anObject == this) {
	    return true;
	  }
	  if (!(anObject instanceof Coordinates)) {
	    return false;
	  }

	  Coordinates aLocation = (Coordinates)anObject;
	  return (aLocation.getxAxis() == this.xAxis) && (aLocation.getyAxis() == this.yAxis);
  }
}