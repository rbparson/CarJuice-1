package ncsu.carjuice.main;

import java.util.List;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapsActivity extends MapActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        Intent mapIntent = getIntent();
        
        String latitude = mapIntent.getStringExtra(StationsListActivity.PIN_LAT);
    	String longitude = mapIntent.getStringExtra(StationsListActivity.PIN_LONG);
 
        
        MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true); // Displaying Zooming controls
        mapView.setTraffic(true); // Traffic View
        
        MapController mc = mapView.getController();
        double lat = Double.parseDouble(latitude); // latitude
        double lon = Double.parseDouble(longitude); // longitude
        GeoPoint geoPoint = new GeoPoint((int)(lat * 1E6), (int)(lon * 1E6));
        mc.animateTo(geoPoint);
        mc.setZoom(13);
        mapView.invalidate();
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.map_marker);
        AddItemizedOverlay itemizedOverlay =
        new AddItemizedOverlay(drawable, this);
         
        OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello", "Sample Overlay item");
         
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        
        
    }
 
    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }
}