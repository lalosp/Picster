import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFileChooser;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.io.File;

import static javax.swing.JOptionPane.showMessageDialog;


// TODO: fix image scaling issues
// TODO: add cropping
public class App extends JFrame implements KeyListener, ActionListener {
	private final String APP_NAME = "Picster";
	
	// menubar
	private final JMenuBar menubar = new JMenuBar();
	
	// menus
	private final JMenu menuFile = new JMenu("File");
	private final JMenu menuEdit = new JMenu("Edit");
	private final JMenu menuHelp = new JMenu("Help");
	
	// menu items
	private final JMenuItem miOpenFile = new JMenuItem("Open File");
	private final JMenuItem miOpenDirectory = new JMenuItem("Open Directory");
	private final JMenuItem miSave = new JMenuItem("Save");
	private final JMenuItem miCrop = new JMenuItem("Crop");
	private final JMenuItem miAbout = new JMenuItem("About");
	
	private ImageIcon icon;
	private JLabel label;
	private Carrousel images;
	private int fWidth;
	private int fHeight;
	private JFileChooser fileChooser;
	
	int counter = 0;
	
	public void setup() {
		fWidth = 1000;
		fHeight = 600;
		images = new Carrousel();
		images.setup();
		icon = scaleImage(new ImageIcon(images.getImage("start")), fWidth, fHeight);
		label = new JLabel(icon);
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Pictures/"));
		
		// actiolisteners for menu items
		miOpenFile.addActionListener(this);
		miOpenDirectory.addActionListener(this);
		miSave.addActionListener(this);
		miCrop.addActionListener(this);
		miAbout.addActionListener(this);
		
		// adds menuitems to menu
		menuFile.add(miOpenFile);
		menuFile.add(miOpenDirectory);
		menuFile.add(miSave);
		
		menuEdit.add(miCrop);
		
		menuHelp.add(miAbout);
		
		// add menu to menubar
		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuHelp);
		
		// add menubar to frame
		setJMenuBar(menubar);
		
		add(label);
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(APP_NAME + " | " + images.getFileName());
		pack();
		setSize(fWidth, fHeight);
		setLocationRelativeTo(null);
	}
	
	public void run() {
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e) {
		String key = e.getKeyText(e.getKeyCode());
		if (key == "Left") {
			label.setIcon(scaleImage(new ImageIcon(images.getImage("before")),fWidth ,fHeight));
		} else if (key == "Right") {
			label.setIcon(scaleImage(new ImageIcon(images.getImage("next")),fWidth ,fHeight ));
		}
		
		setSize(fWidth, fHeight);
		setTitle(APP_NAME + " | " + images.getFileName());
	}
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	// Fix scaling problems
	public ImageIcon scaleImage(ImageIcon icon, int winWidth, int winHeight) {
		float imgWidth = icon.getIconWidth();
		float imgHeight = icon.getIconHeight();
		
		float w2hRatio = imgHeight / imgWidth;
		float h2wRatio = imgWidth / imgHeight;
		
		// scaling code goes here
		imgHeight = winHeight;
		imgWidth = imgHeight * h2wRatio;
		
		return new ImageIcon(icon.getImage().getScaledInstance((int) imgWidth, (int) imgHeight, Image.SCALE_DEFAULT));
	}
	
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if (action == "About") {
			showMessageDialog(null, "Picster by Eduardo Sandoval");
		} else if (action == "Open File") {
			fileChooser.setDialogTitle("Choose file");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				images.fillOneFile(System.getProperty("user.home") + "/Pictures/", selectedFile.toString());
				
				label.setIcon(scaleImage(new ImageIcon(images.getImage("current")),fWidth ,fHeight ));
				setSize(fWidth, fHeight);
				setTitle(APP_NAME + " | " + images.getFileName());
			}
		} else if (action == "Open Directory") {
			fileChooser.setDialogTitle("Choose directory");
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			//fileChooser.setAcceptAllFileFilterUsed(false);
			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				images.fillFromDir(selectedFile.toString());
				
				label.setIcon(scaleImage(new ImageIcon(images.getImage("start")),fWidth ,fHeight ));
				setSize(fWidth, fHeight);
				setTitle(APP_NAME + " | " + images.getFileName());
			}
		} else if (action == "Save") {
			
		} else if (action == "Crop") {
			
		}
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.setup();
		app.run();
	}
}
