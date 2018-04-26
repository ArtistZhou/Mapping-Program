
import javax.swing.JPanel;

import java.util.List;

public class Canvas extends JPanel {
	final static double offset = 0.05;
	static double length = 500;
	static double width = 500;

	// draw the map
	public void show() {
		// if the shortest path is known, show that too
	}

	public static void main(String[] args) {
	
	}

	public static double generateX(Graph g, Node n) {
		return ((n.lat-g.minlat)/(g.maxlat - g.minlat)*width);
	}

	public static double generateY(Graph g, Node n) {
		return ((n.lon-g.minlon)/(g.maxlon - g.minlon)*length);
	}
}
