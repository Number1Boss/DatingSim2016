import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Frame extends JFrame{
	public static void main(String[] args) throws AWTException{
		Frame frame = new Frame();
		frame.setDefaultCloseOperation(3);
		Board board = new Board();
		board.setFocusable(true);
		board.setPreferredSize(new Dimension(800, 600));
		frame.add(board);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}
}