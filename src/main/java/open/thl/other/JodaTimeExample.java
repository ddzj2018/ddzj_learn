package open.thl.other;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.collect.Lists;

/**
 * Java SE 8֮ǰ��JAVA�汾�ж����ڵ�֧���ǱȽϲ�ģ�Joda-Time��������ʹ�����滻ԭ�е�����ϵͳ�����ܹ�֧�ָ����������ϵ��
 * ���ṩ�˺ܶ�ǳ���������ڴ�������������������Ҳ�Ƿǳ���ɫ�ġ�
 * 
 * @author zhouchangwei
 *
 */
public class JodaTimeExample {
	public static void main(String[] args) {
		DateTime dTime=DateTime.now();
//		System.out.println(dTime.minusDays(1).toString("yyyy-MM-dd"));
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");  
        //ʱ�����    
        DateTime dateTime2 = DateTime.parse("2012-12-21 23:22:45", format);   
        
     // DateTime��java.util.Date����,��ǰϵͳTimeMillisת��    
        DateTime dt6 = new DateTime(new Date());    
        Date date = dt6.toDate();    
        DateTime dt7 = new DateTime(System.currentTimeMillis());    
        dt7.getMillis();   
          
        Calendar calendar = Calendar.getInstance();    
        dt7 = new DateTime(calendar);  
        LocalDate _1=new LocalDate(2016,10,15);
		LocalDate _2=new LocalDate();
		System.out.println(_2.minusDays(10).toString());
        System.out.println(dateList(_1, _2));
	}
	private static List<LocalDate> dateList(LocalDate start,LocalDate end) {
		List<LocalDate> list=Lists.newArrayList();
		if(end.isAfter(start)){
			while (!start.equals(end)) {
				list.add(start);
				start=start.plusDays(1);
			}
			list.add(end);
		}
		return list;
	}
}
