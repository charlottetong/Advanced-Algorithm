package Search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WebSearch {

	public static void main (String args[]){
		
	}
	
	private class ValueComparator implements Comparator<Map.Entry<String, Integer>>  
    {  
        public int compare(Map.Entry<String, Integer> mp1, Map.Entry<String, Integer> mp2)   
        {  
            return mp1.getValue() - mp2.getValue();  
        }  
    }  
	public List<String> sortMapByValue(HashMap<String,Integer> map)  
    {  
        int size = map.size();  
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(size);  
        list.addAll(map.entrySet());  
        ValueComparator vc = new ValueComparator();  
        Collections.sort(list, vc);  
        final List<String> keys = new ArrayList<String>(size);  
        for (int i = 0; i < size; i++) {  
            keys.add(i, list.get(i).getKey());  
        }  
        return keys;      
    }  

}
