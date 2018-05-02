
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.util.List;

public class Canvas extends JPanel {
	final static double offset = 0.05; // boundary of map
	double length;
	double width;
	Graph graph;
	double scale;

	// main class has a JFrame that adds this canvas
	public Canvas(Graph g) {
		this.graph = g;
		double widthRatio = graph.maxlon - graph.minlon;
		double heightRatio = graph.maxlat - graph.minlat;
		
		// the idea is that I am using one scale for both x and y such that neither x
		// nor y exceeds length or width
		if (widthRatio > heightRatio) {
			scale = widthRatio;
		} else {
			scale = heightRatio;
		}
		
		if (getWidth() > getHeight()) {
			length = width = getHeight();
		} else {
			length = width = getWidth();
		}
		repaint();
	}

	// added some math to center the map on the canvas
	public double generateX(Node n) {
		double realx = (n.lon - graph.minlon)/scale*width;
		if (getWidth() > getHeight()) {
			return (realx + (double) (getWidth() - getHeight()) / 2);
		}
		return realx;
	}

	public double generateY(Node n) {
		double realy = (1 - (n.lat-graph.minlat)/scale)*length;
		if(getHeight()>getWidth()) {
			return ( realy + (double)(getHeight()-getWidth())/2);
		}
		return realy;
	}

	@Override
	public void paintComponent(Graphics g) {
		if (getWidth() > getHeight()) {
			length = width = getHeight();
		} else {
			length = width = getWidth();
		}

		// for each intersection in list of vertices in graph
		for (String s : graph.vertices.keySet()) {
			Node node = graph.vertices.get(s);
			int x1 = (int) generateX(node);
			int y1 = (int) generateY(node);

			for (Edge adj : node.edgeList) {
				Node destination = adj.dest;
				int x2 = (int) generateX(destination);
				int y2 = (int) generateY(destination);
				
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}
}
