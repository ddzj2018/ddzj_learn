package open.thl.other;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import open.thl.domain.User;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;

public class CsvExample {
	private static String[] headers = {"userName","age","score"};
	//跳过首行头部标题
	private static CSVFormat format = CSVFormat.DEFAULT.withHeader(headers).withSkipHeaderRecord();
	public static void main(String[] args) {
		writeCvs();
	}
	public static void readCvs() {
		try {
			File csvData = new File("D:/test2.csv");
			CSVParser parser2 = CSVParser.parse(csvData, Charsets.UTF_8,format);
			for (CSVRecord csvRecord : parser2) {
				System.out.println(csvRecord);
				System.out.println(csvRecord.get("userName"));
			}
		} catch (Exception e) {

		}
	}
	public static void writeCvs() {
		try {
			FileWriter out = new FileWriter("D:/test2.csv");
			CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT);
			printer.printRecord(Arrays.asList(headers));
			User student = new User("水电费", 11, 122);
			User student2 = new User("水电费谢谢", 11, 122);
			User student3 = new User("水电费嗯嗯", 11, 122);
			List<User> userList=Lists.newArrayList(student,student2,student3);
			for(User user:userList){
				List<Object> records = Lists.newArrayList();
				records.add(user.getUserName());
				records.add(user.getAge());
				records.add(user.getScore());
				printer.printRecord(records);
			}
			printer.flush();
			out.flush();
			out.close();
			printer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
