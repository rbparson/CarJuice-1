/**
 * CarJuice Main
 */
package ncsu.carjuice.main;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
/**
 * @author Jason Brown
 * @version 1.3
 * JSONWorker Class- Gets JSON data from URL and parses it into an array of Stations, with each station populated with information for that station.
 */
public class ParseJaSON {


	StationInfo[] stationsArray;
	JSONObject mainJSONObject;
	static final String LOG_TAG = "ParseJaSON";
	private String location;
	private String latitude;
	private String longitude;
	
	/**
	 * temp string for test parsing
	 */
	private String jsonString ="{latitude: 35.8019142,longitude: -78.6875364,precision: {name: \"postal_code\",types: [\"postal_code\"],value: 5},station_locator_url: " +
			"\"http://www.afdc.energy.gov/afdc/locator/stations/\",total_results: 23,offset: 0,fuel_stations: [{access_days_time: \"24 hours daily\",bd_blends: null,cards_accepted: null,city: " +
			"\"Raleigh\",date_last_confirmed: \"2012-02-29\",expected_date: null,fuel_type_code: \"ELEC\",geocode_status: \"200-8\",groups_with_access_code: \"Public - see hours\",intersection_directions:" +
			" null,latitude: 35.7828039,longitude: -78.683243,open_date: \"2012-02-01\",owner_type_code: \"SG\",plus4: null,station_name: \"North Carolina State University - Joyner Visitor Center\",station_phone:" +
			" \"919-513-1200\",status_code: \"E\",street_address: \"1210 Varsity Dr\",zip: \"27606\",state: \"NC\",ng_fill_type_code: null,ng_psi: null,ev_level1_evse_num: 1,ev_level2_evse_num: 1,ev_dc_fast_num:" +
			" null,ev_other_evse: null,ev_network: null,ev_network_web: null,id: 43880,updated_at: null,distance: 1.33944},{access_days_time: \"24 hours daily\",bd_blends: null,cards_accepted: null,city:" +
			" \"Raleigh\",date_last_confirmed: \"2012-02-29\",expected_date: null,fuel_type_code: \"ELEC\",geocode_status: \"GPS\",groups_with_access_code: \"Public - see hours\",intersection_directions: " +
			"\"Gorman Ave between Varsity and western\",latitude: 35.782144,longitude: -78.686442,open_date: \"2012-02-20\",owner_type_code: \"SG\",plus4: null,station_name: \"North Carolina State University " +
			"- McKimmon Center and Solar House\",station_phone: \"503-892-7345\",status_code: \"E\",street_address: \"1201 Gorman Ave\",zip: \"27606\",state: \"NC\",ng_fill_type_code: null,ng_psi: null," +
			"ev_level1_evse_num: null,ev_level2_evse_num: 2,ev_dc_fast_num: null,ev_other_evse: null,ev_network: null,ev_network_web: null,id: 43324,updated_at: \"2012-03-14T17:33:35Z\",distance: 1.36443}]}";
		
	//empty constructor used for testing
	public ParseJaSON(){
		
		parseStationInfo();
		
	}
	/**
	 * Default constructor, mostly using for testing currently
	 */
	public ParseJaSON(JSONObject mainJSONObject) {	
		this.mainJSONObject = mainJSONObject;
	}
	
	
	/**
	 * Method to return a StationInfo Array, loaded up with all stations and their info
	 * @return StationsInfo[]
	 */
	public StationInfo[] getStationArray(){
		return this.stationsArray;
	}
	
	
//---------------------------------------------Private Methods--------------------------------------------------------------------------------------------------------
	//using JSON string from http request below to test
	//http://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?api_key=f46ab87903614bc4dc864b057bc0fb543d197900&status=E&limit=2&fuel_type=ELEC&location=27607
	/**
	 * Method to parse JSON Object and populate StationInfo Array with Stations and details for each
	 */
	private void parseStationInfo(){ 									//	JSONObject mainJSONObject
		
		/**
		 * temp commented out to use string instead of JSON object
		if(mainJSONObject != null && !mainJSONObject.equals("")){
			this.mainJSONObject = mainJSONObject;
		}
		else{
			Log.e(LOG_TAG, "JSON object was null or empty string");
		}
		*/
		JSONArray JSONStationsArray = null;
		StationInfo station = null;
		
		//temp code uncommented to make sample array from sample string instead of JSON object
		///**
		try {
			mainJSONObject = new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Could not find MainJSONobject");
		}
		//*/
		
		try {
			JSONStationsArray = mainJSONObject.getJSONArray("fuel_stations");
			Log.e(LOG_TAG, "The number of stations to parse is =" + JSONStationsArray.length());
		} catch (JSONException e) {
			Log.e(LOG_TAG, "Could not find stations array");
		}
		// setup and array to hold each station object
		stationsArray = new StationInfo[JSONStationsArray.length()];
		
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
				
			} catch (JSONException e) {
				Log.e(LOG_TAG, "Could not parse station data");
			}
			//add station with full info to array
			stationsArray[i] = station;
		} //end for loop
		
	} //end JSONParser method
//---------------------------------------------End Private Method-------------------------------------------------------------------------------------------------
	
} //end class