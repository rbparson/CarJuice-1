package ncsu.carjuice.main;


import ncsu.carjuice.main.GetLocation.LocationResult;
import ncsu.carjuice.main.ListViewAdapter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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

        
        
        
        GetJaSON JSONParser = new GetJaSON();
       // StationInfo[] stationsArray= JSONParser.getStationArray();


        
   

 */       

        
        //startActivity(new Intent("ncsu.carjuice.ListViewAdapter"));
        //Open the search dialogue on app-launch
        //onSearchRequested();

        
        
    }

    //Message sent by "Locate" button. Sends an intent to the listview activity
    public void sendMessage(View view) {
    	
        // Do something in response to button
    	Intent intent = new Intent(this, ResultsListActivity.class);
    	
    	
    	//EditText editText = (EditText) findViewById(R.id.edit_address);
    	//String message = editText.getText().toString();
    	//intent.putExtra(SEARCH_QUERY, message);
    	
    	
    	//Starts instance of the activity called by intent parameter, in this case: DisplayMessageActivity
    	//Intent also carries with it the SEARCH_QUERY
    	startActivity(intent);
    }

}