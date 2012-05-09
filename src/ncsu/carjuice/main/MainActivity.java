package ncsu.carjuice.main;

import ncsu.carjuice.main.GetLocation.LocationResult;
import ncsu.carjuice.main.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	
	
	//Can add things like strings to an intent and send those along too
	//public final static String EXTRA_MESSAGE = "ncsu.carjuice.main.MESSAGE";

    /** Called when the activity is first created. */
   	
    @Override
    //@param savedInstanceState
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

/*   
//****On the emulator this code is causing force close due to not being able to 
//    get GPS or network location, commented out for now 

//----------------------------------get location--------------------
       
      
        LocationResult locationResult = new LocationResult(){
            @Override
            public void gotLocation(Location location){
                //Got the location!
            	// now use location.methods to get long and lat to pass to http request
            	JSONWorker jaSON = new JSONWorker();
            	jaSON.getUrlMyLocation(location.getLongitude()+"", location.getLongitude()+"");
            }
        };
        GetLocation myLocation = new GetLocation();
        myLocation.getLocation(this, locationResult);        
        
//----------------------------------end get location--------------------        
 */       

        Intent intent = new Intent(this, ResultsListActivity.class);
        startActivity(intent);
        //Open the search dialogue on app-launch
        //onSearchRequested();
        
        
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