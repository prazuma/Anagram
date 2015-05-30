import java.util.*;
import java.io.*;

public class Anagram{
    public static void main(String[] args){
	//辞書の吸い上げ
	/*
	  /usr/share/dict/wordsのファイルはwords.txt
	  辞書ファイルdictionary.txt
	  dictionary.txtがフォルダ内になければ、words.txtからdictionary.txtを作成する。
	  dictionary.txtから読込みを行う。
	 */

	/*
	  dictionary.txtがあるか確認
	  ない：createDictionaryFile("/usr/share/dict/words");
	  ある：次へ
	 */
	
	//dictionary.txtの読込み
	ArrayList<String> textList = readFile("dictionary.txt");
	
	//TODO: dictionary.txtからHashMapをつくる
	HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();
	hashDictionary = setHashMap(textList);


	//TODO: words.txtからdictionary.txtをつくる
	createDictionaryFile("test.txt");

	String word = "lameiwe";//キーボード入力にする（優先度最低）
	word = sort(word);
	for(int i = word.length() - 1; i >= 0; i--){
	    ArrayList<String> combinations = combination(word, word.length(), i);
	    for(int j = 0; j < combinations.size(); j++){
		String anagram = combinations.get(j);
		if(isMatch(anagram, hashDictionary)){
		    System.out.println(hashDictionary.get(anagram).get(0));
		    return;
		}
	    }
	}
    }

    public static void print(ArrayList<String> list){
	for(int i = 0; i < list.size(); i++){
	    System.out.println(list.get(i));
	}
    }
    
    public static void createFile(String fileName){
	createFile(fileName, "");
    }

    public static void createFile(String fileName, String str){
	try {
	    File file = new File(fileName);
	    FileWriter fw = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter pw = new PrintWriter(bw);
	    pw.println(str);
	    pw.close();
	} catch (IOException e) {
	    System.out.println("IOException: " + e);
	}

    }

    public static void createDictionaryFile(String fileName){
	ArrayList<String> dictionary = readFile(fileName);
	HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();
	ArrayList<String> words;

	String str = "";
	for(int i = 0; i < dictionary.size(); i++){
	    String word = dictionary.get(i);
	    String key = sort(word);
	    if(hashDictionary.containsKey(key)){
		words = hashDictionary.get(key);
	    } else {
		words = new ArrayList<String>();
	    }
	    words.add(word);
	    hashDictionary.put(key, words);
	}
	ArrayList<String> keyList = new ArrayList<String>(hashDictionary.keySet());
	for(int i = 0; i < keyList.size(); i++){
	    str += "0";
	    str += "\n";
	    String key = keyList.get(i);
	    str += key + "\n";
	    ArrayList<String> item = new ArrayList<String>();
	    item = hashDictionary.get(key);
	    for(int j = 0; j < item.size(); j++){
		str += item.get(j);
		str += "\n";
	    }
	}
	str += "0";
	createFile("test2.txt", str);

    }
    
    public static ArrayList<String> readFile(String filePath){
	ArrayList<String> text = new ArrayList<String>();
	try {
	    FileReader fr = new FileReader(filePath);
	    BufferedReader br = new BufferedReader(fr);
	    String str;
	    while((str = br.readLine()) != null){
		text.add(str);
	    }
	    br.close();
	} catch (IOException e) {
	    System.out.println("IOException" + e);
	}
	return text;
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
    //TODO: 今度staticじゃないのにする
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
}
