package Search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import computing.In;
import computing.TST;

public class Index {

	// Words passed over in the inverted index;
	List<String> stopwords = Arrays.asList("a", "able", "about", "across", "after", "all", "almost", "also", "am",
			"among", "an", "and", "any", "are", "as", "at", "be", "because", "been", "but", "by", "can", "cannot",
			"could", "dear", "did", "do", "does", "either", "else", "ever", "every", "for", "from", "get", "got", "had",
			"has", "have", "he", "her", "hers", "him", "his", "how", "however", "i", "if", "in", "into", "is", "it",
			"its", "just", "least", "let", "like", "likely", "may", "me", "might", "most", "must", "my", "neither",
			"no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our", "own", "rather", "said",
			"say", "says", "she", "should", "since", "so", "some", "than", "that", "the", "their", "them", "then",
			"there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "we", "were", "what",
			"when", "where", "which", "while", "who", "whom", "why", "will", "with", "would", "yet", "you", "your");

	// private class saving the words' location
	class Tuple {
		// The serial number of a index which reference to the files;
		private int fileNo;
		// The location of a word
		private int position;

		Tuple(int fileNo, int position) {
			this.fileNo = fileNo;
			this.position = position;
		}
		public int getFileNo() {
			return fileNo;
		}

		public int getPosition() {
			return position;
		}

	}

	// inverted index of words
	Map<String, List<Tuple>> index = new HashMap<String, List<Tuple>>();
	// list of true path of files
	List<String> files = new ArrayList<String>();

	public void addFileToIndex(File file) {
		// put the path of a file into the List:files and get a file number;
		int fileNo = files.indexOf(file.getPath());
		if (fileNo == -1) {
			files.add(file.getPath());
			fileNo = files.size() - 1;
		}

		// position of a word in a file;
		int pos = 0;
		In in = new In(file.getPath());
		for (String line = in.readLine(); line != null; line = in.readLine()) {
			for (String _word : line.split("\\W+")) {
				pos++;
				String word = _word.toLowerCase();
				if (stopwords.contains(word))
					continue;
				// Linked list about the position of words in the files
				List<Tuple> idx = index.get(word);
				if (idx == null) {
					idx = new LinkedList<Tuple>();
					index.put(word, idx);
				}

				idx.add(new Tuple(fileNo, pos));
			}
		}
	}

	// Search files contain keywords and its frequency
	public HashMap<String, Integer> search(String _keyword) {
			String word = _keyword.toLowerCase();
			HashMap<String, Integer> outcome = new HashMap<String, Integer>();
			String fileName = null;

			List<Tuple> idx = index.get(word);
			if (idx != null) {
				for (Tuple t : idx) {
					fileName = files.get(t.fileNo);
					// Put the file names into the outcome list
					outcome.put(fileName, outcome.get(fileName) == null ? 1 : outcome.get(fileName) + 1);
				}
			}

//			System.out.println(word);

//			Iterator iter = outcome.entrySet().iterator();
//			while (iter.hasNext()) {
//				Map.Entry entry = (Map.Entry) iter.next();
//				Object key = entry.getKey();
//				Object val = entry.getValue();
//				System.out.println(" file: " + key + " frequency: " + val);
//			}

			return outcome;
	}

	public static List<Entry<String, Integer>> mapToList(HashMap<String, Integer> map){
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});

//		for (int i = 0; i < list.size(); i++) {
//			String id = list.get(i).toString();
//			System.out.println(id + "  ");
//		}
		
		return list;
	}
	
	public static File[] getFiles(String path) {
		File file = new File(path);
		File[] tempList = file.listFiles();
		// System.out.println("Quantity of the File is " + tempList.length);
		return tempList;
	}

	public static void main(String[] args) {
		Index index = new Index();
		File[] files = getFiles("content/W3C Web Pages/");
		for (File file : files) {
			if (file.isFile())
				index.addFileToIndex(file);
		}
		String keyword = "web";
		index.search(keyword);
	}
}
