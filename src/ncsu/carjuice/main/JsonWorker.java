/**
 * 
 */
package ncsu.carjuice.main;

/**
 * @author Jason Brown
 * @version 1.0
 * Gets JSON data from URL and parses it into stations
 */
public class JsonWorker {

	//***I found that Xml and Json files are compressed by gzip, but not CSV files.**
	
	//GET http://developer.nrel.gov/api/alt-fuel-stations/v1.format?parameters
	//final url format = baseURL + apiKeyParameter + fuelTypeParameter + Location(either long and lat or user input)
	// location and any other params need & before 
	private String baseURL = "http://developer.nrel.gov/api/alt-fuel-stations/v1.json?";       // returns json formatted data
	
	final private String apiKeyParameter = "api_key=f46ab87903614bc4dc864b057bc0fb543d197900"; //required to get info
	
	final private String fuelTypeParameter = "&fuel_type=ELEC";								// all stations returned will be electric charging stations
	
	final private String stationStatus = "&status=E";										//only get open stations
	
	//other string to append to url depending on filters
	private String distance ="&";
	
	
	
	
	
	
// need to get URL, constructed from current user location, OR from user input	
	
public void getUrlMyLcation(String latitude, String longitude){
	
	
	
	
	
	
	
}
	
	
}
