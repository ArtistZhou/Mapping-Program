import java.util.List;

public class Test {

	public static void main(String[] args) {
		Graph g = new Graph("nys.txt");
		System.out.println("done making graph");
		System.out.println("Latitude ranges from " + g.minlat + " to " + g.maxlat);
		System.out.println("Longitude ranges from " + g.minlon + " to " + g.maxlon);
		List<Node> n = g.shortestPath("i0", "i100");
		System.out.println("done finding shortest path");
		System.out.println(n.toString());
		for (Node node : n) {
			System.out.println(node.lat + " " + node.lon);
		}
	}

}
