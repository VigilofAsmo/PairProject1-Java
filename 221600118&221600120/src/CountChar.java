import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CountChar {
	
	public static int getNumber(String path){
		int num=0;
        try{	
        	File file = new File(path);
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            int cc;
            char ccc;
            		
            while((cc=br.read())!=-1) {
            	ccc=(char)cc;
            	if(ccc=='\n') {
            		num--;
            	}
            	if(cc>=0&&cc<=127) {
            		num++;
            	}
            }
            br.close();
            return num;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return 0;
	}
}
