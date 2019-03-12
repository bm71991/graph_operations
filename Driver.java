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

		// g.addNode(6,19, "a");
		// g.addNode(5,12, "b");
		// g.addNode(10,13, "c");
		// g.addNode(2,8, "d");
		// g.addNode(11,6, "e");
		// g.addNode(11, 6, "f");
		// g.addNode(8,1, "g");
		// g.addNode(14, 12, "h");
		// g.addNode(20, 21, "i");

		// g.connect("a","d");
		// g.connect("a","b");
		// g.connect("d","b");
		// g.connect("e","b");
		// g.connect("c","b");
		// g.connect("c","e");
		// g.connect("e","d");
		// g.connect("d","f");
		// g.connect("f","g");
		// g.connect("g","e");
		// g.connect("g","h");
		// g.connect("h","i");
		// g.connect("g","i");

		Searches.aStar(g, "Brick Church", "Hoboken");

	}
}













































		// testGraph.addNode(5,5, "a");
		// testGraph.addNode(7,11, "b");
		// testGraph.addNode(9,8, "c");
		// testGraph.addNode(10,5, "d");
		// testGraph.addNode(13,4, "e");
		// testGraph.addNode(17,5, "f");
		// testGraph.addNode(15,10, "g");
		// testGraph.addNode(15,15, "h");

		// testGraph.connect("a", "b");
		// testGraph.connect("b", "a");

		// testGraph.connect("a", "c");
		// testGraph.connect("c", "a");

		// testGraph.connect("a", "d");
		// testGraph.connect("d", "a");

		// testGraph.connect("b", "e");
		// testGraph.connect("e", "b");

		// testGraph.connect("b", "g");
		// testGraph.connect("g", "b");

		// testGraph.connect("g", "h");
		// testGraph.connect("h", "g");

		// testGraph.connect("c", "g");
		// testGraph.connect("g", "c");


		// testGraph.connect("d", "f");
		// testGraph.connect("f", "d");

		// testGraph.connect("f", "h");
		// testGraph.connect("h", "f");

		// Searches.breadthFirst(testGraph, "a", "z");
