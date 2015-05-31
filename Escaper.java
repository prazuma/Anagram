import java.util.*;
public class Escaper{
    
    public String trimSpace(String word){
	if(word.length() == 1) return word;
	int lastIndex = 0;
	if(word.startsWith(" ")){
	    lastIndex = word.lastIndexOf(" ");
	    word = word.substring(lastIndex + 1);
	}
	return word;
    }
    
    public String arrangeWord(String word){
	char[] letters = word.toCharArray();
	Arrays.sort(letters);
	word = String.valueOf(letters);
	word = word.toLowerCase();
	return word;
    }
    
}
