import java.io.*;
import java.util.*;

public class text_process {
  public static void main(String[] args) {
  
    System.out.println("Start");
    String pathIn = "c:\\Users\\Administrator\\Desktop\\pride-and-prejudice.txt";
    String pathOut = "c:\\Users\\Administrator\\Desktop\\outcome.txt";
    String[] token = null;
    Map<String, Integer> wordlist = null;
    List<pair> sorted = null;
    
    token = utilities(pathIn);
    
    wordlist = frequency(token);
    
    sorted = sortmap(wordlist);
    
    try {
      PrintWriter writer = new PrintWriter(pathOut);
      writer.println(sorted);//(Arrays.toString(token));//
      writer.close();    
    } catch (Exception e) {
      System.out.println("There is a problem writing the file.");
    } 
    
    System.out.println("End.");  
    
  }
  
  
  public static String[] utilities(String path) {
	  
	    String content = "";
	    String[] token = null;  
	    String line = null;
	    
	    try {    
	      BufferedReader reader = new BufferedReader(new FileReader(path));
	      while((line=reader.readLine()) != null) {
	        content += " " + line;
	      }
	      reader.close();
	    } catch (Exception e) {
	      System.out.println("There is a problem reading the file.");
	    }
	    
	    token = content.toLowerCase()./*replaceAll("_","").*/split("[\\W_]+");
	    
	    System.out.println(content.length());
	    System.out.println(token.length);
	    
	    return token;
	    
  }
  
  
  public static Map<String, Integer> frequency(String[] token) {
	  
	  Map<String, Integer> wordlist = new HashMap<String, Integer>();
	  
	  for(int i=0;i<token.length;i++) {
		  Integer freq = wordlist.get(token[i]);
		  wordlist.put(token[i], (freq!=null)?freq+1:1);		  
	  }
	  
	  return wordlist;
	  
  }
  
  
  public static class pair {
	  String word;
	  Integer freq;
      public pair(String word, Integer freq) {
	    this.word = word;
	    this.freq = freq;
      }
      
      @Override
      public String toString() {
    	  return (this.word + "---" + this.freq);
      }
  }
  
  public static List<pair> sortmap(Map<String, Integer> wordlist) {
	  
	  List<pair> sorted = new ArrayList<pair>();
	  int n = wordlist.size();
	  String[] words = new String[n];
	  List<Integer> freqs = new ArrayList<Integer>();
	  int[] sortedfreqs = new int[n];
	  
      int i=0;
	  for (String key : wordlist.keySet()) {
		  words[i] = key;
		  freqs.add(wordlist.get(key));
		  sortedfreqs[i] = wordlist.get(key);
		  i+=1;
	  }
	  
	  Arrays.sort(sortedfreqs);
	  //System.out.println(Arrays.toString(sortedfreqs));
	  for (int j=0;j<n;j++) {
		  int target = sortedfreqs[n-1-j];
		  int position = freqs.indexOf(target);
		  //System.out.println(target);
		  //System.out.println(position);
		  sorted.add(new pair(words[position],target));
		  freqs.set(position,-1);
	  }
	  
	  return sorted;
	  
  } 

}
