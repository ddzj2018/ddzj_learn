package open.thl.other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * Apache POI是一个免费的开源库用于处理Microsoft Office文档。用它可以使用Java读取和创建,修改MS Excel文件，MS
 * Word和MSPowerPoint文件。
 * HSSF － 提供读写Microsoft Excel XLS格式档案的功能。
 * XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能。
 * HWPF － 提供读写Microsoft Word DOC97格式档案的功能。
 * XWPF － 提供读写Microsoft Word DOC2003格式档案的功能。
 * HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
 * HDGF － 提供读Microsoft Visio格式档案的功能。
 * HPBF － 提供读Microsoft Publisher格式档案的功能。
 * HSMF － 提供读Microsoft Outlook格式档案的功能。
 * @author zhouchangwei
 *
 */
public class PoiExample {
	public static void main(String[] args) {
		excelEx();
	}
	public static void wordExp(){
		try {
			XWPFDocument doc = new XWPFDocument();
			
			XWPFParagraph graph = doc.createParagraph();
			graph.setBorderTop(Borders.DOUBLE);
			
			XWPFParagraph graph1 = doc.createParagraph();
			XWPFRun run = graph1.createRun();
			run.setBold(true);
			run.setText("内容：xx");
			FileOutputStream out = new FileOutputStream("D:/word.doc");
			doc.write(out);
			doc.close();
			out.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void excelEx(){
		try {
			Workbook wb = new XSSFWorkbook();//只能导出xlsx格式
			ImmutableList<String> headerlist=ImmutableList.of("序号","反馈内容","时间","用户");
			
			// 创建第一个sheet（页）
			Sheet sheet = wb.createSheet("反馈列表");
			//定义每列宽度
//			sheet.setColumnWidth(0, 8 * 256);
//			sheet.setColumnWidth(1, 40 * 256);
//			sheet.setColumnWidth(2, 20 * 256);
//			sheet.setColumnWidth(3, 20 * 256);
			//首行title
			Row row = sheet.createRow((short) 0);
			for (int i=0;i<headerlist.size();i++) {
				row.createCell(i).setCellValue(headerlist.get(i));
			}
			//第二行
			Row rowbody = sheet.createRow((short) 1);
			rowbody.createCell(0).setCellValue(1);
			rowbody.createCell(1).setCellValue("contentxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			rowbody.createCell(2).setCellValue("time");
			rowbody.createCell(3).setCellValue("pin");
			FileOutputStream out = new FileOutputStream("D:/excelTest.xlsx");
			wb.write(out);
			wb.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
