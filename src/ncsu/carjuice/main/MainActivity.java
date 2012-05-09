package ncsu.carjuice.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	//Creating the search query string to send along bundled with the intent to list view
	public final static String SEARCH_QUERY = "ncsu.carjuice.main.MESSAGE";

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
        
    }
    
    //Message sent by "Locate" button. Sends an intent to the listview activity
    public void sendLocation(View view) {
    	
        // Do something in response to button
    	Intent intent = new Intent(this, StationsListActivity.class);
    	
    	//Starts instance of the activity called by intent parameter, in this case: DisplayMessageActivity
    	//Intent also carries with it the SEARCH_QUERY
    	startActivity(intent);
    }    

    //Message sent by Search button. Sends an intent to the listview activity
    public void sendQuery(View view) {
    	
        // Do something in response to button
    	Intent intent = new Intent(this, StationsListActivity.class);
    	
    	
    	EditText editText = (EditText) findViewById(R.id.edit_address);
    	String message = editText.getText().toString();
    	
    	if(message.length()==0)
    	{
    		final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
    		alertDialog.setTitle("Please Enter Address");
    		alertDialog.setMessage("Enter a complete address, a city, state, or zip");
    		
    		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int which) {
    		 
    		       alertDialog.dismiss();
    		 
    		    } });
    		
    		alertDialog.show();
    		
    	}
    	else{
    		intent.putExtra(SEARCH_QUERY, message);
    		//Starts instance of the activity called by intent parameter, in this case: DisplayMessageActivity
        	//Intent also carries with it the SEARCH_QUERY
        	startActivity(intent);
    	}
    	
    }

}