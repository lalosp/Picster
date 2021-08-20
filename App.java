import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Image;


// TODO: scale each image to the window dimensions
// TODO: add cropping
// TODO: streamline folder discovery on linux
public class App extends JFrame implements KeyListener {
	private final String APP_NAME = "Picster";
	
	private ImageIcon icon;
	private JLabel label;
	private Carrousel images;
	private int fWidth;
	private int fHeight;
	
	public void setup() {
		fWidth = 1000;
		fHeight = 600;
		setSize(fWidth, fHeight);
		images = new Carrousel();
		images.setup();
		icon = scaleImage(new ImageIcon(images.getImage("start")), fWidth, fHeight);
		label = new JLabel(icon);
		add(label);
		addKeyListener(this);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(APP_NAME + " | " + images.getFileName());
		pack();
	}
	
	public void run() {
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
		String key = e.getKeyText(e.getKeyCode());
		if (key == "Left") {
			label.setIcon(scaleImage(new ImageIcon(images.getImage("before")),fWidth ,fHeight));
		}
		if (key == "Right") {
			label.setIcon(scaleImage(new ImageIcon(images.getImage("next")),fWidth ,fHeight ));
		}
		
		setTitle(APP_NAME + " | " +images.getFileName());
	}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	// Fix scaling problems
	public ImageIcon scaleImage(ImageIcon icon, int windowWidth, int windowHeight) {
		int imgWidth = icon.getIconWidth();
		int imgHeight = icon.getIconHeight();
		
		int w2hRatio = imgWidth / imgHeight;
		int h2wRatio = imgHeight / imgWidth;
		
		if (imgWidth > imgHeight) {
			if (imgWidth > windowWidth) {
				imgWidth = windowWidth;
				imgHeight = imgWidth * w2hRatio;
			}
		}
		if (imgHeight > imgWidth) {
			if (imgHeight > windowHeight) {
				imgHeight = windowHeight;
				imgWidth = imgHeight * h2wRatio;
			}
		}
		return new ImageIcon(icon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.setup();
		app.run();
	}
}
