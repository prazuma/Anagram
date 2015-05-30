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
	HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();
	try {
	    FileReader fr = new FileReader(fileName);
	    BufferedReader br = new BufferedReader(fr);
	    String word, key;
	    ArrayList<String> values;
	    while((word = br.readLine()) != null){
		key = sort(word);
		if(hashDictionary.get(key) == null){
		    values = new ArrayList<String>();
		    values.add(word);
		} else {
		    values = hashDictionary.get(key);
		    values.add(word);
		}
		hashDictionary.put(key, values);
	    }
	    br.close();
	} catch (IOException e) {
	    System.out.println("IOException: " + e);
	}
	System.out.println("hashMap");
	System.out.println(hashDictionary.size());
	String str = "";
	ArrayList<String> keyList = new ArrayList<String>(hashDictionary.keySet());
	int last = keyList.size();
	while(keyList.size() > 0){
	    str += "0" + "\n";
	    String key = keyList.remove();
	    str += key + "\n";
	    ArrayList<String> item = new ArrayList<String>();
	    item = hashDictionary.get(key);
	    for(int j = 0; j < item.size(); j++){
		str += item.get(j);
		str += "\n";
	    }
	    last--;
	    if(last % 10000 == 0)System.out.println("REMAIN: " + last);
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
		System.out.println(str);
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
