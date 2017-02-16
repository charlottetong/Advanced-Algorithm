package Search;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import computing.In;
import computing.TST;

public class TSTHash {

	List<String> files = new ArrayList<String>();
	TST tst = new TST();

	public TST TSTC(Map<String, List> index) {

		return tst;
	}

	public void addTST(File file) {
		int pos = 0;
		String word = "web";
		In in = new In(file.getPath());
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String line = in.readLine(); line != null; line = in.readLine()) {
			for (String _word : line.split("\\W+")) {
				pos++;
				 word = _word.toLowerCase();
				tst.put(word, map);

			}
			map.put(file.getName(), pos);
	
		}

	}

	public void show(String s){
		Map m = (Map) tst.get(s);
		Set entries = m.entrySet( );
		if(entries != null) {
		   Iterator iterator = entries.iterator( );
		   while(iterator.hasNext( )) {
			 Map.Entry entry =(Entry) iterator.next( ); 
			 String key = (String)entry.getKey( ); 
			 int value = (Integer)entry.getValue();
	   		
		   System.out.println("key: "+ key+" , value :"+ value);
		   }
		}
		
		
	}

	public static void main(String agrs[]) {
		Index t = new Index();
		TSTHash th = new TSTHash();
		File[] files = t.getFiles("W3C Web Pages");
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();

		for (File file : files) {
			if (file.isFile())
				th.addTST(file);
		}

		th.show("web");

	}

}
