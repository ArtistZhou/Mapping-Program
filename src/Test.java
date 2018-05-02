import java.util.List;

import javax.swing.JFrame;

public class Test {
	static double length = 500;
	static double width = 500;

	public static void main(String[] args) {
		Graph g = new Graph("ur.txt");
		List<Node> n = g.shortestPath("RUSH-RHEES", "DEWEY");
		System.out.println(n.toString());
		//to draw graph
		JFrame frame = new JFrame();
		Canvas can = new Canvas(g);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 600);
		frame.setResizable(true);
		frame.add(can);
		frame.setVisible(true);
	}
	
}
