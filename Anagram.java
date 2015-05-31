import java.util.*;
import java.io.*;

public class Anagram{
    static HashMap<String, ArrayList<String>> hashDictionary = new HashMap<String, ArrayList<String>>();;
    
    public static void main(String[] args){
	String fileName = "/usr/share/dict/words";
	Setup setup = new Setup();
	Escaper escaper = new Escaper();
	FindAnagram findanagram = new FindAnagram();
	
	File file = new File("dictionary.txt");
        if(!(file.exists())){
	    setup.createDictionaryFile(fileName);
	}
	hashDictionary = setup.setHashMap();

	Scanner sc = new Scanner(System.in);
	while(true){
	    System.out.print("16 characters: ");
	    String word = sc.nextLine();
	    word = escaper.arrangeWord(word);
	    word = escaper.trimSpace(word);
	    String result = findanagram.findAnagram(word, hashDictionary);
	    System.out.println(result);
	}
    }
}
