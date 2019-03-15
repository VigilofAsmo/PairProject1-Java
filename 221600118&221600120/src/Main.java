import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
	
	static String path;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		path = scan.nextLine();
	
		
		int num=CountChar.getNumber(path);
		int lines=CountLine.getLine(path);
		HashMap<String, Integer> hash=CountWords.getWords(path);
		int words=0;
		
		List<KV> kvs=new ArrayList<KV>();
        
        for(Map.Entry<String, Integer> entry : hash.entrySet()) {
        	kvs.add(new KV(entry.getKey(),entry.getValue()));
        	words+=entry.getValue();
        }

        Collections.sort(kvs, new Comparator<KV>() {
            public int compare(KV arg0, KV arg1) {
                if(arg0.getValue()!=arg1.getValue()) {
                	return -arg0.getValue().compareTo(arg1.getValue());
                }else {
                	return arg0.getKey().compareTo(arg1.getKey());
                }
            }
        });
        String msg="characters: "+num+"\nwords: "+words+"\nlines: "+lines+"\n";
        
        for (KV kv : kvs) {
        	msg+="<"+kv.getKey()+">: "+kv.getValue()+"\n";
        }
		
        try {
        	writeFile(msg);
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
		scan.close();
	}
	
	public static void writeFile(String msg) throws Exception {
        
        //1:ʹ��File�ഴ��һ��Ҫ�������ļ�·��
        File file = new File("C:/Users/������/Desktop/��������/result.txt"); 
        if(!file.getParentFile().exists()){ //����ļ���Ŀ¼������
            file.getParentFile().mkdirs(); //����Ŀ¼
            
        }
        
        //2: ʵ����OutputString ����
        OutputStream output = new FileOutputStream(file);
        
        //3: ׼����ʵ�����ݵ����
        
        byte data[] = msg.getBytes();
        output.write(data);
        //4: ��Դ������������ر�
        output.close();
        
    }
	private static class KV{
		String key;
		Integer value;
		public KV(String key,Integer value) {
			this.key=key;
			this.value=value;
		}
		public String getKey() {
			return this.key;
		}
		public Integer getValue() {
			return this.value;
		}
	}

}
