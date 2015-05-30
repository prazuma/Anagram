import java.util.*;
import java.io.*;

public class Setup{
    /*
    public void Setup(){
	String fileNameTest = "test.txt";
	String fileName = "/usr/share/dict/words";
	craeteDictionary(fileNameTest);
    }
    */
    
    public void createDictionaryFile(String fileName){
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
	System.out.println("hashMap1");
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

    private void createFile(String fileName){
	createFile(fileName, "");
    }
    
    private void createFile(String fileName, String str){
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
    public ArrayList<String> readFile(String filePath){
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
    public String sort(String word){
	char[] letters = word.toCharArray();
	Arrays.sort(letters);
	String sortedWord = String.valueOf(letters);
	sortedWord = trimSpace(sortedWord);
	return sortedWord;
    }

    public String trimSpace(String word){
	if(word.length() == 1) return word;
	int lastIndex = 0;
	if(word.startsWith(" ")){
	    lastIndex = word.lastIndexOf(" ");
	    word = word.substring(lastIndex + 1);
	}
	return word;
    }
}
