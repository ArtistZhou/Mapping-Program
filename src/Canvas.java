
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.util.List;

public class Canvas extends JPanel {
	final static double offset = 0.05; //boundary of map
	static double length;
	static double width;
	Graph graph;
	
	//main class has a JFrame that adds this canvas
	public Canvas(Graph g) {
		this.graph = g;
		
		repaint();
	}

	// draw the map
	public void show() {
		repaint();
		// if the shortest path is known, show that too
	}

	public double generateX(Node n) {
		return ((n.lon-graph.minlon)/(graph.maxlon - graph.minlon)*width);
	}	

	public double generateY(Node n) {
		return (length - (n.lat-graph.minlat)/(graph.maxlat - graph.minlat)*length);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		double ratio = (graph.maxlat - graph.minlat)/(graph.maxlon - graph.minlon);
		int LENGTH = getHeight();
		int WIDTH = getWidth();
		
		if(LENGTH < WIDTH) {
			length = getHeight();
			width = ratio*getHeight();
		} else {
			width = getWidth();
			length = ratio*getWidth();
		}
		
		
		int count = 0;
		
		//for each intersection in list of vertices in graph
		for(String s: graph.vertices.keySet()) {
			Node node = graph.vertices.get(s);
			int x1 = (int)generateX(node);
			int y1 = (int)generateY(node);
			
			for(Edge adj : node.edgeList) {
				Node destination = adj.dest;
				int x2 = (int)generateX(destination);
				int y2 = (int)generateY(destination);
				g.drawLine(x1, y1, x2, y2);
				count ++;
			}
		}
		
		//System.out.println("\n drew " + count + " edges, vertices = " + graph.vertices.size());
		//draw line from that vertex to each neighbour
	
	}
}
