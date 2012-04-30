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
					dialog.setTitle("Title...");
					
					// set the custom dialog components - text, image and button
					TextView text = (TextView) dialog.findViewById(R.id.text);
					text.setText("Android custom dialog example!");
					
					Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
					
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new OnClickListener() 
					{
						public void onClick(View v) 
						{
							dialog.dismiss();
						}
					});

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
