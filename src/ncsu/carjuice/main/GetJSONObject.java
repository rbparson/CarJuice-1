/**
 * package ncsu.carjuice.main
 */
package ncsu.carjuice.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * @author Jason Brown
 * @version 1.5 5/9/12 1:22am
 */
public class GetJSONObject {
	
	private String baseURL = "http://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?api_key=f46ab87903614bc4dc864b057bc0fb543d197900&fuel_type=ELEC&status=E";       
	private String fullURL;
	private String location ="&location=";				//REQUIRES parameter (Longitude AND Latitude)  or free form distance 
	private String latitude ="&latitude=";
	private String longitude ="&longitude=";
	private String radius ="&radius=";				//Type: decimal  Default: 5.0  The radius (in miles) around the search location to search for stations within
	private String LOG_TAG = "GetJSONObject";
	private JSONObject mainJSONObject;
	
	/**
	 * Constructor
	 * @param latitude
	 * @param longitude
	 */
	public GetJSONObject(String latitude, String longitude, int radius){
		this.latitude += latitude;
		this.longitude += longitude;
		this.radius += radius;
		fullURL = baseURL + this.latitude + this.longitude + this.radius;
		Log.d(LOG_TAG, "Full URL using long and lat " + fullURL + " and latitude=" + this.latitude + " and longitude= " + this.longitude + " and the radius is " + this.radius + " miles");
		getJSONObjectFromURL(fullURL);
	}
	
	/**
	 * Second Constructor - used to get Station info, given a free form string input of location by user
	 * @param Location
	 */
	public GetJSONObject(String location, int radius){
		this.location += location;
		this.radius += radius;
		fullURL = baseURL + this.location + this.radius;
		Log.d(LOG_TAG, "Full URL using location " + fullURL + " and location =" + this.location + " and the radius is " + this.radius + " miles");
		getJSONObjectFromURL(fullURL);
	}
	
	/**
	 * Returns the JSON object retrieved from site
	 * @return JSONObject
	 */
	public JSONObject returnJSONObject(){
		return mainJSONObject;
	}
	
//-----------------private methods-----------------------------------------------------
	/**
	 * Gets the JSON object from the Full URL
	 */
	private void getJSONObjectFromURL(String fullURL){
		//initialize
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;
		StringBuilder stringBuilder = null;
		String result = "";
	
		//make connection and get request
		try{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(fullURL);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		}catch(HttpResponseException e){
			Log.e("LOG_TAG", "http error response "+ e.toString());  //@@@@@@@@@@@@@@need to add pop up dialog saying invalid location, and send the user back to the main screen@@@@@@@@@@@@@@@@@@@@@@@
		}
		catch(Exception e){
			Log.e("LOG_TAG", "Error in http connection "+ e.toString()); 
		}
		//convert the HTTP response to a JSON formatted String
		try{
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"),8);
			stringBuilder = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			inputStream.close();
			bufferedReader.close();
			result = stringBuilder.toString();
		}catch(Exception e){
			Log.e("LOG_TAG", "Error parsing http response "+ e.toString());
		}
		//try parse the string to a JSON object
		try{
			mainJSONObject = new JSONObject(result);
		}catch(JSONException e){
			Log.e("LOG_TAG", "Error parsing JaSON data "+ e.toString());
		}
		
	}//end method
		
} // end class	

