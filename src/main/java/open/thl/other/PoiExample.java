package open.thl.other;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


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
		wordExp();
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
			Workbook wb = new HSSFWorkbook();
			// 创建第一个sheet（页）
			Sheet sheet = wb.createSheet("反馈列表");
			//定义每列宽度
			sheet.setColumnWidth(0, 8 * 256);
			sheet.setColumnWidth(1, 40 * 256);
			sheet.setColumnWidth(2, 20 * 256);
			sheet.setColumnWidth(3, 20 * 256);
			//首行title
			Row row = sheet.createRow((short) 0);
			row.createCell(0).setCellValue("序号");
			row.createCell(1).setCellValue("反馈内容");
			row.createCell(2).setCellValue("时间");
			row.createCell(3).setCellValue("用户");
			//第二行
			Row rowbody = sheet.createRow((short) 1);
			rowbody.createCell(0).setCellValue(1);
			rowbody.createCell(1).setCellValue("xx");
			rowbody.createCell(2).setCellValue("xxsss");
			rowbody.createCell(3).setCellValue("pin");
			FileOutputStream out = new FileOutputStream("D:/xs.xlsx");
			wb.write(out);
			wb.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
