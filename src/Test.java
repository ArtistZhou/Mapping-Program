import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Test {
	static double length = 500;
	static double width = 500;

	public static void main(String[] args) {
		String file = args[0];
		Graph g = new Graph(file);
		
		ArrayList<String> com = new ArrayList<String>();
		for(String s: args) {
			com.add(s);
		}
		
		if(com.contains("--directions")) {
			String to = args[(args.length-1)];
			String from = args[args.length -2];
			List<Node> n = g.shortestPath(from, to);
			System.out.println(n.toString());
			System.out.println(Node.pathLength(n) + " Miles");
			
			if(com.contains("--show")) {
				JFrame frame = new JFrame();
				Canvas can = new Canvas(g, n);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(700, 600);
				frame.setResizable(true);
				frame.add(can);
				frame.setVisible(true);
			}
		} else if (com.contains("--show")) {
			JFrame frame2 = new JFrame();
			Canvas can2 = new Canvas(g);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setSize(500, 600);
			frame2.setResizable(true);
			frame2.add(can2);
			frame2.setVisible(true);
		}

	}
	
}
