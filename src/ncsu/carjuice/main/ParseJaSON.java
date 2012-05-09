/**
 * CarJuice Main
 */
package ncsu.carjuice.main;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/**
 * @author Jason Brown
 * @version 1.4
 * JSONWorker Class- Gets JSON data from URL and parses it into an array of Stations, with each station populated with information for that station.
 */
public class ParseJaSON {
	private ArrayList<StationInfo> stationsArrayList;
	JSONObject mainJSONObject;
	static final String LOG_TAG = "ParseJaSON";
	
			
	//empty constructor used for testing
	public ParseJaSON(JSONObject JSONObject){
		
		parseStationInfo(JSONObject);
		
	}
	
	/**
	 * Default constructor, mostly using for testing currently
	 
	public ParseJaSON(JSONObject mainJSONObject) {	
		this.mainJSONObject = mainJSONObject;
	}
	*/
	
	/**
	 * Method to return a StationInfo Array, loaded up with all stations and their info
	 * @return ArrayList<StationInfo>
	 */
	public ArrayList<StationInfo> getStationArray(){
		return this.stationsArrayList;
	}
	
	
//---------------------------------------------Private Methods--------------------------------------------------------------------------------------------------------
	/**
	 * Method to parse JSON Object and populate StationInfo Array with Stations and details for each
	 */
	private void parseStationInfo(JSONObject JSONObject){ 								
		JSONArray JSONStationsArray = null;
		StationInfo station = null;
		
		if(JSONObject != null && !JSONObject.equals("")){
			mainJSONObject = JSONObject;
		}
		else{
			Log.e(LOG_TAG, "JSON object was null or empty string");
		}
	
		try {
			JSONStationsArray = mainJSONObject.getJSONArray("fuel_stations");
			Log.e(LOG_TAG, "The number of stations to parse is =" + JSONStationsArray.length());
		}catch (JSONException e) {
			Log.e(LOG_TAG, "Could not find stations array");
		}
		// setup and arrayList to hold each station object
		stationsArrayList = new ArrayList<StationInfo>();
		
		for (int i = 0; i < JSONStationsArray.length(); i++) {
			//create station to assign value
			station = new StationInfo();
			
			try {
				//set station name
				station.setName(JSONStationsArray.getJSONObject(i).getString("station_name").toString());
				Log.d(LOG_TAG, "1. station_name= " + JSONStationsArray.getJSONObject(i).getString("station_name").toString());
				
				//set station address
				station.setAddress(JSONStationsArray.getJSONObject(i).getString("street_address").toString());
				Log.d(LOG_TAG, "2. street_address= " + JSONStationsArray.getJSONObject(i).getString("street_address").toString());
				
				// set intersection directions
				station.setIntersectionDirections(JSONStationsArray.getJSONObject(i).getString("intersection_directions").toString());
				Log.d(LOG_TAG, "3. intersection_directions= " + JSONStationsArray.getJSONObject(i).getString("intersection_directions").toString());
				
				// set station city
				station.setCity(JSONStationsArray.getJSONObject(i).getString("city").toString());
				Log.d(LOG_TAG, "4. city= " + JSONStationsArray.getJSONObject(i).getString("city").toString());
				
				// set station state
				station.setState(JSONStationsArray.getJSONObject(i).getString("state").toString());
				Log.d(LOG_TAG, "5. state= " + JSONStationsArray.getJSONObject(i).getString("state").toString());
				
				// set station zip
				station.setZip(JSONStationsArray.getJSONObject(i).getString("zip").toString());
				Log.d(LOG_TAG, "6. zip= " + JSONStationsArray.getJSONObject(i).getString("zip").toString());
				
				// set station phone number
				station.setPhoneNumber(JSONStationsArray.getJSONObject(i).getString("station_phone").toString());
				Log.d(LOG_TAG, "7. station_phone= " + JSONStationsArray.getJSONObject(i).getString("station_phone").toString());
				
				
				// set Groups with access
				station.setGroupsWithAccess(JSONStationsArray.getJSONObject(i).getString("groups_with_access_code").toString());
				Log.d(LOG_TAG, "8. groups_with_access_code= " + JSONStationsArray.getJSONObject(i).getString("groups_with_access_code").toString());
				
				//set station operation hours
				station.setOperationHours(JSONStationsArray.getJSONObject(i).getString("access_days_time").toString());
				Log.d(LOG_TAG, "9. access_days_time= " + JSONStationsArray.getJSONObject(i).getString("access_days_time").toString());
				
				// set cards accepted 
				station.setCardsAccepted(JSONStationsArray.getJSONObject(i).getString("cards_accepted").toString());
				Log.d(LOG_TAG, "10. cards_accepted= " + JSONStationsArray.getJSONObject(i).getString("cards_accepted").toString());
				
				// set station owner type
				station.setOwnerType(JSONStationsArray.getJSONObject(i).getString("owner_type_code").toString());
				Log.d(LOG_TAG, "11. owner_type_code= " + JSONStationsArray.getJSONObject(i).getString("owner_type_code").toString());
				
				// set Charging Network
				station.setChargingNetwork(JSONStationsArray.getJSONObject(i).getString("ev_network").toString());
				Log.d(LOG_TAG, "12. ev_network= " + JSONStationsArray.getJSONObject(i).getString("ev_network").toString());
				
				// set Charging Network Web site
				station.setChargingNetworkWebsite(JSONStationsArray.getJSONObject(i).getString("ev_network_web").toString());
				Log.d(LOG_TAG, "13. ev_network_web= " + JSONStationsArray.getJSONObject(i).getString("ev_network_web").toString());
				
				// set geocode status
				station.setGeocodeStatus(JSONStationsArray.getJSONObject(i).getString("geocode_status").toString());
				Log.d(LOG_TAG, "14. geocode_status= " + JSONStationsArray.getJSONObject(i).getString("geocode_status").toString());
				
				// set latitude
				station.setLatitude(JSONStationsArray.getJSONObject(i).getDouble("latitude")+"");
				Log.d(LOG_TAG, "15. latitude= " + JSONStationsArray.getJSONObject(i).getDouble("latitude")+"");
				
				//set longitude
				station.setLongitude(JSONStationsArray.getJSONObject(i).getDouble("longitude")+"");
				Log.d(LOG_TAG, "16. longitude= " + JSONStationsArray.getJSONObject(i).getDouble("longitude")+"");
				
				// set distance
				station.setDistance(JSONStationsArray.getJSONObject(i).getDouble("distance")+"");
				Log.d(LOG_TAG, "17. distance= " + JSONStationsArray.getJSONObject(i).getDouble("distance")+"");
				
				//set Number Level 1 Chargers
				station.setNumLevel1Chargers(JSONStationsArray.getJSONObject(i).optInt("ev_level1_evse_num", 0)+"");
				Log.d(LOG_TAG, "18. ev_level1_evse_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_level1_evse_num", 0)+"");
				
				//set Number Level 2 Chargers
				station.setNumLevel2Chargers(JSONStationsArray.getJSONObject(i).optInt("ev_level2_evse_num", 0 )+"");
				Log.d(LOG_TAG, "19. ev_level2_evse_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_level2_evse_num", 0 )+"");
				
				//set Number of DC Fast Chargers
				station.setNumDcFastChargers(JSONStationsArray.getJSONObject(i).optInt("ev_dc_fast_num", 0)+"");
				Log.d(LOG_TAG, "20. ev_dc_fast_num= " + JSONStationsArray.getJSONObject(i).optInt("ev_dc_fast_num", 0)+"");
				
				//set station ID #
				station.setStationId(JSONStationsArray.getJSONObject(i).getInt("id")+"");
				Log.d(LOG_TAG, "21. id= " + JSONStationsArray.getJSONObject(i).getInt("id")+"");
				
			}catch (JSONException e) {
				Log.e(LOG_TAG, "Could not parse station data");
			}
			//add station with full info to array
			stationsArrayList.add(station);
		} //end for loop
		
	} //end JSONParser method
//---------------------------------------------End Private Method-------------------------------------------------------------------------------------------------
	
} //end class