import java.util.ArrayList;
import graph.*;
import operations.searches.*;
import operations.euler_cycle.*;


public class Driver
{
	public static void main (String[] args)
	{
		Graph g = new Graph();

		g.addNode(6,19, "NP");
		g.addNode(5,12, "Gillette");
		g.addNode(10,13, "Chatham");
		g.addNode(2,8, "Brick Church");
		g.addNode(11,6, "Orange");
		g.addNode(8,1, "South Orange");
		g.addNode(16,9, "Newark");
		g.addNode(17,15, "Carlstadt");
		g.addNode(18,12, "Secaucus");
		g.addNode(23,8, "Hoboken");

		g.connect("NP", "Gillette");
		g.connect("NP", "Chatham");
		g.connect("Gillette", "Chatham");
		g.connect("Gillette", "Brick Church");
		g.connect("Chatham", "Secaucus");
		g.connect("Brick Church", "Orange");
		g.connect("Orange", "Chatham");
		g.connect("Orange", "Newark");
		g.connect("Orange", "South Orange");
		g.connect("Newark", "Carlstadt");
		g.connect("Secaucus", "Hoboken");
		g.connect("Carlstadt", "Hoboken");
		g.connect("South Orange", "Hoboken");

		Searches.aStar(g, "Brick Church", "Hoboken");

	}
}
