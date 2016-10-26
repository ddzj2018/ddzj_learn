package open.thl.google;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.io.Files;

/**
 * Google Cuava�ڹ�����Ŀ�к���ʹ�ã����Һ�������һЩ����JAVA����ʦ���������Ƽ����JAVA�⡣
 * ��������Google���Լ���JAVA��Ŀ����ʹ�õ�һЩ����JAVA�⡣�����˶ԣ�����,����,������,�ַ�������,
 * I/O�ȸ��������֧�֡�����Google�����Ŀ��������������ơ�
 * 
 * @author zhouchangwei
 *
 */
public class GuavaExample {
	
	public static void main(String[] args) {
//		Optional<Integer> possible = Optional.of(5);
//		System.out.println(possible);
//		testNull();
//		testMethodReturn();
		File file = new File("D:/test.txt");  
		List<String> lines = null;  
		try {  
			lines = Files.readLines(file, Charsets.UTF_8);  
			System.out.println(lines);
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
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
            System.out.println("��÷���ֵ: " + value.get());     
        }else{
                
            System.out.println("��÷���ֵ: " + value.or(-12L));    
        }
        
        System.out.println("��÷���ֵ orNull: " + value.orNull());
        
        Optional<Long> valueNoNull = methodNoNull();
        if(valueNoNull.isPresent()==true){
            Set<Long> set=valueNoNull.asSet();
            System.out.println("��÷���ֵ set �� size : " + set.size());    
            System.out.println("��÷���ֵ: " + valueNoNull.get());     
        }else{
            System.out.println("��÷���ֵ: " + valueNoNull.or(-12L));    
        }
        
        System.out.println("��÷���ֵ orNull: " + valueNoNull.orNull());
    }

    private static Optional<Long> method() {
        return Optional.fromNullable(null);
    }
    private static Optional<Long> methodNoNull() {
        return Optional.fromNullable(15L);
    }
	
}
