
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
public class test {
	static double width = 500;

	public static void main(String[] args) {
		Graph g = new Graph("monroe.txt");
		List<Node> n = g.shortestPath("i65", "i234");
		System.out.println(n.toString());
		//to draw graph
		
		//to show shortest path too
		JFrame frame = new JFrame();
		Canvas can = new Canvas(g, n);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setResizable(true);
		frame.add(can);
		frame.setVisible(true);
		
		//to show just the map
//		JFrame frame2 = new JFrame();
//		Canvas can2 = new Canvas(g);
//		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame2.setSize(500, 600);
//		frame2.setResizable(true);
//		frame2.add(can2);
//		frame2.setVisible(true); 

	}
	
}
