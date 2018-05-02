
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
	Node origin;
	Node destination;
	
	Boolean show;
	
	HashMap<Node, Node> sp = new HashMap<Node, Node>();
	List<Node> path;

	// main class has a JFrame that adds this canvas
	public Canvas(Graph g) {
		this.graph = g;
		show = false;
		repaint();
	}
	
	public Canvas(Graph g, List<Node> l) {
		this.graph = g;
		show = true;
		path = l;
		
		Queue<Node> nodes = new LinkedList<Node>();
		nodes.addAll(l);
		while(nodes.size() != 1) {
			Node origin = nodes.poll();
			Node dest = nodes.peek();
			sp.put(origin, dest);
		}
		
		origin = l.get(0);
		destination = l.get(l.size()-1);
		
		repaint();
	}
	
	public void scaleRatio() {
		
	}

	//adding padding
	public double generateX(Node n) {
		double padding = 0.0;
		if(getWidth() > getHeight()) {
			padding = (getWidth() - getHeight())/2;
		}
		
		return ((n.lon-graph.minlon)/(graph.maxlon - graph.minlon)*width) + padding;
	}

	public double generateY(Node n) {
		double padding = 0.0;
		if(getHeight() > getWidth()) {
			padding = (getHeight() - getWidth())/2;
		}
		return (length - (n.lat-graph.minlat)/(graph.maxlat - graph.minlat)*length)+ padding;
	}

	@Override
	public void paintComponent(Graphics g) {
		length = getHeight();
		width = getWidth();
		
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
			
				if(show && sp.containsKey(node) && sp.get(node).equals(destination)) {
					g2.setColor(Color.BLUE);
					g2.setStroke(new BasicStroke(5));
					g2.drawLine(x1, y1, x2, y2);
					
					if(node == origin) {
						g2.drawImage(img, x1-8, y1-20, 17, 20, this);
						g2.drawString(node.id, x1 + 10, y1);
					}
					
					if(destination == this.destination) {
						g2.drawString(destination.id, x2 + 10, y2);
						
						g2.drawImage(img, x2-8, y2-20, 17, 20, this);
						
						String full = "" + Node.pathLength(path);
						String pathlen = full.substring(0, 4);
						g2.setColor(Color.RED);
						g2.fillRoundRect(x2 - 10, y2 + 5, 30, 15, 5, 5);
						g2.setColor(Color.WHITE);
						g2.drawString(pathlen, x2 -8, y2 + 17);
					}
					
				} else {
					g2.setColor(Color.BLACK);
					g2.setStroke(new BasicStroke(1));
					g2.drawLine(x1, y1, x2, y2);
					
					
				}

			}
		}
	}
}
