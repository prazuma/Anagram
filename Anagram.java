import java.util.*;
import java.io.*;

public class Anagram{
    public static void main(String[] args){
	
	String fileName = "/usr/share/dict/words";
	File file = new File("dictionary.txt");
        if(!(file.exists())){
	    Setup setup = new Setup();
	    setup.createDictionaryFile(fileName);
	}

	//read dictionary.txt
	ArrayList<String> textList = readFile("dictionary.txt");

	//dictionary.txt -> HashMap
	HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();
	hashDictionary = setHashMap(textList);
	Scanner sc = new Scanner(System.in);
	while(true){
	    System.out.print("16 characters: ");
	    String word = sc.nextLine();
	    word = sort(word);
	    String result = findAnagram(word, hashDictionary);
	    System.out.println(result);
	}
    }

    public static void print(ArrayList<String> list){
	for(int i = 0; i < list.size(); i++){
	    System.out.println(list.get(i));
	}
    }

    public static ArrayList<String> readFile(String fileName){
	ArrayList<String> list = new ArrayList<String>();
	try {
	    FileReader fr = new FileReader(fileName);
	    BufferedReader br = new BufferedReader(fr);
	    String str;
	    while((str = br.readLine()) != null){
		list.add(str);
	    }
	    br.close();
	} catch (IOException e) {
	    System.out.println("IOException: " + e);
	}
	return list;
    }
    
    public static HashMap<String, ArrayList<String>> setHashMap(ArrayList<String> textList){
	HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();
	int i = 0;
	String key = "";
	ArrayList<String> elements = new ArrayList<String>();
	String element = "";
	boolean isKey = false;
	while(i < textList.size()){
	    element = textList.get(i++);
	    if(element.equals("0")){
		isKey = true;
		if(!(key.equals(""))){
		    ArrayList<String> copy = new ArrayList<String>();
		    copy.addAll(elements);
		    hashDictionary.put(key, copy);
		    elements.clear();
		    key = "";
		}
		continue;
	    }
	    if(isKey){
		key = element;
		isKey = false;
	    } else {
		elements.add(element);
	    }
	}
	return hashDictionary;
    }

    public static String sort(String word){
	char[] letters = word.toCharArray();
	Arrays.sort(letters);
	String sortedWord = String.valueOf(letters);
	sortedWord = trimSpace(sortedWord);	
	return sortedWord;
    }

    public static String trimSpace(String word){
	if(word.length() == 1) return word;
	int lastIndex = 0;
	if(word.startsWith(" ")){
	    lastIndex = word.lastIndexOf(" ");
	    word = word.substring(lastIndex + 1);
	}
	return word;
    }
    
    //nCr
    public static ArrayList<String> combination(String word, int n, int r){
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

    public static ArrayList<String> removeDuplication(ArrayList<String> list){
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

    //TODO: combinationと一緒に別のにもってく
    public static ArrayList<String> combine(String head, ArrayList<String> list){
	for(int i = 0; i < list.size(); i++){
	    String element = list.get(i);
	    list.set(i, head + element);
	}
	return list;
    }

    public static boolean isMatch(String word, HashMap<String,ArrayList<String>> hashDictionary){
	return hashDictionary.containsKey(word);
    }

    public static String findAnagram(String word, HashMap<String, ArrayList<String>> hashDictionary){
	for(int i = word.length(); i > 0; i--){
	    ArrayList<String> combinations = combination(word, word.length(), i);
	    for(int j = 0; j < combinations.size(); j++){
		String anagram = combinations.get(j);
		if(isMatch(anagram, hashDictionary)){
		    return hashDictionary.get(anagram).get(0);
		}
	    }
	}
	return "NO RESULTS";
    }
}
