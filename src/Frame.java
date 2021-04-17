import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame extends JFrame{
	Panel panel;
	
	Frame(){
		panel = new Panel();
		add(panel);
		
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		
		addMouseMotionListener(new MyMouseMotion());
		addMouseListener(new MyMouse());
	}
	
	public class MyMouseMotion implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			if(e.getX() >= panel.game.computerLeftIndent && e.getX() <= panel.game.computerLeftIndent + panel.game.cellSize * panel.game.fieldSize 
					&& e.getY() >= panel.game.topIndent && e.getY() <= panel.game.topIndent + panel.game.cellSize * panel.game.fieldSize) {
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			}else {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
		}
		
		
	}	
	public class MyMouse implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getX() >= panel.game.computerLeftIndent && e.getX() <= panel.game.computerLeftIndent + panel.game.cellSize * panel.game.fieldSize 
					&& e.getY() >= panel.game.topIndent && e.getY() <= panel.game.topIndent + panel.game.cellSize * panel.game.fieldSize) {
				panel.game.playerTurn(e.getX(), e.getY());
				panel.repaint();
			}
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}

