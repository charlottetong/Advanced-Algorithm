package Search;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Search.Index.Tuple;
import computing.TST;

import java.util.Map.Entry;

public class Tst {
	public static void main(String agrs[]) {
		Index t = new Index();
		File[] files = t.getFiles("W3C Web Pages");
		for(File file:files){
			if(file.isFile())
				t.addFileToIndex(file);
		}
		TST tst = new TST();
		Tst tt = new Tst();
		tt.getTST(t.index,tst);
		List list = (List) tst.get("web");
		tt.show(list);
	}

	public void getTST(Map m,TST t) {
		Set entries = m.entrySet();
		if (entries != null) {
			Iterator iterator = entries.iterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Entry) iterator.next();
				String key = (String) entry.getKey();
				List value =  (List) entry.getValue();
				if(key.length()>=1){
				t.put(key, value);
				}
			}
		}
	}
	public void show(List list){
		for(int i = 0; i<list.size();i++){
			Tuple t = (Tuple) list.get(i);
			System.out.println("File Number: "+t.getFileNo()+" Position: "+ t.getPosition());
		}
	}
	

}
