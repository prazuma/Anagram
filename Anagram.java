import java.util.*;
public class Anagram{
    public static void main(String[] args){
	//辞書の吸い上げ
	ArrayList<String> dictionary = new ArrayList<String>();
	dictionary.add("email");
	dictionary.add("amile"); //a mile
	dictionary.add("alime"); //a lime
	//辞書のソーティング、ハッシュでやるのがいい気がするよ
	for(int i = 0; i < dictionary.size(); i++){
	    dictionary.set(i, sort(dictionary.get(i)));
	}
	String word = "lameiwe";//キーボード入力にする（優先度最低）
	word = sort(word);
	System.out.println(word);
	//抜かす文字列を徐々に増やしていく
	int total = 0;//デバッグ用
	for(int i = 0; i < word.length(); i++){
	    //判定の場所を変える。見つかったのがわかっているのに止めないのは非常に無駄
	    ArrayList<String> combinations = combination(word, word.length(), i);
	    for(int j = 0; j < combinations.size(); j++){
		String anagram = combinations.get(j);
		System.out.println(anagram);
		if(isMatch(anagram, dictionary)){
		    System.out.println("TRUE");
		}
	    }
	    total += combinations.size();
	}
	System.out.println("total: " + total);
    }

    public static String sort(String word){
	//trimspaceする。メソッドはお外に
	char[] letters = word.toCharArray();
	Arrays.sort(letters);
	return String.valueOf(letters);
    }
    
    //nCr
    //TODO: 今度staticじゃないのにする
    public static ArrayList<String> combination(String word, int n, int r){
	//重複の場合がまちがっている
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

    public static boolean isMatch(String word, ArrayList<String> dictionary){
	return dictionary.contains(word);
    }
}
