/*This class represents individual nodes or cells on the grid.
Each node corresponds to a location in the grid and has properties 
such as its position, cost, type (start, goal, solid, etc.), and state (open, checked).
Nodes are displayed as buttons in the GUI, allowing user interaction.
*/

package a_Star_Pathfinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

// This node is a button, so it responds to your mouse click
public class Node extends JButton implements ActionListener{
	
	Node parent;
	int column;
	int row;
	int gCost;
	int hCost;
	int fCost;
	boolean start;
	boolean goal;
	boolean solid;
	boolean open;
	boolean checked;
	
	// Create constructor
	public Node(int column, int row) {
		
		this.column = column;
		this.row = row;
		
		// Set background and foreground color for this node, the text color
		setBackground(Color.white);
		setForeground(Color.black);
		addActionListener(this); //We implement the result of clicking this button
	}
	
	public void setAsStart() {
		setBackground(Color.blue); //start node color
		setForeground(Color.white); //for text
		setText("Start"); //display text
		start = true;
	}
	
	public void setAsGoal() {
		setBackground(Color.red); //goal node color
		setForeground(Color.white); //for text
		setText("Goal"); //display text
		goal = true;
	}
	
	public void setAsSolid() {
		setBackground(Color.black);
		setForeground(Color.black);
		solid = true;	
	}
	
	public void setAsOpen () {
		open = true;
	}
	
	public void setAsChecked() {
		if (start == false && goal == false) {
			setBackground(Color.orange);
			setForeground(Color.black);
		}
		checked = true;
	}
	
	public void setAsPath() {
		setBackground(Color.green);
		setForeground(Color.black);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Node clicked!"); //To check if the method is being triggered when you click a node. 
		// When the button is clicked, the background color is change
		setBackground(Color.orange);
		setForeground(Color.black);
	}
}