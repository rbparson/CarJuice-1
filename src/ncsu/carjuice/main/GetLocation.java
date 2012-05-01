package ncsu.carjuice.main;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * 
 * @author Jason Brown
 * @version 1.2
 */
public class GetLocation {
    
	Timer timer;
    LocationManager locationManager;
    LocationResult locationResult;
    boolean isGpsEnabled=false;
    boolean isNetworkEnabled=false;

    public boolean getLocation(Context context, LocationResult result) {
        //I use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult=result;
        if(locationManager==null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }
        
        //exceptions will be thrown if provider is not permitted.
        try{
        	isGpsEnabled=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch(Exception ex){}
        
        try{
        	isNetworkEnabled=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch(Exception ex){}

        //don't start listeners if no provider is enabled
        if(!isGpsEnabled && !isNetworkEnabled)
            return false;

        if(isGpsEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        }
        
        if(isNetworkEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        }
        
        timer=new Timer();
        timer.schedule(new GetLastLocation(), 20000);
        return true;
    }

    LocationListener locationListenerGps = new LocationListener() {
    	public void onLocationChanged(Location location) {
    		timer.cancel();
    		locationResult.gotLocation(location);
    		locationManager.removeUpdates(this);
    		locationManager.removeUpdates(locationListenerNetwork);
    	}
    	public void onProviderDisabled(String provider) {}
       	public void onProviderEnabled(String provider) {}
       	public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            timer.cancel();
            locationResult.gotLocation(location);
            locationManager.removeUpdates(this);
            locationManager.removeUpdates(locationListenerGps);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    class GetLastLocation extends TimerTask {
        @Override
        public void run() {
             locationManager.removeUpdates(locationListenerGps);
             locationManager.removeUpdates(locationListenerNetwork);

             Location netwworkLocation=null, gpsLocation=null;
             if(isGpsEnabled){
                 gpsLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             }
             
             if(isNetworkEnabled){
                 netwworkLocation=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
             }
             
             //if there are both values use the latest one
             if(gpsLocation!=null && netwworkLocation!=null){
                 if(gpsLocation.getTime()>netwworkLocation.getTime()){
                     locationResult.gotLocation(gpsLocation);
                 }
                 else{
                     locationResult.gotLocation(netwworkLocation);
                 }
                 return;
             }

             if(gpsLocation!=null){
                 locationResult.gotLocation(gpsLocation);
                 return;
             }
             
             if(netwworkLocation!=null){
                 locationResult.gotLocation(netwworkLocation);
                 return;
             }
             locationResult.gotLocation(null);
        }
    }

    public static abstract class LocationResult{
        public abstract void gotLocation(Location location);
    }
}