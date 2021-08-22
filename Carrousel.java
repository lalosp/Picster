import java.io.File;


class Carrousel {
	private final int START = 0;
	
	private File file;
	private String imagePath = System.getProperty("user.home") + "/Pictures/";
	private String[] images;
	private int index;
	private int end;
	
	public void setup() {
		file = new File(imagePath);
		images = file.list();
		index = 0;
		end = images.length - 1;
		
		// adds current dir to filename for one file chooser to work
		// foreach doesn't actually change the values in the array
		for (int i = 0; i <= end; i++) {
			images[i] = imagePath + images[i];
		}
	}
	
	public String getImage(String pos) {
		String fileName = "";
		
		if (pos == "start") {
			index = 0;
		} else if (pos == "next") {
			if (index == end) {
				index = START;
			} else {
				index++;
			}
		} else if (pos == "before") {
			if (index == START) {
				index = end;
			} else {
				index--;
			}
		}
		
		fileName = images[index];
		return fileName;
	}
	
	public String getFileName() {
		return images[index];
	}
	
	public void fillOneFile(String path, String filename) {
		imagePath = path;
		images = new String[1];
		images[0] = filename;
		index = 0;
		end = images.length - 1;
	}
	
	public void fillFromDir(String dirname) {
		imagePath = dirname;
		file = new File(imagePath);
		images = file.list();
		index = 0;
		end = images.length - 1;
		
		for (int i = 0; i <= end; i++) {
			images[i] = imagePath + "/" + images[i];
		}
	}
}
