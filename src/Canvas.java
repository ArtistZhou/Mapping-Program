
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.util.List;

public class Canvas extends JPanel {
	final static double offset = 0.05;
	static double length = 500;
	static double width = 500;
	
	//main class has a JFrame that adds this canvas
	public Canvas() {
		repaint();
	}

	// draw the map
	public void show() {
		repaint();
		// if the shortest path is known, show that too
	}

	public static void main(String[] args) {
	JFrame frame = new JFrame();
	Canvas can = new Canvas();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(500, 500);
	frame.setResizable(true);
	frame.add(can);
	frame.setVisible(true);
	}

	public static double generateX(Graph g, Node n) {
		return ((n.lon-g.minlon)/(g.maxlon - g.minlon)*width);
	}	

	public static double generateY(Graph g, Node n) {
		return ((n.lat-g.minlat)/(g.maxlat - g.minlat)*length);
	}

	
	@Override
	public void paintComponent(Graphics g) {
		g.drawLine(0, 20, 20, 30);
	}
}
