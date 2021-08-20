import java.io.File;


class Carrousel {
	private final String IMAGE_PATH = "/home/lalospx/Pictures/me/";
	private final int START = 0;
	
	private File file;
	private String[] images;
	private int index;
	private int end;
	
	public void setup() {
		file = new File(IMAGE_PATH);
		images = file.list();
		index = 0;
		end = images.length - 1;
	}
	
	public String getImage(String pos) {
		String fileName = "";
		
		if (pos == "start") {
			index = 0;
		}
		if (pos == "next") {
			if (index == end) {
				index = START;
			} else {
				index++;
			}
		}
		if (pos == "before") {
			if (index == START) {
				index = end;
			} else {
				index--;
			}
		}
		
		fileName = images[index];
		return IMAGE_PATH + fileName;
	}
	
	public String getFileName() {
		return images[index];
	}
}
