package Search;

import computing.TST;
import computing.StdOut;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import computing.In;

public class Search {

	public static void main(String args[]) {
		In in = new In("Text.txt");
		String s = null;
		if (!in.isEmpty()) {
			s = in.readAll();
		}
		
		TST<Integer> tst = new TST<Integer>();
		tst = TST(s);
		try{
		String tem = "one";
		int offset = tst.get(tem);
		System.out.println(offset);
		}catch(NullPointerException c){
			System.out.println("this word doesn't exist in the text");
		}
	}
	public static TST TST(String s){
		ArrayList<String> save = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(s);
		// exact the word
		while (st.hasMoreTokens()) {
			save.add(st.nextToken());
		}
		TST<Integer> tst = new TST<Integer>();
		for (int i = 0; i < save.size(); i++) {
			// String key = In.readString();
			tst.put(save.get(i), i);
		}
		return tst;
	}

}