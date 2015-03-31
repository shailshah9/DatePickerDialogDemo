package skrapdeals.shail;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import skrapdeal.shail.gen.*;


public class second extends Activity {
	private TextView tv;
	@Override
    public void onCreate(Bundle savedInstanceState) {         

       super.onCreate(savedInstanceState);    
       setContentView(R.layout.second);
       tv=(TextView)findViewById(R.id.textView1);
       //rest of the code
       
       tv.setText("Date Set Success");
       
       if(isNetworkStatusAvialable (getApplicationContext())) {
		    Toast.makeText(getApplicationContext(), "internet available", Toast.LENGTH_SHORT).show();
		} else {
		    Toast.makeText(getApplicationContext(), "Let's Celebrate!", Toast.LENGTH_SHORT).show();

		}
       
   }
	public static boolean isNetworkStatusAvialable (Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    if (connectivityManager != null) 
	    {
	        NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
	        if(netInfos != null)
	        if(netInfos.isConnected()) 
	            return true;
	    }
	    return false;
	}

}
