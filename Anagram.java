import java.util.*;
public class Anagram{
    public static void main(String[] args){
	String word = "abcdef";
	word = sort(word);
	ArrayList<String> combinations = combination(word, word.length(), 2);
	for(int i = 0; i < combinations.size(); i++){
	    System.out.println(combinations.get(i));
	}
	System.out.println("total:" + combinations.size());
    }

    public static String sort(String word){
	
    }
    
    //nCr
    //TODO: 今度staticじゃないのにする
    public static ArrayList<String> combination(String word, int n, int r){
	if(r == 0){
	    return null;
	}
	if(r == 1){
	    ArrayList<String> list = new ArrayList<String>();
	    for(int i = 0; i < n; i++){
		list.add(word.substring(i, i+1));
	    }
	    return list;
	}
	if(r == n){
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
	return allCombination;
    }

    //TODO: combinationと一緒に別のにもってく
    public static ArrayList<String> combine(String head, ArrayList<String> list){
	for(int i = 0; i < list.size(); i++){
	    String element = list.get(i);
	    list.set(i, head + element);
	}
	return list;
    }
}
