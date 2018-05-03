# Mapping-Program
Find shortest Path 

Collaborators:
Akira Ranjan Sah . 06 . asah@u.rochester.edu
Shagun Bose . 181 . sbose5@u.rochester.edu

Files:
Node.java
Edge.java
Graph.java
Canvas.java
StreetMap.java

Synopsis:
For this project, we divided the work accordingly. Together we first defined, roughly the base classes: Node, Edge and Graph. Then Akira handled the construction of the graph from the input file, the implementation of Dijkstra's Algorithm to find the shortest path between two arbitrary points, the calculation of the distance in miles of this path and the printing of this path onto the console. Shagun handled the drawing and scaling of the map, the drawing of the shortest path using the path computed, the creation of the main-class to use command line arguments for input and additional graphic features. 

A general road-map of the code: It reads input from the txt file, and creates a graph with Nodes and Edges. Each graph has a list of nodes called vertices and each node has a list of edges for it's neightbours which makes the adjacency list. After creating the graph, it calculates the shortest path and renders the graph on a JFrame (depending on user input). We have explained the process of calculating the shortest distance and the drawing the graph in more detail below. 

DRAWING THE MAP
It takes O(|E|) --> O(|V|^2) to draw the map. The algorithm goes as such: 
For every node in the Graph's list of vertices O(|V|)
- calculate the x, y coordinates using it's latitude and longitude degrees O(1)
- for each Node in it's adjacency list, calculate x,y coordinates and draw a line connecting that node with it's neighbors (O(|E|/|V|)

To draw the shortest path the following modifications are made:
- the canvas takes in the list of nodes, L, returned by shortest path method
	- add Nodes from L to a Queue, Q
	- Until Q has only 1 element left, 
		- Pop element 1 and peek element 2 from Q
		- add to hashMap<Node, Node> SP as (element 1, element 2)
	- while drawing the map, if {Node, Destination} pair are part of the shortest path, draw a thicker blue line instead
	- after drawing, add extra graphic elements: pointers, and print distance (miles)

notable obstacle/design choices
- As for drawing the graph, we wrote our function in such a manner that a) the graph maintains it's aspect ration when scaled and b) it remains at the centre of the screen when scaled. It was quite a challenge to ensure that the aspect ratio was maintained, to do this we defined the size of the JPanel instead of the JFrame. For example, if the width of the frame was greater than the height, we fixed the panel width to the width but, scaled the height according to the aspect ratio of the graph.
- To clearly identify the start and finish points on the graph, we added a small red pointers, that people associate with maps and also draw the distance (in miles) for a more informative map. 

SHORTEST PATH
The worst case runtime of shortest path algorithm is V^2log(V) because:
-extract the minimum node from the priority queue, O(log(V)), at most V times
-check all adjacent nodes of the current and update information, O(V)

notable obstacle/design choices
- We really tried to focus on making our methods as efficient as possible. 
	-For the Dijkstra's algorithm, the norm is to add all nodes into a priority queue sorted by distance from the start node.
		-Always having all unvisited nodes in the priority queue makes the runtime of Dijkstra's algorithm is inefficient because the cost of 		taking out the minimum element is dependent on the number of nodes in the queue. 
	-Instead, we opted to only add the start node to the queue at the beginning, and add the adjacent nodes as their distances were updated when visiting the start node. 
	-So just the nodes that are unvisited and adjacent to visited nodes are in the queue. While the worst case runtime of the algorithm is still V^2log(V), this drastically improved the general runtime of the algorithm because the queue was always as small as possible. 
	-All other things constant, the change improved the runtime by at least a constant factor of 5 when we tested the algorithms. 
	-This also means that the algorithm can handle disconnected graphs more efficiently, since nodes that are not connected to the Also, the shortest path algorithm stops iterating through nodes once the end node has been visited.

Note: We used the code for the 'Harversine Formula' found at [https://github.com/jasonwinn/haversine/blob/master/Haversine.java] to calculate the weight of the edges based on their longitude and latitude. This was the only part of the pre-written code we used.

Extra-Credit Features: 
- We have added extra graphics to the map to make it more informational (pointer and printing distance)
- We have also ensured that the map is always centred (for a better user experience)
- We have improved upon the shortest path algorithm to make it more efficient.

Compile and Run Instructions
javac StreetMap.java
java StreetMap "file.txt" --show --directions from to
