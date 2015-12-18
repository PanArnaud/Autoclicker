import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;

public class Autocliker {
	private static Robot robot = null;

	public static void main(String[] args) {
		try{
			robot = new Robot();
		} catch(AWTException e){
			e.printStackTrace();
		}
		
		int i = 1;  
		
		while(i != 100){
			click(800, 450);
			i++;
		}
			
		
		
	}

	public static void click(int x, int y){
		robot.mouseMove(x,  y);
		robot.delay(25);
		robot.mousePress(MouseEvent.BUTTON1_MASK);
		robot.mouseRelease(MouseEvent.BUTTON1_MASK);
	}
}
