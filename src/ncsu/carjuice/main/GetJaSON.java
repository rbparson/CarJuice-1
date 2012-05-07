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
	
	//private JSONObject mainJSONObject;
	
	private String fullURL;
	
	
	public GetJaSON(){
		
		fullURL = baseURL + location + "27607";
		Log.d(LOG_TAG,fullURL );
		JSONObject mainJSONObject = getJSON(fullURL);
		ParseJaSON parseJSON = new ParseJaSON(mainJSONObject);
		StationInfo[] stationArray = parseJSON.getStationArray();
		Log.d(LOG_TAG, "JSON should be parsed and station array filled");
		Log.d(LOG_TAG, "Stations Array is lenght " + stationArray.length);
	}
	/**
	 * Constructor
	 * @param latitude
	 * @param longitude
	 */
	public GetJaSON(String latitude, String longitude){
		this.latitude += latitude;
		this.longitude += longitude;
		
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
	public JSONObject getJSON(String FullURL){
		//initialize
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		String result = "";
		JSONObject mainJSONObject = null; //may use global
	
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

