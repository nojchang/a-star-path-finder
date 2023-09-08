/*This class implements the KeyListener interface to handle keyboard events.
It is responsible for listening to the "Enter" key press to trigger 
the A* pathfinding search (autoSearch method) when pressed.
*/

package a_Star_Pathfinder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	Panel keyPanel;
	
	//constructor of the KeyHandler class.
	public KeyHandler(Panel keyPanel) {
		this.keyPanel = keyPanel;
	}
	
	/*an overridden method from the KeyListener interface. It's not used in this implementation 
	and is left empty ({}). This method is called when a key is typed (pressed and released), 
	but it is not relevant for triggering the pathfinding search.
	*/
	@Override
	public void keyTyped(KeyEvent e) {}
		
	//another overridden method from the KeyListener interface. It's called when a key is pressed down. In this method:
	@Override
	public void keyPressed(KeyEvent e) {
		//retrieves the key code of the pressed key using e.getKeyCode().
		int code = e.getKeyCode();
		//checks if the key code corresponds to the "Enter" key using KeyEvent.VK_ENTER.
		if(code == KeyEvent.VK_ENTER) {
			
			//Uncomment the line below to performs one step of the A* search algorithm at a time.
			//keyPanel.search();
			
			/*If the "Enter" key is pressed, it calls the autoSearch() method of the Panel. 
			This is the action triggered when the "Enter" key is pressed, 
			initiating the A* pathfinding search.
			*/
			keyPanel.autoSearch();
		}		
	}
	/*another overridden method from the KeyListener interface. It's called when a key is released 
	after being pressed down. It is left empty ({}) in this implementation, as it is not used 
	for the specific functionality of triggering pathfinding.
	*/
	@Override
	public void keyReleased(KeyEvent e) {
	}
}
