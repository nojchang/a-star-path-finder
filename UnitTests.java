package a_Star_Pathfinder;

public class UnitTests {

    public static void main(String[] args) {
        testSetStartNode();
        testSetGoalNode();
        testSetSolidNode();
        testSetCostOnNodes();
        testSearchMethod();
    }

    public static void testSetStartNode() {
        Panel panel = new Panel();
        Node startNode = panel.startNode;

        if (startNode != null && startNode.start && !startNode.goal && !startNode.solid) {
            System.out.println("testSetStartNode passed");
        } else {
            System.out.println("testSetStartNode failed");
        }
    }

    public static void testSetGoalNode() {
        Panel panel = new Panel();
        Node goalNode = panel.goalNode;

        if (goalNode != null && !goalNode.start && goalNode.goal && !goalNode.solid) {
            System.out.println("testSetGoalNode passed");
        } else {
            System.out.println("testSetGoalNode failed");
        }
    }

    public static void testSetSolidNode() {
        Panel panel = new Panel();
        Node solidNode = panel.node[9][2];

        if (solidNode != null && !solidNode.start && !solidNode.goal && solidNode.solid) {
            System.out.println("testSetSolidNode passed");
        } else {
            System.out.println("testSetSolidNode failed");
        }
    }

    public static void testSetCostOnNodes() {
        Panel panel = new Panel();
        panel.setCostOnNodes();

        Node node = panel.node[2][2]; // Example node for testing
    
        if (node.gCost == 5 && node.hCost == 13 && node.fCost == 18) {
            System.out.println("testSetCostOnNodes passed");
        } else {
            System.out.println("testSetCostOnNodes failed");
        }
    }
    
    public static void testSearchMethod() {
        Panel panel = new Panel();

        // Set up a test scenario (customize it based on your needs)
        panel.setStartNode(0, 0);
        panel.setGoalNode(3, 3);
        panel.setSolidNode(1, 1);
        panel.setSolidNode(2, 2);

        // Perform one step of the search algorithm
        panel.search();

        // Assert or check the state of nodes after the search step
        Node startNode = panel.node[0][0];
        Node goalNode = panel.node[3][3];
        Node solidNode1 = panel.node[1][1];
        Node solidNode2 = panel.node[2][2];

        if (startNode.checked && !startNode.open &&
            !goalNode.checked && !goalNode.open &&
            solidNode1.solid && !solidNode1.checked && !solidNode1.open &&
            solidNode2.solid && !solidNode2.checked && !solidNode2.open) {
            System.out.println("testSearchMethod passed");
        } else {
            System.out.println("testSearchMethod failed");
        }
    }
}