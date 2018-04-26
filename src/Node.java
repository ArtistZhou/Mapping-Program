import java.util.HashSet;

public class Node implements Comparable<Node> {

	String id;
	double lat;
	double lon;
	Information info;

	// stores the edges that this node is connected to (undirected graph)
	HashSet<Edge> edgeList;

	// constructor
	public Node(String id, double lat, double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.info = null;
		edgeList = new HashSet<Edge>();
	}
	
	//creates new instance of info
	public void remember(boolean start) {
		this.info = new Information(start);
	}
	
	//removes the instance of info (after the use of info is done)
	public void forget() {
		this.info = null;
	}
	
	public String toString() {
		return id;
	}

	@Override
	public int compareTo(Node n) {
		//if either one of the node is lacking info, then it has no dist, so there is no point in comparing
		if (this.info == null || n.info == null) {
			return 0;
		}
		if (this.info.dist >= n.info.dist) {
			return 1;
		} else {
			return -1;
		}
	}
	
	// a collection of information that the node must store when using Dijkstra's Algorithm
	public static class Information {
		Node prev;
		double dist;
		
		// the starting vertex must have a distance of 0 for the whole thing to work
		public Information(boolean start) {
			prev = null;
			if (start) {
				dist = 0;
			} else {
				dist = Integer.MAX_VALUE;
			}
		}

		public void update(Node newprev, Edge workingEdge) {
			this.prev = newprev;
			this.dist = newprev.info.dist + workingEdge.weight;
		}
	}
}
