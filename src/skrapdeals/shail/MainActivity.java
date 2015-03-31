package skrapdeals.shail;


import skrapdeal.shail.gen.*;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	int mDay = 31; 
	int mMonth = 3; // August, month starts from 0
	int mYear= 2015;
	
	/** This handles the message send from DatePickerDialogFragment on setting date */
	Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message m){   
        	/** Creating a bundle object to pass currently set date to the fragment */
        	Bundle b = m.getData();
        	
        	/** Getting the day of month from bundle */
    		mDay = b.getInt("set_day");
    		
    		/** Getting the month of year from bundle */
    		mMonth = b.getInt("set_month");
    		
    		/** Getting the year from bundle */
    		mYear = b.getInt("set_year");
    		
    		/** Displaying a short time message containing date set by Date picker dialog fragment */
    		Toast.makeText(getBaseContext(), b.getString("set_date"), Toast.LENGTH_SHORT).show();
    		Intent startNewActivityOpen = new Intent(MainActivity.this, second.class);
    		startActivityForResult(startNewActivityOpen, 0);
    		if(isNetworkStatusAvialable (getApplicationContext())) {
			    Toast.makeText(getApplicationContext(), "internet available", Toast.LENGTH_SHORT).show();
			} else {
			    Toast.makeText(getApplicationContext(), "Let's Celebrate!", Toast.LENGTH_SHORT).show();

			}
        }
	};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /** Click Event Handler for button */
        OnClickListener listener = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				
				/** Creating a bundle object to pass currently set date to the fragment */
				Bundle b = new Bundle();
				
				/** Adding currently set day to bundle object */
				b.putInt("set_day", mDay);
				
				/** Adding currently set month to bundle object */
				b.putInt("set_month", mMonth);
				
				/** Adding currently set year to bundle object */
				b.putInt("set_year", mYear);
				
				/** Instantiating DatePickerDialogFragment */
				DatePickerDialogFragment datePicker = new DatePickerDialogFragment(mHandler);
				
				/** Setting the bundle object on datepicker fragment */
				datePicker.setArguments(b);				
				
				/** Getting fragment manger for this activity */
				FragmentManager fm = getSupportFragmentManager();				
				
				/** Starting a fragment transaction */
				FragmentTransaction ft = fm.beginTransaction();
				
				/** Adding the fragment object to the fragment transaction */
				ft.add(datePicker, "date_picker");
				
				/** Opening the DatePicker fragment */
				ft.commit();
				
			}
		};
        
		/** Getting an instance of Set Date button */
        Button btnSet = (Button)findViewById(R.id.btnSet);
        
        /** Setting click event listener for the button */
        btnSet.setOnClickListener(listener);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
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
