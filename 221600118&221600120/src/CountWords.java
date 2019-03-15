import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CountWords {
	public static HashMap<String, Integer> hash = new HashMap<String, Integer>();
	
	public static HashMap<String, Integer>  getWords(String path) {
        
        try{	
        	
        	File file = new File(path);
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;
            
            while ((line = br.readLine()) != null) getWord(line);
            
            br.close();
            return hash;
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<String, Integer>();
	}

	
	public static void getWord(String line) {
		if(line.length()<4) return;
		
		String theline=line.toLowerCase();
		
		char c[]=theline.toCharArray();

		for(int i=0;i<c.length;i++) {
			int ch=(int)c[i];
			if(isAZ(ch)) {
				if(the4(c,i)) {
					int last=0;
					for(int j=i+4;j<c.length;j++) {
						last=j;
						if(!isAZ((int)c[last])&&!isNUM((int)c[last])) {
							getWord(theline.substring(last,theline.length()));
							break;
						}
					}
					if(last==c.length-1) last++;
					String word=theline.substring(i,last);
					
					if(hash.containsKey(word)) {
						int nn=hash.get(word);
						hash.put(word, nn+1);
					}else {
						hash.put(word, 1);
					}
					break;
				}
			}
		}
	}
	

	public static boolean isAZ(int c) {
		if(c>=97&&c<=122) {
			return true;
		}
		return false;
	}
	public static boolean isNUM(int c) {
		if(c>=48&&c<=57) {
			return true;
		}
		return false;
	}
	
	public static boolean the4(char[] line,int index) {
		int n=0;
		for(int i=index+1;i<line.length;i++) {
			int ch=(int)line[i];
			if(isAZ(ch)) {
				if(++n==3) return true;
			}else {
				return false;
			}
			
		}
		return false;
	}


}
