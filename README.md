# Mapping-Program
Find shortest Path 

Collaborators:
Akira Ranjan Sah . 06
Shagun Bose . 181

Files:
Node.java
Edge.java
Graph.java
Canvas.java
StreetMap.java

Synopsis:
For this project, we divided the work accordingly. Together we first defined, roughly the base classes: Node, Edge and Graph. Then Akira handled the construction of the graph from the input file, the implementation of Dijkstra's Algorithm to find the shortest path between two arbitrary points, the calculation of the distance in miles of this path and the printing of this path onto the console. Shagun handled the drawing and scaling of the map, the drawing of the shortest path using the path computed, the creation of the main-class to use command line arguments for input and additional graphic features.  

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


We used the code for the 'Harversine Formula' found at [https://github.com/jasonwinn/haversine/blob/master/Haversine.java] to calculate the weight of the edges based on their longitude and latitude. This was the only part of the pre-written code we used.

Notable Obstacles:
- The biggest obstacle for drawing the graph was trying to maintain it's aspect ratio when the window was resized. 

Design Choices:
- We really tried to focus on making our methods as efficient as possible. For the dijkstra's algorithm, for example, we used a [insert], which did [insert]
- As for drawing the graph, we wrote our function in such a manner that a) the graph maintains it's aspect ration when scaled and b) it remains at the centre of the screen when scaled. 
- To clearly identify the start and finish points on the graph, we added a small red pointers, that people associate with maps and also draw the distance (in miles) for a more informative map. 

Extra-Credit Features: 


Compile and Run Instructions

