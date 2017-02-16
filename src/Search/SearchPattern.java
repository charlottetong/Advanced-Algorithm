package Search;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import computing.In;

public class SearchPattern {

	public static void main(String args[]) {
		// begin with
		String s = null;
		String pattern1 = "[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9]+(\\.(com|cn|org|edu|hk))";
		String pattern2 = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
		String pattern3 = "^web";
		String pattern4 = "tion$";
		// end with

		Scanner r = new Scanner(System.in);
		boolean test = true;
		while (test) {
			System.out.println("1.check email");
			System.out.println("2.check phone number");
			System.out.println("3.chech the word begin with web");
			System.out.println("4.chech the word end with web");
			System.out.println("5.Exit");
			String number = r.next();
			switch (number) {
			case "1":
				SearchPattern.checkWords(pattern1);
				break;
			case "2":
				SearchPattern.checkWords(pattern2);
				break;
			case "3":
				SearchPattern.checkWords(pattern3);
				break;
			case "4":
				SearchPattern.checkWords(pattern4);
			case "5":
				test = false;// Stop the loop.
				break;
			default:
				System.out.println("please enter the right number");
			}
		}

		// SearchPattern.checkWords(pattern, s);
		// SearchPattern.checkWords(pattern, s);

	}

	public static File[] getName(String path) {
		File file = new File(path);
		return file.listFiles();
	}

	public static void checkWords(String pattern) {
		String path = "new/";
		File[] array = getName(path);
		In in = new In();

		ArrayList<String> save = new ArrayList<String>();
		for (int ii = 0; ii < array.length; ii++) {
			String s = null;
			in = new In(array[ii]);
			if (!in.isEmpty()) {
				s = in.readAll();
			}

//			StringTokenizer st = new StringTokenizer(s);
//			// exact the word
//			while (st.hasMoreTokens()) {
//				save.add(st.nextToken());
//			}
			StringTokenizer str = new StringTokenizer(s, " \t\n\r\f,.:;?![]'-");
	        
	        while ( str.hasMoreTokens() )
	        {
	        	save.add(str.nextToken());
	         
	      	
	        }
			Pattern r = Pattern.compile(pattern);
			Matcher m;
			for (int i = 0; i < save.size(); i++) {
				m = r.matcher(save.get(i));
				while (m.find()) {
					System.out.println("Found value at  :" + array[ii].getName() + " :  " + i + "  " + save.get(i));
				}
			}

		}
	}
}
