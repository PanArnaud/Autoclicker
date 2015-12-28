/*
	Author: Arnaud Panapadéatchy
	GitHub: ArnaudPan
	Website: http://ar-pa.esy.es
	Version: 1.0
*/

import java.awt.AWTException;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Autoclicker extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	
	private static Robot robot = null;
	private static int time;
	static JTextField textField; 
	static JButton valid;

	public static void main(String[] args) {
		new Autoclicker();
	}
	
	public Autoclicker(){
		this.setTitle("Ma fenêtre");
	    this.setSize(270, 100);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	    Container container = this.getContentPane();
	    container.setLayout(new GridLayout(2, 1));
	    
	    JPanel header = new JPanel();
	    container.add(header);
	    header.add(new JLabel("Choisissez la durée de clics (en Secondes)"));
	    
	    JPanel pan = new JPanel();
	    container.add(pan);
	    pan.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));

	    pan.add(new JLabel("Durée:"));
	    textField = new JTextField(5);
	    pan.add(textField);
	    
	    valid = new JButton("Envoyer");
	    pan.add(valid);
		valid.addMouseListener(this);
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
	    this.setVisible(true);
		
		try{
			robot = new Robot();
		} catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	public static Point getCoordinates(){
		PointerInfo pointer = MouseInfo.getPointerInfo();
		Point location = pointer.getLocation();
		return location;
	}
	
	public void dialog(){
		JOptionPane.showMessageDialog(this, "Placez votre souris à l'endroit souhaité, et appuyez sur Entrée", "Attention",
			    JOptionPane.PLAIN_MESSAGE);
	
		Point coordinates = getCoordinates();

		int i = 1;  
		while(i != (1000 / 25) * time){
			click(coordinates.x, coordinates.y);
			i++;
		}
	}
	
	public static void click(int x, int y){
		robot.mouseMove(x,  y);
		robot.delay(25);
		robot.mousePress(MouseEvent.BUTTON1_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_MASK);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == valid){
			try {
				time =  Integer.parseInt(textField.getText());
				dialog();
			} catch (Exception err) {
				System.exit(0);
			}
		}
	}
}
