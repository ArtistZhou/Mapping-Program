import java.util.HashSet;
import java.util.List;

import javax.swing.JPanel;

public class Graph extends JPanel {
	//Store list of vertices in a hash set to prevent duplicates
	HashSet<Node> vertices;
	
	//constructor
	public Graph() {
		
	}
	
	void addIntersection(String id, double lat, double lon) {
		//create Node with ID = id; latitude and longitude
	
		//add Node to HashSet
		
	}
	
	//add edge between node a and node b
	void addEdge(String a, String b){
		//find Node a and Node b in HashSet
		
		//create edge between two Nodes. UNDIRECTED Graph 
		Edge e = null;
		
		//test distance calculating method
		System.out.println(e.weight);
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
