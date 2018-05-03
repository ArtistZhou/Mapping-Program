/* AUTHORS
 * Shagun Bose . 118
 * Akira Ranjan Sah . 06
 */
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
	double length; //length and width of panel 
	double width;
	
	Graph graph; //graph that will be represented as a map
	
	//variables used to Draw Shortest Path
	Node origin;
	int ox, oy, dx, dy;
	Node destination;
	HashMap<Node, Node> sp = new HashMap<Node, Node>(); 
	List<Node> path;
	
	//boolean used to ensure that the path is only drawn when needed
	Boolean show;

	/* There are two types of Constructors:
	 * Where only the graph is rendered
	 * Where the shortest path is also rendered
	 */
	public Canvas(Graph g) {
		this.graph = g;
		show = false;
		repaint();
	}
	
	public Canvas(Graph g, List<Node> l) {
		this.graph = g;
		show = true;
		path = l;
		
		//this takes the nodes and adds them to a queue and then creates pairs of edges which is added to a hashMap to keep track. 
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

	//generates x and y coordinates based on the given latitude and longitude
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

	//renders the map
	@Override
	public void paintComponent(Graphics g) {
		length = getHeight();
		width = getWidth();
		
		//to ensure the aspect ratio is maintained you take the bigger side and adjust the smaller side to the ratio, accordingly
		Graphics2D g2 = (Graphics2D) g;
		if (getWidth() > getHeight()) {
			length = width = getHeight();
		} else {
			length = width = getWidth();
		}
		
		//image of the pointer
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("map.png"));
		} catch (IOException e) {}
		
		// for each intersection in list of vertices in graph
		for (String s : graph.vertices.keySet()) { //O(|E|) --> O(|V|^2)
			Node node = graph.vertices.get(s);
			int x1 = (int) generateX(node);
			int y1 = (int) generateY(node);
			//draw the edge connecting it to it's neighbours
			for (Node destination : node.adjlist.keySet()) {
					int x2 = (int) generateX(destination);
					int y2 = (int) generateY(destination);
					if(node == origin) { ox = x1; oy = y1;}
					if(destination == this.destination) {dx = x2; dy = y2;}
			
					//if the user wants to see the shortest path, do this
					if(show && sp.containsKey(node) && sp.get(node).equals(destination)) {
						g2.setColor(Color.BLUE);
						g2.setStroke(new BasicStroke(5));
						g2.drawLine(x1, y1, x2, y2);
					//else  simply draw map	
					} else {
						g2.setColor(Color.BLACK);
						g2.setStroke(new BasicStroke(1));
						g2.drawLine(x1, y1, x2, y2);
					}
				}
		}
		
		//Additional Graphic - pointers to show where the start and finish are and the distance (miles)
		if(show) {
			g2.drawImage(img, ox-8, oy-20, 17, 20, this);
			g2.drawImage(img, dx-8, dy-20, 17, 20, this);
			//print distance in miles on map
			String full = "" + Node.pathLength(path);
			String pathlen = full.substring(0, 4);
			g2.setColor(Color.RED);
			g2.fillRoundRect(dx - 30, dy + 5, 30, 15, 5, 5);
			g2.setColor(Color.WHITE);
			g2.drawString(pathlen, dx -28, dy + 17);
		}
	
	}
}
