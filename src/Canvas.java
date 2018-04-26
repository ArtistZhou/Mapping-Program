
import javax.swing.JPanel;

import java.util.List;

public class Canvas extends JPanel {
	final static double offset = 0.05;
	static double length = 500;
	static double width = 500;

	// draw the map
	public void show() {
		// if the shortest path is known, show that too
	}

	public static void main(String[] args) {
		Graph g = new Graph("nys.txt");
		System.out.println("done making graph");
		System.out.println("Latitude ranges from " + g.minlat + " to " + g.maxlat);
		System.out.println("Longitude ranges from " + g.minlon + " to " + g.maxlon);
		List<Node> n = g.shortestPath("i0", "i100");
		System.out.println("done finding shortest path");
		System.out.println(n.toString());
		for(Node node: n) {
			System.out.println(node.lat + " " + node.lon);
		}
	}

	public static double generateX(Graph g, Node n) {
		return ((n.lat-g.minlat)/(g.maxlat - g.minlat)*width);
	}

	public static double generateY(Graph g, Node n) {
		return ((n.lon-g.minlon)/(g.maxlon - g.minlon)*length);
	}
}
