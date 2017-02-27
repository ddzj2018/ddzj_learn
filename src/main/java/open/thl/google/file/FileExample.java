package open.thl.google.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class FileExample {
	
	public static void main(String[] args) {
		String path="D:/test.txt";
		try {
			List<String> lsLists=Files.readLines(new File(path), Charsets.UTF_8);//guava
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileUtils.readLines(new File(path), Charsets.UTF_8);//apache io
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//大文件处理
		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(new File(path), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
		    while (it.hasNext()) {
		        String line = it.nextLine();
		        // do something with line
		    }
		} finally {
		    LineIterator.closeQuietly(it);
		}
	}
}
