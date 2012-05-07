/**
 * 
 */
package ncsu.carjuice.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author Jason Brown
 * @version 1.0
 *
 */
public class GetJaSON {
	//GET http://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.format?parameters
	//final URL format = baseURL + dataType + apiKeyParameter + fuelTypeParameter + status code = E == Open Stations Only
	private String baseURL = "http://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?api_key=f46ab87903614bc4dc864b057bc0fb543d197900&fuel_type=ELEC&status=E";       
	
	
	// location and any other params need "&" before parameter 
	private String location ="&location=";				//REQUIRES parameter (Longitude AND Latitude)  or free form distance 
	private String latitude ="&latitude=";
	private String longitude ="&longitude=";
	private String LOG_TAG = "GetJaSON";
	//using sample JSON String below to test with, had to add escapes (/) before each (") Retrieved from site using URL below with required Parameter Location=27607
	//http://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?api_key=f46ab87903614bc4dc864b057bc0fb543d197900&status=E&limit=2&fuel_type=ELEC&location=27607
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
	
	
	private JSONObject mainJSONObject;
	
	private String FullURL;
	
	/**
	 * Constructor
	 * @param latitude
	 * @param longitude
	 */
	public GetJaSON(String latitude, String longitude){
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	/**
	 * Constructor
	 * @param location
	 */
	public GetJaSON(String location){
		this.location = location;		
	}
	

	public String getURLCurrentLocation(Double latitude, Double longitude){
		this.latitude += latitude;
		this.longitude += longitude;
		
		//getUrlCurrentLocation("34.234", "56.56");
			return "";
		
	}
	
	
	/**
	 * Second Constructor - used to get Station info, given a free form string input of location by user
	 * @param Location
	 */
	public String getURLOtherLocation(String location){
		this.location += location;
		
		return ""; // return full URL-- better yet set it as a global then can use either function above with 2 constructors more flexible, pass answer to function below
	}
	
	/**
	 * Takes the full URL and gets the JSON object from it
	 * @param FullURL
	 * @return 
	 */
	public JSONObject getConnection(String FullURL){
		//initialize
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String result = "";
		JSONObject MainJSONObject = null; //may use global
	
		//make connection and get request
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(FullURL);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		}catch(Exception e){
			Log.e("LOG_TAG", "Error in http connection "+ e.toString());
		}
		//convert the HTTP response to a JSON formatted String
		try{
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			inputStream.close();
			bufferedReader.close();
			result=stringBuilder.toString();
		}catch(Exception e){
			Log.e("LOG_TAG", "Error parsing http response "+ e.toString());
			
		}
		//try parse the string to a JSON object
		try{
			mainJSONObject = new JSONObject(result);
		}catch(JSONException e){
			Log.e("LOG_TAG", "Error parsing JaSON data "+ e.toString());
		}
		return mainJSONObject;
		}//end method
			
	
		//********** send resulting JSON object over for parsing
} // end class	

