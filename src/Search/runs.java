package Search;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import computing.StdIn;
import computing.StdOut;

/** 
* @author  Louis Liu 
* @date Create Date：Dec 15, 2016 2:29:43 PM 
**/
public class runs {

	public static void main(String[] args) throws IOException {
		//extract information from html to text
//		htmlText.main(args);
		
		//create inverted index
		Index index = new Index();
		File[] files = Index.getFiles("new");
		for (File file : files) {
			if (file.isFile())
				index.addFileToIndex(file);
		}
		StdOut.println("Please input keyword(s).");
		while (StdIn.hasNextLine()) {
			String source = StdIn.readLine();
			if (source.equals("exit")) {
				StdOut.println("Bye...");
				break;
			}else if(source.length()<=0){
				StdOut.println("Please input a keyword");
				continue;
			}
			String[] keywords = source.split("\\W+");
			HashMap<String,Integer> result = index.search(keywords[0]);
			
			if (result.size()==0) {
				StdOut.println("Not web page matches such a keyword.");
				continue;
			} 
			//If have more than one source separated by '/'
			for (int i = 1; i < keywords.length; i++) {
				HashMap<String,Integer> result2 =  index.search(keywords[i]);
				if (result2.size()>0) {
					HashMap<String,Integer> result3 =  new HashMap<String, Integer>();
					Iterator iter = result2.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						Object key = entry.getKey();
						if(result.containsKey(key))
							result3.put(key.toString(), result.get(key));
					}
					result = (HashMap<String, Integer>) result3.clone();
				} else {
					StdOut.println("Cannot find web page contains secondary keyword '" + keywords[i] + "'\n");
				}
			}
			
			
			//Serialize the result and print it on the screen;
			List<Entry<String, Integer>> list = Index.mapToList(result);
			int size = list.size()>10?10:list.size();
			for (int i = 0; i < size; i++) {
//				String id = list.get(i).getKey().replaceAll("txt", "html").toString();
				String id = list.get(i).toString().replaceAll("txt", "html");
				System.out.println(id);
			}
			StdOut.println("Please input keyword(s).");
		}
	}

}
