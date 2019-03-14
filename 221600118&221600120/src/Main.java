
import java.io.*;
import java.util.*;

public class cnt {
	
	public static HashMap<String, Integer> hash = new HashMap<String, Integer>();
	
	public static void  main(String []args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please input the absolutely path!\n");
		
		String path = scan.nextLine();
		readFile(path);
		
		scan.close();
	}
	
	public static void getLine(BufferedReader br) {}
	
	public static void readFile(String path) {
        int num=0,lines=0,words=0,t=0;
        try{	
        	FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String line;
          
            char c[];
            
            boolean bline=false;
            
            while ((line = br.readLine()) != null) {
            	num++;
            	bline=false;
            	c=line.toCharArray();
            	for(int i=0;i<c.length;i++) {
            		int ch=(int)c[i];
            		if(ch>=0&&ch<=127) num++;
            		if(ch>=33&&ch<127) bline=true;
            	}
            	
            	System.out.println(line);
                if(bline) lines++;
            }
            
            System.out.println("num:"+num);
            
            System.out.println("lines:"+lines);
           
            System.out.println("words:"+words);
            System.out.println("t:"+t);
            for(Map.Entry<String, Integer> entry : hash.entrySet()) {
            	System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static int getWord(String line) {
		if(line.length()<4) return 0;
		
		String theline=line.toLowerCase();
		
		char c[]=theline.toCharArray();
		int n=0;
		for(int i=0;i<c.length;i++) {
			int ch=(int)c[i];
			if(isaz(ch)) {
				if(the4(c,i)) {
					int last=0;
					n++;
					for(int j=i+4;j<c.length;j++) {
						last=j;
						if(!isaz((int)c[last])&&!isnum((int)c[last])) {
							n+=getWord(theline.substring(last,theline.length()));
							break;
						}
					}
					if(last==c.length-1) last++;
					String word=theline.substring(i,last);
					if(hash.containsKey(word)) {
						int nn=hash.get(word);
						hash.put(word, nn+1);
					}
					else hash.put(word, 1);
					break;
				}
			}
		}
		return n;
	}

	public static boolean isaz(int c) {
		return c>=97&&c<=122;
	}
	public static boolean isnum(int c) {
		return c>=48&&c<=57;
	}
	
	public static boolean the4(char[] line,int index) {
		int n=0;
		for(int i=index+1;i<line.length;i++) {
			int ch=(int)line[i];
			if(isaz(ch)) {
				if(++n==3) return true;
			}
			else return false;
		}
		return false;
	}
}
