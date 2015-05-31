import java.util.*;

public class FindAnagram{
    HashMap<String, ArrayList<String>> hashDictionary;
    
    void FindAnagram(HashMap<String, ArrayList<String>> hashDictionary){
	this.hashDictionary = hashDictionary;
    }

    public String findAnagram(String word){
	for(int i = word.length(); i > 0; i--){
	    ArrayList<String> combinations = combination(word, word.length(), i);
	    for(int j = 0; j < combinations.size(); j++){
		String anagram = combinations.get(j);
		if(isMatch(anagram)){
		    return hashDictionary.get(anagram).get(0);
		}
	    }
	}
	return "NO RESULTS";
    }
    
    // nCr
    private ArrayList<String> combination(String word, int n, int r){
	if(r == 1){
	    ArrayList<String> list = new ArrayList<String>();
	    for(int i = 0; i < n; i++){
		list.add(word.substring(i, i+1));
	    }
	    return list;
	}
	if(r == n || r == 0){
	    ArrayList<String> list = new ArrayList<String>();
	    list.add(word);
	    return list;
	}
	ArrayList<String> list1 = new ArrayList<String>();
	String head = word.substring(0, 1);
	list1 = combination(word.substring(1), n-1, r-1);
	list1 = combine(head, list1);
	ArrayList<String> list2 = new ArrayList<String>();
	head = word.substring(0, 1);
	list2 = combination(word.substring(1), n-1, r);
	ArrayList<String> allCombination = new ArrayList<String>();
	allCombination.addAll(list1);
	allCombination.addAll(list2);
	allCombination = removeDuplication(allCombination);
	return allCombination;
    }

    private ArrayList<String> combine(String head, ArrayList<String> list){
	for(int i = 0; i < list.size(); i++){
	    String element = list.get(i);
	    list.set(i, head + element);
	}
	return list;
    }

    private ArrayList<String> removeDuplication(ArrayList<String> list){
	ArrayList<String> uniqueCombination = new ArrayList<String>();
	for(int i = 0; i < list.size(); i++){
	    String element = list.get(i);
	    if(uniqueCombination.contains(element)){
		list.remove(element);
	    } else {
		uniqueCombination.add(element);
	    }
	}
	return uniqueCombination;
    }

    private boolean isMatch(String word){
	return hashDictionary.containsKey(word);
    }
}
