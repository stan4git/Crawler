package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
	private final static String DEFAULT_PATH = "C:\\temp\\";
	private static long fileCounter = 0L;
	
	public static void storeHTML(String pageContent) throws IOException {
		File file = new File(DEFAULT_PATH + fileCounter + ".html");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		for (int i = 0; i < pageContent.length(); i++) {
			out.write(pageContent.charAt(i));
		}
		fileCounter++;
		out.flush();
		out.close();
	}
}
