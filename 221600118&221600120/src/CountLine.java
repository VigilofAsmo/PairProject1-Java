import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class CountLine {
		
	public static int getLine(String path) {
		
        int lines=0;

        try{	
        	
        	File file = new File(path);
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;

            boolean bline=false;
          
            char c[];
            while ((line = br.readLine()) != null) {
            	if(line.length()==0) { 
            		continue;
            	}
          
            	bline=false;
            	c=line.toCharArray();
            	for(int i=0;i<c.length;i++) {
            		int ch=(int)c[i];
            		if(ch>=33&&ch<127) {
            			bline=true;
            		}
            	}
              if(bline) lines++;
            }
            br.close();
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		return 0;
	}
}
