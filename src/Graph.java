import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Graph {
	// Store list of vertices in a hash set to prevent duplicates
	HashMap<String, Node> vertices;
	//max lat and max lon will be useful for painting the graph later
	double maxlat;
	double minlat;
	double maxlon;
	double minlon;
	// constructor
	public Graph() {
		vertices = new HashMap<String, Node>();
		maxlat = maxlon = Double.MIN_VALUE;
		minlat = minlon - Double.MAX_VALUE;
	}

	// construct a graph from a text file
	public Graph(String filename) {
		vertices = new HashMap<String, Node>();
		maxlat = maxlon = Double.MIN_VALUE;
		minlat = minlon - Double.MAX_VALUE;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			String type;
			StringTokenizer tokenizer;
			while ((line = reader.readLine()) != null) {
				tokenizer = new StringTokenizer(line, "\t");
				type = tokenizer.nextToken();
				if (type.equals("i")) {
					this.addIntersection(tokenizer.nextToken(), Double.parseDouble(tokenizer.nextToken()),
							Double.parseDouble(tokenizer.nextToken()));
				} else if (type.equals("r")) {
					this.addEdge(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void addIntersection(String id, double lat, double lon) {
		// create Node with ID = id; latitude and longitude
		// add Node to HashSet
		vertices.put(id, new Node(id, lat, lon));
		//keeping track of max and min lat and lon
		if(lat>maxlat) {
			maxlat = lat;
		}else if(lat<minlat) {
			minlat = lat;
		}
		if(lon>maxlon) {
			maxlon = lon;
		}else if(lon<minlon) {
			minlon = lon;
		}
	}

	// add edge between node a and node b
	void addEdge(String id, String a, String b) {
		Node anode = vertices.get(a);
		Node bnode = vertices.get(b);
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
		anode.edgeList.add(new Edge(id, anode, bnode));
		bnode.edgeList.add(new Edge(id, bnode, anode));
		// test distance calculating method
	}

	// shortest path
	List<Node> shortestPath(String start, String end) {
		Node startnode = vertices.get(start);
		Node endnode = vertices.get(end);
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		LinkedList<Node> returnlist = null;
		// iterate through vertices looking for corresponding nodes, update info, and
		// add nodes to queue
		for (String s : vertices.keySet()) {
			Node n = vertices.get(s);
			if (n.id.equals(start)) {
				n.remember(true);
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
		// iterate through all nodes from closest to farthest, and update info of each
		// adjacent node if necessary
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			for (Edge e : current.edgeList) {
				if (current.info.dist + e.weight < e.dest.info.dist) {
					e.dest.info.update(current, e);
					queue.add(queue.poll());
				}
			}
		}
		if (endnode.info.dist == Double.MAX_VALUE) {
			// clause for the case that b is not connected to a
			System.out.println(start + " is not connected to " + end);
		} else {
			// if connected, iterate through prev starting from b and build the list
			returnlist = new LinkedList<Node>();
			Node ptr = endnode;
			while (ptr != null) {
				returnlist.addFirst(ptr);
				ptr = ptr.info.prev;
			}
		}
		for (String s : vertices.keySet()) {
			// just for formality. Clearing info
			vertices.get(s).forget();
		}
		return returnlist;
	}
}
