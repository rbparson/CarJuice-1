package ncsu.carjuice.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CarJuiceActivity extends Activity {
	
	//Can add things like strings to an intent and send those along too
	//public final static String EXTRA_MESSAGE = "ncsu.carjuice.main.MESSAGE";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
//-----------------Get location code------------------------------------------------------------------------
     // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            
        	@Override
        	public void onLocationChanged(Location location) {      
        		// Called when a new location is found by the network location provider.      
        		//makeUseOfNewLocation(location); wrote this method    
        	}    
        	
        	public void onStatusChanged(String provider, int status, Bundle extras) {}
        	
        	public void onProviderEnabled(String provider) {}    
        	
        	public void onProviderDisabled(String provider) {}
 
        };
        	
        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        
//-----------------end location code-----------------------------------------------------------------------
        
        //Open the search dialogue on app-launch
        onSearchRequested();
    }

    //Message sent by "Locate" button. Sends an intent to the listview activity
    public void sendMessage(View view) {
    	
        // Do something in response to button
    	Intent intent = new Intent(this, ResultsListActivity.class);
    	
    	/*This is code for grabbing the text out of a text box on the main page,
    	 * not currently in use but in case we need to go back to that route...
    	 * 
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	*/
    	
    	//Starts instance of the activity called by intent parameter, in this case: DisplayMessageActivity
    	startActivity(intent);
    }
    
   
    
}