package ncsu.carjuice.main;

import ncsu.carjuice.main.GetLocation.LocationResult;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//default name to access settings across any activity
	//public static final String APP_SETTINGS = "AppSettings";
	
	//Creating the search query string to send along bundled with the intent to list view
	protected final static String SEARCH_QUERY = "ncsu.carjuice.main.SEARCH_QUERY";
	protected static String LONG = "ncsu.carjuice.main.LONG";
	protected static String LAT = "ncsu.carjuice.main.LAT";
	protected static String RAD = "ncsu.carjuice.main.RAD";
	
	private String longitude;
	private String latitude;
	private String radius;

    /** Called when the activity is first created. */
    @Override
    //@param savedInstanceState
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

		radius = sharedPrefs.getString("radius", "-1");   //should snag the i
		
		//to be safe
		if (Integer.parseInt(radius) < 5)
			radius = "5";

		//to confirm
	     Toast.makeText(getApplicationContext(), 
                "Search Radius is currently " + radius + " miles", Toast.LENGTH_LONG).show();
		

//****On the emulator this code is causing force close due to not being able to 
//    get GPS or network location, commented out for now 

//----------------------------------get location--------------------
       
      
        LocationResult locationResult = new LocationResult(){
            @Override
            public void gotLocation(Location location){        	           	
            	latitude = location.getLatitude()+"";
            	longitude = location.getLongitude()+"";
            }

        };
        GetLocation myLocation = new GetLocation();
        myLocation.getLocation(this, locationResult);        
        
//----------------------------------end get location--------------------        

        
    }
    
    //Sends an intent to the SettingsActivity, attached to the settings icon
    public void viewSettings(View view) {
    	
    	Intent intent = new Intent(this, SettingsActivity.class);	
    	startActivity(intent);
	
    }// of viewSettings      
   
    public void invalidSearchAlert ()
    {
    	
		final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
		alertDialog.setTitle("Invalid Input");
		alertDialog.setMessage("Please Enter a Valid Address, City, State, or Zip Code");
		
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 	       alertDialog.dismiss();
		    } });
		
		alertDialog.show();
		
    }// of viewSettings 
    

    
    //Message sent by Search button. Sends an intent to the listview activity
    public void sendQuery(View view) {
    	
        // Do something in response to button
    	
    	Intent intent = new Intent(this, StationsListActivity.class);
    	
    	EditText editText = (EditText) findViewById(R.id.edit_address);
    	String message = editText.getText().toString().trim();
    	
    	if(message.length() == 0){
    		//putting empty string into Search Query so our logic in StationsListActivity works and we don't get null pointer exception
    		intent.putExtra(SEARCH_QUERY, "");	
    		intent.putExtra(RAD, radius);
/*	
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ HARD CODED LONG/LAT @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    	
        	latitude="35.77435";
        	longitude="-78.64233";
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    	
*/       	
        	intent.putExtra(LONG, longitude);
        	intent.putExtra(LAT, latitude);
        	//Starts instance of the activity called by intent parameter, in this case: DisplayMessageActivity
        	//Intent also carries with it the SEARCH_QUERY
        	startActivity(intent);
    		
    	}
    	else{
    		intent.putExtra(RAD, radius);
    		intent.putExtra(SEARCH_QUERY, message);
    		//Starts instance of the activity called by intent parameter, in this case: StationsListActivity
        	//Intent also carries with it the SEARCH_QUERY
        	startActivity(intent);
    	}
    	
    }

}