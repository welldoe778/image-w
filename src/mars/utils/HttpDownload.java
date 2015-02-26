package mars.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.R.integer;

public class HttpDownload {
	private URL url;

	public InputStream getUrlInputStream(String urlStr)
			throws MalformedURLException, IOException {
		
		url = new URL(urlStr);
		HttpURLConnection urlCnn = (HttpURLConnection) url.openConnection();
		
		InputStream inputStream = urlCnn.getInputStream();
		

		return inputStream;
	}

	public String downLoad(String url) {
		BufferedReader buffered = null;
		StringBuffer sBuffer = new StringBuffer();
		InputStream inputStream = null;
		String line = null;
		try {
			inputStream = getUrlInputStream(url);
			buffered = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = buffered.readLine()) != null) {
				sBuffer.append(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				buffered.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sBuffer.toString();

	}

	public int downLoadFile(String path, String filename, String url) {
		InputStream inputStream = null;

		try {
			FileUtils fileUtils = new FileUtils();
			if (fileUtils.isFileExists(path +filename)) {
				return 1;
			} else {

				inputStream = getUrlInputStream(url);
				System.out.println("begin");
				File file = fileUtils.writedToSD(filename, path, inputStream);
				
				if (file == null) {
					return -1;
				}
			}
		} 
		 catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
