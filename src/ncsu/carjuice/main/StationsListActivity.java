package ncsu.carjuice.main;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.view.Window;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StationsListActivity extends Activity { 

	static final String KEY_NAME = "name";							
	static final String KEY_ADDRESS = "address";					
	static final String KEY_INTERSECTION = "intersection";				//Brief additional information about how to locate the station.
	static final String KEY_CITY = "city";							
	static final String KEY_STATE = "state";							//The two character U.S. state or Canadian province code of the station's location.
	static final String KEY_ZIP = "zip";							
	static final String KEY_PHONE_NUMBER = "phoneNumber";
	static final String KEY_GROUPS_WITH_ACCESS = "groupsWithAccess";	//A description of who is allowed to access the station and other station access information
	static final String KEY_OPERATING_HOURS = "operatingHours";
	static final String KEY_PAYMENT_TYPES = "paymentTypes";				//A space-separated list of payment methods accepted. 
																		/**
																		 * Possible payment methods: 
																		 * A = American Express 
																		 * D = Dicovery
																		 * M = Mastercard
																		 * V = Visa
																		 * Cash 
																		 * Checks 
																		 * CFN 
																		 * CleanEnergy 
																		 * FuelMan 
																		 * GasCard 
																		 * PHH 
																		 * Voyager 
																		 * Wright_Exp
																		 */
	
	static final String KEY_OWNER_TYPE = "ownerType";					//A space-separated list of owner codes.
																		/**
																		 * P=Privately owned
																		 * T=Utility owned
																		 * FG=Federal government owned
																		 * LG=Local government owned, SG=State government owned.
																		 */
	
	static final String KEY_CHARGING_NETWORK = "chargingNetwork";		//the name of the EVSE network, if applicable.
	static final String KEY_LATITUDE = "latitude";						//The latitude of the station's address Range: -90 to 90
	static final String KEY_LONGITUDE = "longitude";					//The longitude of the station's address Range -180 to 180
	static final String KEY_DISTANCE = "distance";
	static final String KEY_LEVEL1_CHARGERS = "level1Chargers";			//The number of Level 1 EVSE (standard 110V outlet).
	static final String KEY_LEVEL2_CHARGERS = "level2Chargers";			//The number of Level 2 EVSE (J1772 connector).
	static final String KEY_DC_FAST_CHARGERS = "dcFastChargers";		//The number of DC Fast Chargers
	static final String KEY_STATION_ID = "stationId";					//A unique identifier for this specific station.
	
	
	ListView list;
    ListViewAdapter adapter;
    JSONObject JSONObject;
    final Context context = this;
    static final String LOG_TAG = "JSONParsing";
    
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
	
		ArrayList<HashMap<String, String>> stationsList = new ArrayList<HashMap<String, String>>();
		
		//using temp params
		JSONObject = (new GetJSONObject("27607", 10).returnJSONObject() );
		Log.d(LOG_TAG, "JSON object set");
		
		JSONArray JSONStationsArray = null;
		
		if(JSONObject != null && !JSONObject.equals("")){
			try {
				JSONStationsArray = JSONObject.getJSONArray("fuel_stations");
				Log.d(LOG_TAG, "The number of stations to parse is =" + JSONStationsArray.length());
			}catch (JSONException e) {
				Log.e(LOG_TAG, "Could not find stations array");
			}
			
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

					map.put(KEY_PAYMENT_TYPES, JSONStationsArray.getJSONObject(i).getString("cards_accepted").toString());
					Log.d(LOG_TAG, "10. cards_accepted= " + JSONStationsArray.getJSONObject(i).getString("cards_accepted").toString());

					map.put(KEY_OWNER_TYPE, JSONStationsArray.getJSONObject(i).getString("owner_type_code").toString());
					Log.d(LOG_TAG, "11. owner_type_code= " + JSONStationsArray.getJSONObject(i).getString("owner_type_code").toString());

					map.put(KEY_CHARGING_NETWORK, JSONStationsArray.getJSONObject(i).getString("ev_network").toString());
					Log.d(LOG_TAG, "12. ev_network= " + JSONStationsArray.getJSONObject(i).getString("ev_network").toString());

					map.put(KEY_LATITUDE, JSONStationsArray.getJSONObject(i).getDouble("latitude")+"");
					Log.d(LOG_TAG, "13. latitude= " + JSONStationsArray.getJSONObject(i).getDouble("latitude"));

					map.put(KEY_LONGITUDE, JSONStationsArray.getJSONObject(i).getDouble("longitude")+"");
					Log.d(LOG_TAG, "14. longitude= " + JSONStationsArray.getJSONObject(i).getDouble("longitude"));

					map.put(KEY_DISTANCE, JSONStationsArray.getJSONObject(i).getDouble("distance")+"");
					Log.d(LOG_TAG, "15. distance= " + JSONStationsArray.getJSONObject(i).getDouble("distance"));

					map.put(KEY_LEVEL1_CHARGERS, JSONStationsArray.getJSONObject(i).optInt("ev_level1_evse_num", 0)+"");
					Log.d(LOG_TAG, "16. ev_level1_evse_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_level1_evse_num", 0));

					map.put(KEY_LEVEL2_CHARGERS, JSONStationsArray.getJSONObject(i).optInt("ev_level2_evse_num", 0 )+"");
					Log.d(LOG_TAG, "17. ev_level2_evse_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_level2_evse_num", 0 ));

					map.put(KEY_DC_FAST_CHARGERS, JSONStationsArray.getJSONObject(i).optInt("ev_dc_fast_num", 0)+"");
					Log.d(LOG_TAG, "18. ev_dc_fast_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_dc_fast_num", 0));

					map.put(KEY_STATION_ID, JSONStationsArray.getJSONObject(i).getInt("id")+"");
					Log.d(LOG_TAG, "19. id= " + JSONStationsArray.getJSONObject(i).getInt("id"));

					//adding HashList to ArrayList
					stationsList.add(map);
					
				} catch (JSONException e) {
					Log.e(LOG_TAG, "Could not parse station data");
				}
			} //end if loop
			
	    list=(ListView)findViewById(R.id.list);
		 
        // Getting adapter by passing JSON data ArrayList
        adapter = new ListViewAdapter(this, stationsList);
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
            	// custom dialog
				final Dialog dialog = new Dialog(context);
				dialog.setContentView(R.layout.station_details);
				
				//Set the title, probably station name?
				dialog.setTitle("Title...");
				
				TextView address = (TextView) dialog.findViewById(R.id.address);
				address.setText("12 Shepherd Drive, Raleigh, NC 27607");
				
				TextView distance = (TextView) dialog.findViewById(R.id.distance);
				distance.setText("Distance: 6.9 Miles");
				
				TextView intersection = (TextView) dialog.findViewById(R.id.intersection);
				intersection.setText("First right after intersection of Hillsborough St. and Dixie Trail");
				
				//Button to send to maps view
				Button mapButton = (Button) dialog.findViewById(R.id.mapButton);
				// if button is clicked, close the custom dialog
				mapButton.setOnClickListener(new OnClickListener() 
				{
					public void onClick(View v) 
					{
						//Send to map View here
					}
				});
				
				TextView level1Chargers = (TextView) dialog.findViewById(R.id.level1Chargers);
				level1Chargers.setText("1 Level 1 Charger");
				
				TextView level2Chargers = (TextView) dialog.findViewById(R.id.level2Chargers);
				level2Chargers.setText("1 Level 2 Charger");
				
				TextView dcFastChargers = (TextView) dialog.findViewById(R.id.dcFastChargers);
				dcFastChargers.setText("1 DC Fast Charger");
				
				//Need to parse the codes to words
				TextView ownerType = (TextView) dialog.findViewById(R.id.ownerType);
				ownerType.setText("Owner: Privately Owned");
				
				//Should we just make this text a link to the website?
				TextView network = (TextView) dialog.findViewById(R.id.network);
				network.setText("Network: Sharepoint Network");
				
				TextView groupsWithAccess = (TextView) dialog.findViewById(R.id.groupsWithAccess);
				groupsWithAccess.setText("All groups have access");
				
				TextView cardsAccepted = (TextView) dialog.findViewById(R.id.cardsAccepted);
				cardsAccepted.setText("Payment Methods: All major credit cards accepted");
				
				TextView telephone = (TextView) dialog.findViewById(R.id.telephone);
				telephone.setText("1-800-123-4567");

				Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);			
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() 
				{
					public void onClick(View v) 
					{
						dialog.dismiss();
					}
				});

				//Display the station details dialog
				dialog.show();
		      
	    	} //end onClick
	  });

	} //end if loop
		else{
			Log.e(LOG_TAG, "JSON object was null or empty string");
		}
		
    } //end onCreate()
} //end class    
    
    


	