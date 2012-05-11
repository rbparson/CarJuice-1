package ncsu.carjuice.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class StationsListActivity extends Activity {
	
	static final String KEY_NAME = "name";							
	static final String KEY_ADDRESS = "address";					
	static final String KEY_INTERSECTION = "intersection";				//Brief additional information about how to locate the station.
	static final String KEY_CITY = "city";								// city station is in
	static final String KEY_STATE = "state";							//The two character U.S. state or  of the station's location.
	static final String KEY_ZIP = "zip";								// postal code
	static final String KEY_PHONE_NUMBER = "phoneNumber";				// phone number
	static final String KEY_GROUPS_WITH_ACCESS = "groupsWithAccess";	//A description of who is allowed to access the station
	static final String KEY_OPERATING_HOURS = "operatingHours";			// hours station is open
	static final String KEY_OWNER_TYPE = "ownerType";					//A space-separated list of owner codes.P=Privately owned, T=Utility owned, FG=Federal government owned, LG=Local government owned, SG=State government owned
	static final String KEY_CHARGING_NETWORK = "chargingNetwork";		//the name of the EVSE network, if applicable.
	static final String KEY_LATITUDE = "latitude";						//The latitude of the station's address Range: -90 to 90
	static final String KEY_LONGITUDE = "longitude";					//The longitude of the station's address Range -180 to 180
	static final String KEY_DISTANCE = "distance";						//distance to station
	static final String KEY_LEVEL1_CHARGERS = "level1Chargers";			//The number of Level 1 EVSE (standard 110V outlet).
	static final String KEY_LEVEL2_CHARGERS = "level2Chargers";			//The number of Level 2 EVSE (J1772 connector).
	static final String KEY_DC_FAST_CHARGERS = "dcFastChargers";		//The number of DC Fast Chargers
	static final String KEY_STATION_ID = "stationId";					//A unique identifier for this specific station.
	static final String LOG_TAG = "JSONParsing";						// Log Tag for debugging
	static final String PIN_LONG = "ncsu.carjuice.main.PIN_LONG";		//String extras to send with intent to map-view
	static final String PIN_LAT = "ncsu.carjuice.main.PIN_LAT";			//String extras to send with intent to map-view
	private String query="";											//user query from search box
	private String longitude;											//longitude
	private String latitude;											//latitude
	private ListView list;
	private ListViewAdapter adapter;
	private JSONObject JSONObject;
	private final Context context = this;
    private ArrayList<HashMap<String, String>> stationsList = new ArrayList<HashMap<String, String>>();
	
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
    	
    	Intent queryIntent = getIntent();
    	
    	if(!queryIntent.getStringExtra(MainActivity.SEARCH_QUERY).equals("")){
    		query = queryIntent.getStringExtra(MainActivity.SEARCH_QUERY);
    		JSONObject = (new GetJSONObject(query, 30).returnJSONObject() );  //@@@@@@@@@@@@@@@@@still using hard coded radius param of 30@@@@@@@@@@@@@@@@@@@@@@@@
    		Log.d(LOG_TAG, "JSON object set using search query constructor");
    	}
    	else{
    		longitude = queryIntent.getStringExtra(MainActivity.LONG);
        	latitude = queryIntent.getStringExtra(MainActivity.LAT);
        	JSONObject = (new GetJSONObject(latitude, longitude, 30).returnJSONObject() );  //@@@@@@@@@@@@@@@@@still using hard coded radius param of 30@@@@@@@@@@@@@@@@@@@@@@@@
    		Log.d(LOG_TAG, "JSON object set using search query constructor");
    	}
    	
    	JSONArray JSONStationsArray = null;
		if(JSONObject != null && !JSONObject.equals("")){
			try {
				JSONStationsArray = JSONObject.getJSONArray("fuel_stations");
				Log.d(LOG_TAG, "The number of stations to parse is =" + JSONStationsArray.length());
				if(JSONStationsArray.length() == 0){
					invalidSearchAlert(); // error invalid input, pops up dialog and then sends back to MainActivity
				}
			}catch (JSONException e) {
				Log.e(LOG_TAG, "Could not find stations array");
			}

    	super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		
			for (int i = 0; i < JSONStationsArray.length(); i++) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				try {
					map.put(KEY_NAME, JSONStationsArray.getJSONObject(i).getString("station_name").toString() );
					Log.d(LOG_TAG, "1. station_name= " + JSONStationsArray.getJSONObject(i).getString("station_name").toString());
					
					map.put(KEY_ADDRESS, JSONStationsArray.getJSONObject(i).getString("street_address").toString());
					Log.d(LOG_TAG, "2. street_address= " + JSONStationsArray.getJSONObject(i).getString("street_address").toString());
					
					map.put(KEY_INTERSECTION, JSONStationsArray.getJSONObject(i).getString("intersection_directions").toString());
					Log.d(LOG_TAG, "3. intersection_directions= " + JSONStationsArray.getJSONObject(i).getString("intersection_directions").toString());
					
					map.put(KEY_CITY, JSONStationsArray.getJSONObject(i).getString("city").toString());
					Log.d(LOG_TAG, "4. city= " + JSONStationsArray.getJSONObject(i).getString("city").toString());
					
					map.put(KEY_STATE, JSONStationsArray.getJSONObject(i).getString("state").toString());
					Log.d(LOG_TAG, "5. state= " + JSONStationsArray.getJSONObject(i).getString("state").toString());
					
					map.put(KEY_ZIP, JSONStationsArray.getJSONObject(i).getString("zip").toString());
					Log.d(LOG_TAG, "6. zip= " + JSONStationsArray.getJSONObject(i).getString("zip").toString());
					
					map.put(KEY_PHONE_NUMBER, JSONStationsArray.getJSONObject(i).getString("station_phone").toString());
					Log.d(LOG_TAG, "7. station_phone= " + JSONStationsArray.getJSONObject(i).getString("station_phone").toString());
					
					map.put(KEY_GROUPS_WITH_ACCESS, JSONStationsArray.getJSONObject(i).getString("groups_with_access_code").toString());
					Log.d(LOG_TAG, "8. groups_with_access_code= " + JSONStationsArray.getJSONObject(i).getString("groups_with_access_code").toString());

					map.put(KEY_OPERATING_HOURS, JSONStationsArray.getJSONObject(i).getString("access_days_time").toString());
					Log.d(LOG_TAG, "9. access_days_time= " + JSONStationsArray.getJSONObject(i).getString("access_days_time").toString());

					map.put(KEY_OWNER_TYPE, JSONStationsArray.getJSONObject(i).getString("owner_type_code").toString());
					Log.d(LOG_TAG, "10. owner_type_code= " + JSONStationsArray.getJSONObject(i).getString("owner_type_code").toString());

					map.put(KEY_CHARGING_NETWORK, JSONStationsArray.getJSONObject(i).getString("ev_network").toString());
					Log.d(LOG_TAG, "11. ev_network= " + JSONStationsArray.getJSONObject(i).getString("ev_network").toString());

					map.put(KEY_LATITUDE, JSONStationsArray.getJSONObject(i).getDouble("latitude")+"");
					Log.d(LOG_TAG, "12. latitude= " + JSONStationsArray.getJSONObject(i).getDouble("latitude"));

					map.put(KEY_LONGITUDE, JSONStationsArray.getJSONObject(i).getDouble("longitude")+"");
					Log.d(LOG_TAG, "13. longitude= " + JSONStationsArray.getJSONObject(i).getDouble("longitude"));

					map.put(KEY_DISTANCE, JSONStationsArray.getJSONObject(i).getDouble("distance")+"");
					Log.d(LOG_TAG, "14. distance= " + JSONStationsArray.getJSONObject(i).getDouble("distance"));

					map.put(KEY_LEVEL1_CHARGERS, JSONStationsArray.getJSONObject(i).optInt("ev_level1_evse_num", 0)+"");
					Log.d(LOG_TAG, "15. ev_level1_evse_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_level1_evse_num", 0));

					map.put(KEY_LEVEL2_CHARGERS, JSONStationsArray.getJSONObject(i).optInt("ev_level2_evse_num", 0 )+"");
					Log.d(LOG_TAG, "16. ev_level2_evse_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_level2_evse_num", 0 ));

					map.put(KEY_DC_FAST_CHARGERS, JSONStationsArray.getJSONObject(i).optInt("ev_dc_fast_num", 0)+"");
					Log.d(LOG_TAG, "17. ev_dc_fast_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_dc_fast_num", 0));

					map.put(KEY_STATION_ID, JSONStationsArray.getJSONObject(i).getInt("id")+"");
					Log.d(LOG_TAG, "18. id= " + JSONStationsArray.getJSONObject(i).getInt("id"));

					//adding HashList to ArrayList
					stationsList.add(map);
				} catch (JSONException e) {
					Log.e(LOG_TAG, "Could not parse station data");
				}
				
			} //end if loop
			
	    list = (ListView) findViewById(R.id.list);
        // Getting adapter by passing JSON data ArrayList
        adapter = new ListViewAdapter(this, stationsList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> parent, View view,int position, long id) {            	
				final Dialog dialog = new Dialog(context);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.station_details);
				
				TextView stationName = (TextView) dialog.findViewById(R.id.stationName);
				stationName.setText(stationsList.get(position).get(KEY_NAME));
				
				TextView address = (TextView) dialog.findViewById(R.id.address);
				address.setText(stationsList.get(position).get(KEY_ADDRESS) + "\n" + stationsList.get(position).get(KEY_CITY)+ ", "+ stationsList.get(position).get(KEY_STATE)+"   "+ stationsList.get(position).get(KEY_ZIP));
				
				TextView distance = (TextView) dialog.findViewById(R.id.distance);
				distance.setText("Distance: "+ stationsList.get(position).get(KEY_DISTANCE).substring(0, 4) + " Miles");
				
				if(stationsList.get(position).get(KEY_INTERSECTION).equals("null")) {
					TextView intersection = (TextView) dialog.findViewById(R.id.intersection);
					intersection.setText("Intersection: None Provided");
				}
				else{
					TextView intersection = (TextView) dialog.findViewById(R.id.intersection);
					intersection.setText("Intersection: "+stationsList.get(position).get(KEY_INTERSECTION));
				}
				
				final Intent mapIntent = new Intent(context, MapsActivity.class);
				
				mapIntent.putExtra(PIN_LONG, stationsList.get(position).get(KEY_LONGITUDE));
		    	mapIntent.putExtra(PIN_LAT, stationsList.get(position).get(KEY_LATITUDE));
				
				//Button to send to maps view
				ImageButton mapButton = (ImageButton) dialog.findViewById(R.id.mapButton);
				
				// if button is clicked, close the custom dialog
				mapButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						startActivity(mapIntent);
					}
				}); //end onClick
				
				//Button to send launch navigation
				ImageButton navButton = (ImageButton) dialog.findViewById(R.id.navButton);
				// if button is clicked, close the custom dialog
				navButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						Intent i = new Intent(Intent.ACTION_VIEW, 
								Uri.parse("google.navigation:q=New+York+NY")); 
								startActivity(i);
					}
				}); //end onClick
				
				TextView labelMap = (TextView) dialog.findViewById(R.id.labelMap);
				labelMap.setText("Map it");
			
				TextView labelNav = (TextView) dialog.findViewById(R.id.labelNav);
				labelNav.setText("Navigate");
			
				
				TextView level1Chargers = (TextView) dialog.findViewById(R.id.level1Chargers);
				level1Chargers.setText("Level 1 Chargers: "+stationsList.get(position).get(KEY_LEVEL1_CHARGERS));
				
				TextView level2Chargers = (TextView) dialog.findViewById(R.id.level2Chargers);
				level2Chargers.setText("Level 2 Chargers: "+stationsList.get(position).get(KEY_LEVEL2_CHARGERS));
				
				TextView dcFastChargers = (TextView) dialog.findViewById(R.id.dcFastChargers);
				dcFastChargers.setText("DC Fast Chargers: "+stationsList.get(position).get(KEY_DC_FAST_CHARGERS));
				
				TextView operatingHours = (TextView) dialog.findViewById(R.id.operatingHours);
				operatingHours.setText("Operating Hours: "+stationsList.get(position).get(KEY_OPERATING_HOURS));
				
				//Parsing various kinds of owners
				if(stationsList.get(position).get(KEY_OWNER_TYPE).equals("SG")){
					TextView ownerType = (TextView) dialog.findViewById(R.id.ownerType);
					ownerType.setText("Owned by: State Government");
				}
				else if(stationsList.get(position).get(KEY_OWNER_TYPE).equals("LG")){
					TextView ownerType = (TextView) dialog.findViewById(R.id.ownerType);
					ownerType.setText("Owned by: Local Government");
				}
				else if(stationsList.get(position).get(KEY_OWNER_TYPE).equals("FG")){
					TextView ownerType = (TextView) dialog.findViewById(R.id.ownerType);
					ownerType.setText("Owned by: Federal Government");
				}
				else if(stationsList.get(position).get(KEY_OWNER_TYPE).equals("P")){
					TextView ownerType = (TextView) dialog.findViewById(R.id.ownerType);
					ownerType.setText("Owned by: Private Owner");
				}
				else if(stationsList.get(position).get(KEY_OWNER_TYPE).equals("T")){
					TextView ownerType = (TextView) dialog.findViewById(R.id.ownerType);
					ownerType.setText("Owned by: Utility Owner");
				}
				
				
				if(stationsList.get(position).get(KEY_CHARGING_NETWORK).equals("null")){
					TextView network = (TextView) dialog.findViewById(R.id.network);
					network.setText("Network: None");
				}
				else{
					TextView network = (TextView) dialog.findViewById(R.id.network);
					network.setText("Network: "+ stationsList.get(position).get(KEY_CHARGING_NETWORK));
				}
				
				TextView groupsWithAccess = (TextView) dialog.findViewById(R.id.groupsWithAccess);
				groupsWithAccess.setText("Groups With Access: "+ stationsList.get(position).get(KEY_GROUPS_WITH_ACCESS));
								
				if(stationsList.get(position).get(KEY_PHONE_NUMBER).equals("null")){
					TextView telephone = (TextView) dialog.findViewById(R.id.telephone);
					telephone.setText("Station Phone: None Provided");
				}
				else{
					TextView telephone = (TextView) dialog.findViewById(R.id.telephone);
					telephone.setText("Station Phone: " + stationsList.get(position).get(KEY_PHONE_NUMBER));
				}
				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);			
				
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						dialog.dismiss();
					}
				}); // end onClick
				
				//Display the station details dialog
				dialog.show();
		      
	    	} //end onItemClick
	  });

	} //end if loop
		else{
			Log.e(LOG_TAG, "JSON object was null or empty string");
		}
		
    } //end onCreate()
 
//------------------------------private method----------------------------------------------------------------------
    private void invalidSearchAlert(){
		final AlertDialog alertDialog = new AlertDialog.Builder(StationsListActivity.this).create();
		alertDialog.setTitle("Invalid Input");
		alertDialog.setMessage("Please Enter a Valid Address, City, State, or Zip Code");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		    	  alertDialog.dismiss();
		 	      Intent intent = new Intent(context, MainActivity.class);
		 	      startActivity(intent);
		    } });
		alertDialog.show();	
    } //end invalidSearchAlert
//------------------------------ end private method----------------------------------------------------------------------
    
} //end class    
