import java.util.List;

import javax.swing.JFrame;

public class Test {
	static double length = 500;
	static double width = 500;

	public static void main(String[] args) {
		Graph g = new Graph("nys.txt");
		List<Node> n = g.shortestPath("i8", "i23642");
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
	}
	
}
