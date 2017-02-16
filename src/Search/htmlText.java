package Search;

import java.io.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

public class htmlText extends HTMLEditorKit.ParserCallback {
	StringBuffer s;

	public htmlText() {
	}

	// parse string
	public void parse(Reader in) throws IOException {
		s = new StringBuffer();
		ParserDelegator delegator = new ParserDelegator();
		// the third parameter is TRUE to ignore charset directive
		delegator.parse(in, this, Boolean.TRUE);
	}

	// append string
	public void handleText(char[] text, int pos) {
		s.append(text);
	}

	// get text
	public String getText() {
		return s.toString();
	}

	// find files in a folder
	public static void findFiles(String filePath) throws IOException {
		File path = new File(filePath);
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isFile()) {
				file.getAbsolutePath();
			} else if (file.isDirectory()) {
				findFiles(file.getAbsolutePath());
			}
		}

	}

	public static void convert(File file, String text, String outputFile) {
		try {
			// convert html to text
			FileReader in = new FileReader(file);
			htmlText parser = new htmlText();
			parser.parse(in);
			text = parser.getText();
			// System.out.println(parser.getText());

			// write text to file
			File newTextFile = new File(outputFile);
			FileWriter fw = new FileWriter(newTextFile);
			fw.write(text);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {

		String filePath = "W3C Web Pages";
		File path = new File(filePath);
		File[] files = path.listFiles();
		String[] fns = new String[path.listFiles().length];
		String txt = null;

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			// if a file exists
			if (file.isFile()) {
				// get the name of the file
				fns[i] = file.getName();
				// convert html to text
				convert(file, txt, "new/" + file.getName().replace(".htm", ".txt"));
				// find the absolutePath of the file
			} else if (file.isDirectory()) {
				findFiles(file.getAbsolutePath());
			}
		}
		System.out.println("HTMLtoText converter is finished.");
	}

}
