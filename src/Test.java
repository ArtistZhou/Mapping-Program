import java.util.List;

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
	}
	public static double generateX(Graph g, Node n) {
		return ((n.lon-g.minlon)/(g.maxlon - g.minlon)*width);
	}

	public static double generateY(Graph g, Node n) {
		return ((n.lat-g.minlat)/(g.maxlat - g.minlat)*length);
	}
	
}
