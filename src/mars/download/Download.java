package mars.download;

import mars.utils.HttpDownload;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Download extends Activity {
	private Button downMp3, downText;
	int result;
	HttpDownload httpDownload;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		downMp3 = (Button)findViewById(R.id.downMp3button);
		downText = (Button)findViewById(R.id.downTextbutton);
		downMp3.setOnClickListener(new DownListener());
	}
	class DownListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			 httpDownload = new HttpDownload();
		
			new Thread(new InputThread()).start();
			System.out.println(result);
		}
		
	}
	public class InputThread implements Runnable{

		@Override
		public void run() {
			int result = httpDownload.downLoadFile("mother/", "mother.mp3", "http://my.9ku.com/mp3.asp?id=657074");
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
