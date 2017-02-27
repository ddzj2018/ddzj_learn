package open.thl.google;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * Google Cuava在国内项目中很少使用，但我合作过的一些国外JAVA工程师几乎都会推荐这个JAVA库。
 * 它包含了Google在自己的JAVA项目中所使用的一些核心JAVA库。包含了对：集合,缓存,并发库,字符串处理,
 * I/O等各个方面的支持。另外Google开发的库总是以性能著称。
 * 
 * @author zhouchangwei
 *
 */
public class GuavaExample {
	public static final Charset GBK = Charset.forName("GBK");
	public static void readFile(){
		File file = new File("D:/pinFile.txt");  
		List<String> lines = null;  
		try {  
			lines = Files.readLines(file, Charsets.UTF_8);  
			System.out.println(lines);
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}
	
	public static void writeFile(){
//		File file=new File("D:/dts_xxx.txt");
//		System.out.println(file.length());
		
		try {
//			OutputStream out =new FileOutputStream(file,true);
//		    OutputStreamWriter osw = new OutputStreamWriter(out,GBK);  
			FileWriter fw = new FileWriter("D:/dts_ddddccd.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fw);
		    long start =System.currentTimeMillis();
			for(int i=1;i<2000000;i++){
				System.out.println(i);
				try {
//					out.write(("c75b22e18cd34ee8ac55b699a53e13b7" + "\r\n").getBytes(GBK));
					bufferedWriter.append("c75b22e18cd34ee8ac55b699a53e13b7"+"\r\n");
//					bufferedWriter.newLine();
//					osw.write("c75b22e18cd34ee8ac55b699a53e13b7" + "\r\n");
//					Files.append("c75b22e18cd34ee8ac55b699a53e13b7"+"\r\n", file, GBK);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bufferedWriter.flush();
			bufferedWriter.close();
//			osw.flush();
//			osw.close();
//			out.flush();
//			out.close();
			long end=System.currentTimeMillis();
			System.out.println(end-start);

		} catch (Exception e) {
			// TODO: handle exception13823 13677     13842
		}
		
		
//		File zipFile=new File("D:/pins.zip");
		
	}
	
	public static void main(String[] args) {
		File file = new File("D:/pinFile.txt");  
		System.out.println(file.getAbsolutePath());
//		writeFile();
//		Optional<Integer> possible = Optional.of(5);
//		System.out.println(possible);
//		testNull();
//		testMethodReturn();
		
	}
	public static void testNull(){
		Optional<Integer> possible=Optional.of(6);
        Optional<Integer> absentOpt=Optional.absent();
        Optional<Integer> NullableOpt=Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt=Optional.fromNullable(10);
        if(possible.isPresent()){
            System.out.println("possible isPresent:"+possible.isPresent());
            System.out.println("possible value:"+possible.get());
        }
        if(absentOpt.isPresent()){
            System.out.println("absentOpt isPresent:"+absentOpt.isPresent()); ;
        }
        if(NullableOpt.isPresent()){
            System.out.println("fromNullableOpt isPresent:"+NullableOpt.isPresent()); ;
        }
        if(NoNullableOpt.isPresent()){
            System.out.println("NoNullableOpt isPresent:"+NoNullableOpt.isPresent()); ;
        }
	}
	public static void testMethodReturn() {
        Optional<Long> value = method();
        if(value.isPresent()==true){
            System.out.println("获得返回值: " + value.get());     
        }else{
                
            System.out.println("获得返回值: " + value.or(-12L));    
        }
        
        System.out.println("获得返回值 orNull: " + value.orNull());
        
        Optional<Long> valueNoNull = methodNoNull();
        if(valueNoNull.isPresent()==true){
            Set<Long> set=valueNoNull.asSet();
            System.out.println("获得返回值 set 的 size : " + set.size());    
            System.out.println("获得返回值: " + valueNoNull.get());     
        }else{
            System.out.println("获得返回值: " + valueNoNull.or(-12L));    
        }
        
        System.out.println("获得返回值 orNull: " + valueNoNull.orNull());
    }

    private static Optional<Long> method() {
        return Optional.fromNullable(null);
    }
    private static Optional<Long> methodNoNull() {
        return Optional.fromNullable(15L);
    }
	
}
