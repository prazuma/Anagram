import java.util.*;
import java.io.*;

public class Setup{

    public void createDictionaryFile(String fileName){
	HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();
	try {
	    FileReader fr = new FileReader(fileName);
	    BufferedReader br = new BufferedReader(fr);
	    String word, key;
	    ArrayList<String> values;
	    while((word = br.readLine()) != null){
		key = reArrangeWord(word);
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
	StringBuilder sb = new StringBuilder();
	ArrayList<String> keyList = new ArrayList<String>(hashDictionary.keySet());
	for(int i = 0; i < keyList.size(); i++){
	    sb.append("0" + "\n");
	    String key = keyList.get(i);
	    sb.append(key + "\n");
	    ArrayList<String> item = new ArrayList<String>();
	    item = hashDictionary.get(key);
	    for(int j = 0; j < item.size(); j++){
		sb.append(item.get(j) + "\n");
	    }
	}
	sb.append("0");
	createFile("dictionary.txt", sb.toString());
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

    private String reArrangeWord(String word){
	char[] letters = word.toCharArray();
	Arrays.sort(letters);
	word = String.valueOf(letters);
	return word.toLowerCase();
    }

    private String trimSpace(String word){
	if(word.length() == 1) return word;
	int lastIndex = 0;
	if(word.startsWith(" ")){
	    lastIndex = word.lastIndexOf(" ");
	    word = word.substring(lastIndex + 1);
	}
	return word;
    }

    public HashMap<String, ArrayList<String>> setHashMap(){
	ArrayList<String> textList = readFile("dictionary.txt");
	return convertHashMap(textList);
    }

    private ArrayList<String> readFile(String fileName){
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
    
    private HashMap<String, ArrayList<String>> convertHashMap(ArrayList<String> textList){
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
}
