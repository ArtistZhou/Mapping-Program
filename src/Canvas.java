
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.util.List;

public class Canvas extends JPanel {
	final static double offset = 0.05;
	static double length = 500;
	static double width = 500;
	Graph g;
	
	//main class has a JFrame that adds this canvas
	public Canvas(Graph g) {
		this.g = g;
		repaint();
	}

	// draw the map
	public void show() {
		repaint();
		// if the shortest path is known, show that too
	}

	public static double generateX(Graph g, Node n) {
		return ((n.lon-g.minlon)/(g.maxlon - g.minlon)*width);
	}	

	public static double generateY(Graph g, Node n) {
		return ((n.lat-g.minlat)/(g.maxlat - g.minlat)*length);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		//for each intersection in list of vertices in graph
		
		//draw line from that vertex to each neighbour
	
	}
}
