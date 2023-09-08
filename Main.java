/* The program creates a graphical window using the Swing library (JFrame). 
 * This window will serve as the interface for displaying the pathfinding demonstration.
 */

package a_Star_Pathfinder;

/*Imports the JFrame class from the javax.swing package. 
JFrame is a class that allows you to create a graphical window for GUI applications.
*/
import javax.swing.JFrame;

public class Main {
	
	public static void main (String[] args) {
	
		//creates a new instance of the JFrame class and assigns it to the variable window. It is the main graphical window of the program.
		JFrame window = new JFrame();
		//sets the default close operation for the window to JFrame.EXIT_ON_CLOSE. This means that when the user closes the window, the program will exit.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//prevents the user from resizing the window, ensuring that it remains a fixed size.
		window.setResizable(false);
		//adds a new instance of the Panel class to the window. Panel is a component that will be displayed within the window and contains the A* pathfinding.
		window.add(new Panel());
		//causes the window to automatically adjust its size to fit its contents (Panel).
		window.pack();
		//centers the window on the screen, so it appears in the middle of the screen.
		window.setLocationRelativeTo(null);
		//sets the window to be visible, making it appear on the screen and allowing the user to interact with it.
		window.setVisible(true);
	}
}