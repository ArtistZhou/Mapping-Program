import java.util.List;

import javax.swing.JFrame;

public class Test {
	static double length = 500;
	static double width = 500;

	public static void main(String[] args) {
		Graph g = new Graph("ur.txt");
		System.out.println("done making graph");
		System.out.println("Latitude ranges from " + g.minlat + " to " + g.maxlat);
		System.out.println("Longitude ranges from " + g.minlon + " to " + g.maxlon);
		List<Node> n = g.shortestPath("HOEING", "LOVEJOY");
		System.out.println("done finding shortest path");
		System.out.println(n.toString());
		for (Node node : n) {
			System.out.println(node.lat + " " + node.lon);
		}
		
		//to draw graph
		JFrame frame = new JFrame();
		Canvas can = new Canvas(g);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setResizable(true);
		frame.add(can);
		frame.setVisible(true);
	}
	public static double generateX(Graph g, Node n) {
		return ((n.lon-g.minlon)/(g.maxlon - g.minlon)*width);
	}

	public static double generateY(Graph g, Node n) {
		return ((n.lat-g.minlat)/(g.maxlat - g.minlat)*length);
	}
	
}
