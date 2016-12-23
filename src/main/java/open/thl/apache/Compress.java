package open.thl.apache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class Compress {
	/**
	 * 压缩普通文件成zip文件
	 * @param files
	 * @param zipFilePath "D:/zip/pins.zip"
	 */
	public static void compressFiles2Zip(File[] files, String zipFilePath) {
		if (files != null && files.length > 0) {
			if (isEndsWithZip(zipFilePath)) {
				ZipArchiveOutputStream zaos = null;
				try {
					File zipFile = new File(zipFilePath);
					zaos = new ZipArchiveOutputStream(zipFile);
					zaos.setUseZip64(Zip64Mode.AsNeeded);
					for (File file : files) {
						if (file != null) {
							ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
							zaos.putArchiveEntry(zipArchiveEntry);
							InputStream is = null;
							try {
								is = new FileInputStream(file);
								byte[] buffer = new byte[1024 * 5];
								int len = -1;
								while ((len = is.read(buffer)) != -1) {
									zaos.write(buffer, 0, len);
								}
								zaos.closeArchiveEntry();
							} catch (Exception e) {
								throw new RuntimeException(e);
							} finally {
								if (is != null){
									is.close();
								}
							}
						}
					}
					zaos.finish();
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if (zaos != null) {
							zaos.close();
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	/**
	 * 解压zip文件
	 * @param zipFilePath
	 * @param saveFileDir
	 */
	public static void decompressZip(String zipFilePath, String saveFileDir) {  
        if(!saveFileDir.endsWith("\\") && !saveFileDir.endsWith("/") ){  
            saveFileDir += File.separator;  
        }  
        File dir = new File(saveFileDir);  
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        File file = new File(zipFilePath);  
        if (file.exists()) {  
            InputStream is = null;   
            ZipArchiveInputStream zais = null;  
            try {  
                is = new FileInputStream(file);  
                zais = new ZipArchiveInputStream(is);  
                ArchiveEntry archiveEntry = null;  
                while ((archiveEntry = zais.getNextEntry()) != null) {   
                    // 获取文件名  
                    String entryFileName = archiveEntry.getName();  
                    // 构造解压出来的文件存放路径  
                    String entryFilePath = saveFileDir + entryFileName;  
                    OutputStream os = null;  
                    try {  
                        // 把解压出来的文件写到指定路径  
                        File entryFile = new File(entryFilePath);  
                        if(entryFileName.endsWith("/")){  
                            entryFile.mkdirs();  
                        }else{  
                            os = new BufferedOutputStream(new FileOutputStream(  
                                    entryFile));                              
                            byte[] buffer = new byte[1024 ];   
                            int len = -1;   
                            while((len = zais.read(buffer)) != -1) {  
                                os.write(buffer, 0, len);   
                            }  
                        }  
                    } catch (IOException e) {  
                        throw new IOException(e);  
                    } finally {  
                        if (os != null) {  
                            os.flush();  
                            os.close();  
                        }  
                    }  
  
                }   
            } catch (Exception e) {  
                throw new RuntimeException(e);  
            } finally {  
                try {  
                    if (zais != null) {  
                        zais.close();  
                    }  
                    if (is != null) {  
                        is.close();  
                    }  
                } catch (IOException e) {  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
    }  
	public static boolean isEndsWithZip(String fileName) {
		boolean flag = false;
		if (fileName != null && !"".equals(fileName.trim())) {
			if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
				flag = true;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		File file =new File("D:/dts.txt");
		File[] files = { file };
		try {
//			System.out.println("1MB="+1*1024*1024);
//			if(file.length()>1*1024*1024){
//				System.out.println("file size:"+file.length());
//				compressFiles2Zip(files, "D:/dts.zip");
//			}else{
//				System.out.println("file size less 1MB,no need compress");
//			}
//			
			
			
				
			decompressZip("D:/dts.zip","D:/zip/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
