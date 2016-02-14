import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.IllegalComponentStateException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	Graphics2D drawer;
	ImageIcon screen;
	Timer time;
	Point mouse;
	Robot r;
	boolean attemptedEscape = false;
	public Board() throws AWTException{
		System.out.println("Sup");
		screen = new ImageIcon(Board.class.getResource("Resources/Titlescreen.png"));
		screen.setDescription("title");
		time = new Timer(20, this);
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("PRESSED");
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("PRESSED");
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("PRESSED");
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_Q)
					System.exit(0);
			}
		});
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(screen.getDescription().equals("title")){
					if (isWithin(244, 545, 285, 365)){
						screen = new ImageIcon(Board.class.getResource("Resources/Friendzoned.png"));
						screen.setDescription("friendzone");
					}
					if(isWithin(242, 532, 384, 454)){
						screen = new ImageIcon(Board.class.getResource("Resources/Friendzoned-quit.png"));
						screen.setDescription("friendzone-quit");
					}
				}
				
				if(screen.getDescription().equals("friendzone")){
					if (isWithin(685,793,555,592)){
						screen = new ImageIcon(Board.class.getResource("Resources/Friendzoned-quit.png"));
						screen.setDescription("friendzone-quit");
					}
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		r= new Robot();
		time.start();
	}
	
	public void paint(Graphics g){
		drawer = (Graphics2D) g;
		drawer.drawImage(screen.getImage(), 0, 0, 800, 600, null);
		if(attemptedEscape){
			drawer.setColor(Color.WHITE);
			drawer.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			drawer.drawString("Lol if you really want to quit just click on the window and press ESC or q", 50, 50);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try{
		updateMouse();
		}catch(IllegalComponentStateException f){ System.out.println("dumb mouse");}
		repaint();
		//System.out.println(mouse);
	}

	private void updateMouse() {
		// TODO Auto-generated method stub
		Point mSc = MouseInfo.getPointerInfo().getLocation();
		Point bL = this.getLocationOnScreen();
		mouse = new Point((int)(mSc.getX()-bL.getX()),(int)(mSc.getY()-bL.getY()));
		if(isWithin(649, 802, -32, 5) && screen.getDescription().equals("friendzone-quit")){
			r.mouseMove(this.getLocationOnScreen().x + this.getWidth()/2, this.getLocationOnScreen().y + this.getHeight()/2);
			attemptedEscape = true;
		}
	}
	private boolean isWithin(int x1,int x2,int y1,int y2){
		if(mouse.getX()>=x1 && mouse.getX()<=x2 && mouse.getY()>=y1 && mouse.getY()<=y2)
			return true;
		return false;
	}
}
