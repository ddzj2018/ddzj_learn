package open.thl.other;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * Apache POI��һ����ѵĿ�Դ�����ڴ���Microsoft Office�ĵ�����������ʹ��Java��ȡ�ʹ���,�޸�MS Excel�ļ���MS
 * Word��MSPowerPoint�ļ���
 * HSSF �� �ṩ��дMicrosoft Excel XLS��ʽ�����Ĺ��ܡ�
 * XSSF �� �ṩ��дMicrosoft Excel OOXML XLSX��ʽ�����Ĺ��ܡ�
 * HWPF �� �ṩ��дMicrosoft Word DOC97��ʽ�����Ĺ��ܡ�
 * XWPF �� �ṩ��дMicrosoft Word DOC2003��ʽ�����Ĺ��ܡ�
 * HSLF �� �ṩ��дMicrosoft PowerPoint��ʽ�����Ĺ��ܡ�
 * HDGF �� �ṩ��Microsoft Visio��ʽ�����Ĺ��ܡ�
 * HPBF �� �ṩ��Microsoft Publisher��ʽ�����Ĺ��ܡ�
 * HSMF �� �ṩ��Microsoft Outlook��ʽ�����Ĺ��ܡ�
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
			run.setText("���ݣ�xx");
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
			ImmutableList<String> headerlist=ImmutableList.of("���","��������","ʱ��","�û�");
			
			// ������һ��sheet��ҳ��
			Sheet sheet = wb.createSheet("�����б�");
			//����ÿ�п��
//			sheet.setColumnWidth(0, 8 * 256);
//			sheet.setColumnWidth(1, 40 * 256);
//			sheet.setColumnWidth(2, 20 * 256);
//			sheet.setColumnWidth(3, 20 * 256);
			//����title
			Row row = sheet.createRow((short) 0);
			for (int i=0;i<headerlist.size();i++) {
				row.createCell(i).setCellValue(headerlist.get(i));
			}
			//�ڶ���
			Row rowbody = sheet.createRow((short) 1);
			rowbody.createCell(0).setCellValue(1);
			rowbody.createCell(1).setCellValue("contentxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			rowbody.createCell(2).setCellValue("time");
			rowbody.createCell(3).setCellValue("pin");
			FileOutputStream out = new FileOutputStream("D:/excelTest.xls");
			wb.write(out);
			wb.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
