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

We used the code for the 'Harversine Formula' found at [https://github.com/jasonwinn/haversine/blob/master/Haversine.java] to calculate the weight of the edges based on their longitude and latitude. This was the only part of the pre-written code we used.

Notable Obstacles:
- The biggest obstacle for drawing the graph was trying to maintain it's aspect ratio when the window was resized. 

Design Choices:
- We really tried to focus on making our methods as efficient as possible. For the dijkstra's algorithm, for example, we used a [insert], which did [insert]
- As for drawing the graph, we wrote our function in such a manner that a) the graph maintains it's aspect ration when scaled and b) it remains at the centre of the screen when scaled. 
- To clearly identify the start and finish points on the graph, we added a small red pointers, that people associate with maps and also draw the distance (in miles) for a more informative map. 

Extra-Credit Features: 


Compile and Run Instructions

