/*This is the central class that implements the A* pathfinding.
It extends JPanel and represents the game board or grid where pathfinding takes place.
The class defines the grid size, node size, and other parameters.
It contains the A* algorithm logic and handles the visualization of nodes.
Manages the start and goal nodes, solid (obstacle) nodes, and calculates costs for each node.
It provides methods for manual searching (search) and automated searching (autoSearch) to find the path.
The class also handles user interactions and key events via the KeyHandler class.
*/

package a_Star_Pathfinder;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel {
	
	/*Screen setting, define various screen settings and dimensions for the grid. 
	 It sets parameters like the number of columns and rows, the size of each node, 
	 and the overall screen size.
	*/
	final int maxColumn = 16;
	final int maxRow = 13;
	final int nodeSize = 75;
	final int screenWidth = nodeSize * maxColumn;
	final int screenHeight = nodeSize * maxRow;
	
	// Declare the NODE as 2D array to represent the grid of nodes.
	Node [][] node = new Node[maxColumn][maxRow];
	
	// Setting the Start and the Goal nodes
	Node startNode, goalNode, currentNode;
	
	// To keep track of nodes that are open for exploration and nodes that have already been checked.
	ArrayList<Node> openList = new ArrayList<>();
	ArrayList<Node> checkedList = new ArrayList<> ();
	
	/* goalReached keeps track of whether the goal node has been reached, 
	and step counts the number of steps taken during the search.
	*/
	boolean goalReached = false;
	int step = 0;

	// Constructor of the Panel class.
	public Panel() {
		
		// Set the size of the panel
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		// Set background color
		this.setBackground(Color.black);
		
		// Set layout and key event listener for the panel.
		this.setLayout(new GridLayout(maxRow, maxColumn));
		this.addKeyListener(new KeyHandler(this));
		this.setFocusable(true);
		
		// Placing nodes on the panel.
		int column = 0;
		int row = 0;
		
		// A while loop that iterates through the columns and rows of the grid, creating and adding nodes.
		while (column < maxColumn && row < maxRow) {
			
			// Pass this column row to the constructor
			node[column][row] = new Node(column, row);
			
			// Add this instantiated node to this panel
			this.add(node[column][row]);
			
			// Increment the column and row counters, ensuring that nodes are placed correctly in the grid.
			column++;
			if(column == maxColumn) {
				column = 0; // column is reset to 0
				row++;
			}
		}
		
		// Set start and goal node
		setStartNode(2, 7);
		setGoalNode(12, 5);
		
		// Place solid (obstacles) nodes
		setSolidNode(9,2);
		setSolidNode(9,3);
		setSolidNode(9,4);
		setSolidNode(9,5);
		setSolidNode(9,6);
		setSolidNode(9,7);
		setSolidNode(9,8);
		setSolidNode(6,2);
		setSolidNode(7,2);
		setSolidNode(8,2);
		setSolidNode(9,2);
		setSolidNode(10,2);
		setSolidNode(10,7);
		setSolidNode(11,7);
		setSolidNode(12,7);
		setSolidNode(6,1);
		
		// Set cost method, calculates costs (G, H, and F) for each node.
		setCostOnNodes();
		
	}
	/*Sets a specific node as the start node based on its column and row.
	Updates the startNode and currentNode properties.
	*/
	void setStartNode(int column, int row) {
		node[column][row].setAsStart();
		startNode = node[column][row];
		currentNode = startNode; //at the beginning of the program, current node is equal to start node
	}
	/*Sets a specific node as the goal node based on its column and row.
	Updates the goalNode property. 
	*/
	void setGoalNode(int column, int row) {
		node[column][row].setAsGoal();
		goalNode = node[column][row];
	}
	
	//Sets a specific node as a solid (obstacle) node based on its column and row.
	void setSolidNode(int column, int row) {
		node[column][row].setAsSolid();
	}
	
	/*Iterates through all nodes in the grid and calculates their G, H, and F costs.
	Calls the getCost(Node node) method for each node.
	*/
	void setCostOnNodes() {
		
		int column = 0;
		int row = 0;
		
		while (column < maxColumn && row < maxRow) {
			getCost(node[column][row]);
			column++;
			if(column == maxColumn) {
				column = 0;
				row++;
			}
		}	
	}
	
	/*Calculates the G cost (distance from start node), H cost (distance from goal node), and F cost (total cost) for a given node.
	Updates the text displayed on the node with the costs (excluding start and goal nodes).
	*/
	private void getCost(Node node) {
		
		// Get gCost (The distance from the start node)
		int xDistance = Math.abs(node.column - startNode.column);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost = xDistance + yDistance;
		
		// Get hCost (The distance from the goal node)
		xDistance = Math.abs(node.column - goalNode.column);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost = xDistance + yDistance;
		
		// Get fCost (The total cost)
		node.fCost = node.gCost + node.hCost;
		
		// Display the cost on node
		if (node != startNode && node != goalNode) {
			node.setText("<html>F:" + node.fCost + "<br>G:" + node.gCost + "</html>");
		}
	}
	
	/*Performs one step of the A* search algorithm manually.
	Checks neighboring nodes, updates their status, and selects the best node to move to.
	Stops when the goal node is reached or after a certain number of steps (to prevent infinite loops).
	*/
	public void search() {
		
		if(goalReached == false && step < 300) {
			
			int column = currentNode.column;
			int row = currentNode.row;
			
			currentNode.setAsChecked();
			checkedList.add(currentNode);
			openList.remove(currentNode);
			
			// Open the up node
			if (row - 1 >= 0) {
				openNode(node[column][row-1]);
			}
			
			// Open the left node
			if (column - 1 >= 0) {
				openNode(node[column-1][row]);
			}
			
			// Open the down node
			if (row + 1 < maxRow) {
				openNode(node[column][row+1]);
			}
			
			// Open the right node
			if(column + 1 < maxColumn) {
				openNode(node[column+1][row]);
			}
			
			// Find the best node
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for (int i =0; i < openList.size(); i++) {
				
				//Check if this node's fCost is better
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				//If fCost is equal, check the G cost
				else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			// After the loop, we get the best node which is our next step
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
			}
		}
		step++;
	}
	
	/*Continues the A* search algorithm automatically until the goal node is reached.
	Similar to search(), but automatically selects the best nodes to move to until the goal is found.
	Calls trackThePath() when the goal is reached to visualize the path.
	 */
	public void autoSearch() {
		
		while(goalReached == false) {
			
			int column = currentNode.column;
			int row = currentNode.row;
			
			currentNode.setAsChecked();
			checkedList.add(currentNode);
			openList.remove(currentNode);
			
			// Open the up node
			if (row - 1 >= 0) {
				openNode(node[column][row-1]);
			}
			
			// Open the left node
			if (column - 1 >= 0) {
				openNode(node[column-1][row]);
			}
			
			// Open the down node
			if (row + 1 < maxRow) {
				openNode(node[column][row+1]);
			}
			
			// Open the right node
			if(column + 1 < maxColumn) {
				openNode(node[column+1][row]);
			}
			
			// Find the best node
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for (int i =0; i < openList.size(); i++) {
				
				//Check if this node's F cost is better
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				//If F cost is equal, check the G cost
				else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			// After the loop, we get the best node which is our next step
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
				
				trackThePath();
			}
		}
	}
	/*Checks if a given node can be opened (added to the open list) based on certain conditions.
	If the node meets the criteria, it is set as open, and its parent is updated.
	*/
	private void openNode(Node node) {
		
		if (node.open == false && node.checked == false && node.solid == false) {
			
			// If the node is not opened yet, add it to the open list
			node.setAsOpen();
			node.parent = currentNode;
			openList.add(node);
		}
	}
	/*Backtracks from the goal node to the start node, marking the nodes along the best path.
	Sets nodes on the path as "path" nodes, changing their appearance to indicate the final path. 
	*/
	private void trackThePath() {
		
		// Backtrack and draw the best path
		Node current = goalNode;
		
		while(current != startNode) {
			
			current = current.parent;
			
			if (current != startNode) {
				current.setAsPath();
			}
		}
	}
}

