package mars.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {
	private String SDPath;
	public FileUtils() {
		SDPath = Environment.getExternalStorageDirectory() + "/";
				
	}
	public File creatSDFile(String filename)throws IOException{
		File file = new File(SDPath+filename);
		file.createNewFile();
		return file;
		
	} 
	public File creatSDdir(String path){
		File dir = new File(SDPath + path);
		dir.mkdir();
		return dir;
	}
	public boolean isFileExists(String filename){
		File file = new File(SDPath + filename);
		return file.exists();
	}
	public File writedToSD (String filename, String path, InputStream inputStream){
		OutputStream oStream = null;
		File file = null;
		creatSDdir(path);
		try {
			 file = creatSDFile(path + filename);
			 oStream = new FileOutputStream(file);
			 
			 byte[] buffer = new byte[4*1024];
			 while(inputStream.read(buffer)!=-1){
				oStream.write(buffer);
				System.out.println("begin");
			}
			oStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				oStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	
}
