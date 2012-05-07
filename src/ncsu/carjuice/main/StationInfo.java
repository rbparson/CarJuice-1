package ncsu.carjuice.main;


/** 
 * The Station class is used to hold the parsed JSON data for each station
 * @author Jason Brown
 *
 */
public class StationInfo {
	
	private String name;							// The name of the station.
	private String address;							//The street address of the station's location.
	private String intersectionDirections;			//Brief additional information about how to locate the station.
	private String city;							//The city of the station's location.
	private String state;							//The two character U.S. state or Canadian province code of the station's location.
	private String zip;								//The ZIP code (postal code) of the station's location.
	private String phoneNumber;						//The phone number of the station.
	private String groupsWithAccess;				//A description of who is allowed to access the station and other station access information
	private String operationHours;					//Hours of operation for the station.
	private String cardsAccepted;					//A space-separated list of payment methods accepted. Possible payment methods: A, D, M, V, Cash, Checks, CFN, CleanEnergy, FuelMan, GasCard, PHH, Voyager, Wright_Exp 
	private String ownerType;						//A space-separated list of owner codes. 
													/**
													 * P=Privately owned
													 * T=Utility owned
													 * FG=Federal government owned
													 * LG=Local government owned, SG=State government owned.
													 */
	private String chargingNetwork;					//the name of the EVSE network, if applicable.
	private String chargingNetworkWebsite;			//the EVSE network Web site, if applicable.
	private String geocodeStatus;					//A rating indicating the approximate accuracy of the latitude and longitude for the station's address, given as code values as described below:
													/**
													 * GPS	 The location is from a real GPS readout at the station.
													 * 200-9	 Premise (building name, property name, shopping center, etc.) level accuracy
													 * 200-8	 Address level accuracy.
													 * 200-7	 Intersection level accuracy.
													 * 200-6	 Street level accuracy.
													 * 200-5	 ZIP code (postal code) level accuracy.
													 * 200-4	 Town (city, village) level accuracy.
													 * 200-3	 Sub-region (county, municipality, etc.) level accuracy.
													 * 200-2	 Region (state, province,etc.) level accuracy.
													 * 200-1	 Country level accuracy.
													 * 200-0	 Unknown accuracy.
													 */
													
	private String latitude;						//The latitude of the station's address Range: -90 to 90
	private String longitude;						//The longitude of the station's address Range -180 to 180
	private String distance;						//The distance, in miles, from the given location and this station.
	
	private String numLevel1Chargers;				//The number of Level 1 EVSE (standard 110V outlet).
	private String numLevel2Chargers;				//The number of Level 2 EVSE (J1772 connector).
	private String numDcFastChargers;				//The number of DC Fast Chargers
	private String stationId;						//A unique identifier for this specific station.
	
	
	
	//-----------------------------Getters------------------------------------
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @return the intersectionDirections
	 */
	public String getIntersectionDirections() {
		return intersectionDirections;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @return the groupsWithAccess
	 */
	public String getGroupsWithAccess() {
		return groupsWithAccess;
	}
	
	/**
	 * @return the operationHours
	 */
	public String getOperationHours() {
		return operationHours;
	}
	
	/**
	 * @return the cardsAccepted
	 */
	public String getCardsAccepted() {
		return cardsAccepted;
	}
	
	/**
	 * @return the ownerType
	 */
	public String getOwnerType() {
		return ownerType;
	}
	
	/**
	 * @return the chargingNetwork
	 */
	public String getChargingNetwork() {
		return chargingNetwork;
	}
	
	/**
	 * @return the chargingNetworkWebsite
	 */
	public String getChargingNetworkWebsite() {
		return chargingNetworkWebsite;
	}
	
	/**
	 * @return the geocodeStatus
	 */
	public String getGeocodeStatus() {
		return geocodeStatus;
	}
	
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}
	
	/**
	 * @return the numLevel1Chargers
	 */
	public String getNumLevel1Chargers() {
		return numLevel1Chargers;
	}
	
	/**
	 * @return the numLevel2Chargers
	 */
	public String getNumLevel2Chargers() {
		return numLevel2Chargers;
	}
	
	/**
	 * @return the numDcFastChargers
	 */
	public String getNumDcFastChargers() {
		return numDcFastChargers;
	}
	
	/**
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
	}
	
	//---------------------------------Setters-----------------------------------
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @param intersectionDirections the intersectionDirections to set
	 */
	public void setIntersectionDirections(String intersectionDirections) {
		this.intersectionDirections = intersectionDirections;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @param groupsWithAccess the groupsWithAccess to set
	 */
	public void setGroupsWithAccess(String groupsWithAccess) {
		this.groupsWithAccess = groupsWithAccess;
	}
	
	/**
	 * @param operationHours the operationHours to set
	 */
	public void setOperationHours(String operationHours) {
		this.operationHours = operationHours;
	}
	
	/**
	 * @param cardsAccepted the cardsAccepted to set
	 */
	public void setCardsAccepted(String cardsAccepted) {
		this.cardsAccepted = cardsAccepted;
	}
	
	/**
	 * @param ownerType the ownerType to set
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * @param chargingNetwork the chargingNetwork to set
	 */
	public void setChargingNetwork(String chargingNetwork) {
		this.chargingNetwork = chargingNetwork;
	}
	
	/**
	 * @param chargingNetworkWebsite the chargingNetworkWebsite to set
	 */
	public void setChargingNetworkWebsite(String chargingNetworkWebsite) {
		this.chargingNetworkWebsite = chargingNetworkWebsite;
	}
	
	/**
	 * @param geocodeStatus the geocodeStatus to set
	 */
	public void setGeocodeStatus(String geocodeStatus) {
		this.geocodeStatus = geocodeStatus;
	}
	
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	/**
	 * @param numLevel1Chargers the numLevel1Chargers to set
	 */
	public void setNumLevel1Chargers(String numLevel1Chargers) {
		this.numLevel1Chargers = numLevel1Chargers;
	}
	
	/**
	 * @param numLevel2Chargers the numLevel2Chargers to set
	 */
	public void setNumLevel2Chargers(String numLevel2Chargers) {
		this.numLevel2Chargers = numLevel2Chargers;
	}
	
	/**
	 * @param numDcFastChargers the numDcFastChargers to set
	 */
	public void setNumDcFastChargers(String numDcFastChargers) {
		this.numDcFastChargers = numDcFastChargers;
	}
	
	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
}
