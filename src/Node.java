import java.util.HashSet;

public class Node {
	
	String id;
	Double lat;
	Double lon;
	
	//stores the edges that 
	HashSet<Edge> edgeList;
	
	//constructor
	public Node(String id, Double lat, Double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}
}
