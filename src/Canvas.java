
import javax.swing.JPanel;

import java.util.List;
public class Canvas extends JPanel{
	// draw the map
	public void show() {
		// if the shortest path is known, show that too
	}

	public static void main(String[] args) {
		Graph g = new Graph("nys.txt");
		System.out.println("done making graph");
		List<Node> n = g.shortestPath("i0", "i100");
		System.out.println("done finding shortest path");
		System.out.println(n.toString());
	}
}
