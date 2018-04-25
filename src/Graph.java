import java.util.HashSet;
import java.util.List;

import javax.swing.JPanel;

public class Graph extends JPanel {
	//Store list of vertices in a hash set to prevent duplicates
	HashSet<Node> vertices;
	
	//constructor
	public Graph() {
		vertices = new HashSet<Node>();
	}
	
	void addIntersection(String id, double lat, double lon) {
		//create Node with ID = id; latitude and longitude
		//add Node to HashSet
		vertices.add(new Node(id, lat, lon));
	}
	
	//add edge between node a and node b
	void addEdge(String a, String b){
		Node anode = null;
		Node bnode = null;
		//iterate through vertices looking for corresponding nodes
		for(Node n: vertices) {
			if(n.id.equals(a)) {
				anode = n;
			}
			if(n.id.equals(b)) {
				bnode = n;
			}
		}
		//handle cases when nodes aren't on the graph
		if(anode==null) {
			System.out.println(a + " does not exist on the graph");
			return;
		}
		if(bnode==null) {
			System.out.println(b + " does not exist on the graph");
			return;
		}
		//create edges on both nodes and add them to each edgeList 
		Edge ab = new Edge(anode, bnode);
		Edge ba = new Edge(bnode, anode);
		anode.edgeList.add(ab);
		bnode.edgeList.add(ba);
		//test distance calculating method
		System.out.println("Distance from vertex " + a + " to " + b + " is " + ab.weight);
		System.out.println("Distance from vertex " + b + " to " + a + " is " + ba.weight);
	}
	
	//shortest path
	List<Node> shortestPath(String a, String b){
		return null; //change this
	}
	
	//draw the map
	public void show() {
		//if the shortest path is known, show that too
	}
	
	public static void main (String[] args) {
		//take terminal arguments 
		
	}
	
}
