package ncsu.carjuice.main;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsListActivity extends ListActivity {

	
	final Context context = this;	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	  super.onCreate(savedInstanceState);
	 
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, STATIONS));
	  
	  // Get intent and any data it may contain
	  Intent intent = getIntent();

	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);


		  lv.setOnItemClickListener(new OnItemClickListener() 
		  {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) 
		        {
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
							//dialog.dismiss();
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
			      
		    	}
		  });

	}
	
	
	//Array of items to be displayed in list
	static String[] STATIONS = new String[] {
	    "Station 1", "Station 2", "Station 3", "Station 4", "Station 5",
	    "Station 6", "Station 7", "Station 8", "Station 9", "Station 10"
	  };
	
}
