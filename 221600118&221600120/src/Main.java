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
        
        //1:使用File类创建一个要操作的文件路径
        File file = new File("C:/Users/李子琪/Desktop/测试样例/result.txt"); 
        if(!file.getParentFile().exists()){ //如果文件的目录不存在
            file.getParentFile().mkdirs(); //创建目录
            
        }
        
        //2: 实例化OutputString 对象
        OutputStream output = new FileOutputStream(file);
        
        //3: 准备好实现内容的输出
        
        byte data[] = msg.getBytes();
        output.write(data);
        //4: 资源操作的最后必须关闭
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
