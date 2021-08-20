import javax.swing.JFrame;


public class TestGUI extends JFrame {
	public void setup() {
		setSize(1000, 700);
	}
	
	public void run() {
		setVisible(true);
	}
	
	public static void main(String[] args) {
		TestGUI gui = new TestGUI();
		gui.setup();
		gui.run();
	}
}
