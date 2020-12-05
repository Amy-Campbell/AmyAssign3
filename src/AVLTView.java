
//Amy Campbell
//id: 611919

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class AVLTView extends BTView {

	AVLTView(BinarySearchTree<Integer> tree) {
		super(tree);
		// TODO Auto-generated constructor stub

	}

	private double radius = 30; // Tree node radius
	private double vGap = 70; // Gap between two levels in a tree

	/** Display a subtree rooted at position (x, y) */
	@Override
	protected void displayTree(BTNodeInt<Integer> root, double x, double y, double hGap) {
		//BTNodeInt<Integer> newRoot = new AVLTreeNode<Integer>(root);
		if (root.getLeft() != null) {
			// Draw a line to the left node
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			// Draw the left subtree recursively
			displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2);
		}

		if (root.getRight() != null) {
			// Draw a line to the right node
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			// Draw the right subtree recursively
			displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2);
		}

		// Display a node
		
		Circle circle = new Circle(x, y, radius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		getChildren().addAll(circle, new Text(x - 4, y + 4, root.getElement() + "(" + ((AVLTreeNode<Integer>)root).getBalanceFactor() + ")"));
	}
}
