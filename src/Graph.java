import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	// Store list of vertices in a hash set to prevent duplicates
	HashSet<Node> vertices;

	// constructor
	public Graph() {
		vertices = new HashSet<Node>();
	}

	void addIntersection(String id, double lat, double lon) {
		// create Node with ID = id; latitude and longitude
		// add Node to HashSet
		vertices.add(new Node(id, lat, lon));
	}

	// add edge between node a and node b
	void addEdge(String a, String b) {
		Node anode = null;
		Node bnode = null;
		// iterate through vertices looking for corresponding nodes
		for (Node n : vertices) {
			if (n.id.equals(a)) {
				anode = n;
			}
			if (n.id.equals(b)) {
				bnode = n;
			}
		}
		// handle cases when nodes aren't on the graph
		if (anode == null) {
			System.out.println(a + " does not exist on the graph");
			return;
		}
		if (bnode == null) {
			System.out.println(b + " does not exist on the graph");
			return;
		}
		// create edges on both nodes and add them to each edgeList
		Edge ab = new Edge(anode, bnode);
		Edge ba = new Edge(bnode, anode);
		anode.edgeList.add(ab);
		bnode.edgeList.add(ba);
		// test distance calculating method
		System.out.println("Distance from vertex " + a + " to " + b + " is " + ab.weight);
		System.out.println("Distance from vertex " + b + " to " + a + " is " + ba.weight);
	}

	// shortest path
	List<Node> shortestPath(String start, String end) {
		Node startnode = null;
		Node endnode = null;
		CustomPQ<Node> queue = new CustomPQ<Node>();
		LinkedList<Node> returnlist = null;
		// iterate through vertices looking for corresponding nodes, update info, and add nodes to queue
		for (Node n : vertices) {
			if (n.id.equals(start)) {
				endnode = n;
			}
			if (n.id.equals(end)) {
				n.remember(true);
				startnode = n;
			} else {
				n.remember(false);
			}
			queue.add(n);
		}
		// handle cases when nodes aren't on the graph
		if (startnode == null) {
			System.out.println(start + " does not exist on the graph");
			return null;
		}
		if (endnode == null) {
			System.out.println(end + " does not exist on the graph");
			return null;
		}
		//iterate through all nodes from closest to farthest, and update info of each adjacent node if necessary
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			for (Edge e : current.edgeList) {
				if (current.info.dist + e.weight < e.dest.info.dist) {
					e.dest.info.update(current, e);
					queue.update();
				}
			}
		}
		if (endnode.info.dist == Double.MAX_VALUE) {
			//clause for the case that b is not connected to a
			System.out.println(start + " is not connected to " + end);
		} else {
			//if connected, iterate through prev starting from b and build the list
			returnlist = new LinkedList<Node>();
			Node ptr = endnode;
			while (ptr != null) {
				returnlist.addFirst(ptr);
				ptr = ptr.info.prev;
			}
		}
		for(Node n: vertices) {
			// just for formality. Clearing info
			n.forget();
		}
		return returnlist;
	}

}
