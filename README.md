# a-star-path-finder-visualization

This program creates a graphical window using the Swing library (JFrame) to visualize the A* pathfinding algorithm on a grid. Here's a breakdown of the program's components:

## Running the Program

To run the program, execute the `Main` class. It will open a graphical window where you can visualize the A* pathfinding algorithm on the grid. 


### 1. Blue Tiles: Start Node:

Blue tiles represent the starting point of the pathfinding algorithm.
They indicate where the pathfinding process begins,
and the algorithm will search for the optimal path from the start node to the goal node.
Usage: The user typically selects a single node to be the start node,
and it is marked with the blue color to designate it as the initial location for the pathfinding algorithm.

### 2. Red Tiles: Goal Node:

Red tiles represent the destination or goal of the pathfinding algorithm.
They indicate the location where the pathfinding process should ultimately lead.
The algorithm's objective is to find the best path from the start node to the goal node.
Usage: Similar to the start node, the user designates a single node to be the goal node,
and it is marked with the red color to identify it as the target destination.

### 3. Black Tiles: Solid (Obstacle) Nodes:

Black tiles represent obstacles or barriers within the grid.
These nodes cannot be traversed by the pathfinding algorithm.
They create blocked pathways that the algorithm must navigate around.
Usage: The user can place black nodes strategically to define the layout of obstacles in the grid.
The algorithm must find a path that avoids these black nodes when moving from the start to the goal.


![373449787_865148681889681_1347244955351677258_n](https://github.com/nojchang/a-star-path-finder-visualization/assets/69415781/133876a6-18d4-45e3-9d81-9c0490bbed5b)

Press the "Enter" key to start the search automatically.


### 4. Orange Tiles: Checked Nodes

Orange tiles represent nodes that have been checked by theA* algorithm during its exploration of potential paths.
These nodes have been evaluated to determine their suitability as part of the path
but have not been selected as part of the final path yet. They are considered intermediate steps in the search process.
As the algorithm progresses, it marks nodes as "checked" in orange to
indicate that they have been considered but are not part of the final path.

### 5. Green Tiles: Path Nodes

Green tiles represent nodes that are part of the final path determined by the A* algorithm.
These nodes have been selected as part of the optimal path from the start node to the goal node.
They represent the best route found by the algorithm. Once the algorithm successfully reaches the goal node,
it backtracks from the goal to the start, marking nodes along the best path as "path" nodes in green.
These nodes visually depict the solution to the pathfinding problem.


![376370601_1374589766789561_1355207211346412076_n](https://github.com/nojchang/a-star-path-finder-visualization/assets/69415781/a07215c6-f560-4cf0-bc27-e20e069f4338)

The program's purpose is to demonstrate the A* pathfinding algorithm visually, and it includes various methods for customization and testing.

## Main Class (`Main.java`)

The `Main` class serves as the entry point of the program. It sets up the graphical window and initializes the A* pathfinding visualization. Here are the key steps:

1. **Creating the JFrame Window**: It creates a graphical window using the Swing library (JFrame).

2. **Window Configuration**: 
   - Sets the default close operation to `JFrame.EXIT_ON_CLOSE`, ensuring that the program exits when the user closes the window.
   - Prevents the user from resizing the window to maintain a fixed size.
   - Adds an instance of the `Panel` class (which contains the pathfinding visualization) to the window.
   - Causes the window to automatically adjust its size to fit its contents.
   - Centers the window on the screen.
   - Sets the window to be visible.

## Node Class (`Node.java`)

The `Node` class represents individual nodes or cells on the grid. Each node has properties such as its position, cost, type (start, goal, solid, etc.), and state (open, checked). Nodes are displayed as buttons in the GUI, allowing user interaction. Key methods include:

- `setAsStart()`: Sets a node as the start node with a specific appearance.
- `setAsGoal()`: Sets a node as the goal node with a specific appearance.
- `setAsSolid()`: Sets a node as a solid (obstacle) node with a specific appearance.
- `setAsOpen()`: Marks a node as open during the A* search.
- `setAsChecked()`: Marks a node as checked during the A* search.
- `setAsPath()`: Marks a node as part of the final path with a specific appearance.
- `actionPerformed(ActionEvent e)`: Handles mouse clicks on nodes (for debugging purposes).

## Panel Class (`Panel.java`)

The `Panel` class implements the A* pathfinding algorithm and handles the visualization. Key features include:

- Grid Setup: Defines the grid size, node size, and other parameters for the visualization.
- Start and Goal Nodes: Sets and updates the start and goal nodes.
- Solid Nodes: Places solid (obstacle) nodes on the grid.
- Cost Calculation: Calculates G, H, and F costs for each node and displays them.
- `search()`: Performs one step of the A* search algorithm manually.
- `autoSearch()`: Continues the A* search algorithm automatically until the goal node is reached.
- `trackThePath()`: Backtracks from the goal node to the start node, marking the nodes along the best path.

## KeyHandler Class (`KeyHandler.java`)

The `KeyHandler` class is responsible for handling keyboard events, specifically listening for the "Enter" key press to trigger the pathfinding search. Key methods include:

- `keyPressed(KeyEvent e)`: Listens for the "Enter" key press and triggers the `autoSearch()` method in the `Panel` class when pressed.

## UnitTests Class (`UnitTests.java`)

The `UnitTests` class contains several test methods to verify the functionality of various parts of the program. These tests cover setting start/goal nodes, adding solid nodes, calculating costs, and testing the A* search method.






