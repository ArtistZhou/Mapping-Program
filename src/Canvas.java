
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Canvas extends JPanel {
	final static double offset = 0.05; // boundary of map
	double length;
	double width;
	Graph graph;
	double scale;
	HashMap<Node, Node> sp = new HashMap<Node, Node>();

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
	
	public Canvas(Graph g, List<Node> l) {
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
		
		Queue<Node> nodes = new LinkedList<Node>();
		nodes.addAll(l);
		while(nodes.size() != 1) {
			Node origin = nodes.poll();
			Node dest = nodes.peek();
			sp.put(origin, dest);
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
		Graphics2D g2 = (Graphics2D) g;
		if (getWidth() > getHeight()) {
			length = width = getHeight();
		} else {
			length = width = getWidth();
		}
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("map.png"));
		} catch (IOException e) {}
		
		// for each intersection in list of vertices in graph
		for (String s : graph.vertices.keySet()) {
			Node node = graph.vertices.get(s);
			int x1 = (int) generateX(node);
			int y1 = (int) generateY(node);

			for (Node destination : node.adjlist.keySet()) {
				int x2 = (int) generateX(destination);
				int y2 = (int) generateY(destination);
				//there's a better way of doing something like this
				//look at my pathlength method in Node class -Akira
				if(sp.containsKey(node) && sp.get(node).equals(destination)) {
					g2.setColor(Color.BLUE);
					g2.setStroke(new BasicStroke(5));
					g2.drawLine(x1, y1, x2, y2);
					g2.drawImage(img, x1-8, y1-20, 17, 20, this);
					g2.drawImage(img, x2-8, y2-20, 17, 20, this);
				} else {
					g2.setColor(Color.BLACK);
					g2.setStroke(new BasicStroke(1));
					g2.drawLine(x1, y1, x2, y2);
				}
				
				
				
			}
		}
	}
}
