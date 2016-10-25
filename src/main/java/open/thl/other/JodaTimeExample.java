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
 * Java SE 8之前的JAVA版本中对日期的支持是比较差的，Joda-Time被经常被使用来替换原有的日期系统，它能够支持更多的日历体系，
 * 并提供了很多非常方便的日期处理方法，而且它的性能也是非常出色的。
 * 
 * @author zhouchangwei
 *
 */
public class JodaTimeExample {
	public static void main(String[] args) {
		DateTime dTime=DateTime.now();
//		System.out.println(dTime.minusDays(1).toString("yyyy-MM-dd"));
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");  
        //时间解析    
        DateTime dateTime2 = DateTime.parse("2012-12-21 23:22:45", format);   
        
     // DateTime与java.util.Date对象,当前系统TimeMillis转换    
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
