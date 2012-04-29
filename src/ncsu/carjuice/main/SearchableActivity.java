package ncsu.carjuice.main;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SearchableActivity extends Activity{
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    /*
	     * Don't need to use this view if we're going straight to another ListView
	     * Should keep for search logic and delegate display to another Activity?
	     * setContentView(R.layout.search);
		*/
	    
	    // Get the intent, verify the action and get the query
	    Intent intent = getIntent();
	    
	    //Made a new intent to go to the ListView, need to do something about handling back button. #ghetto
	    Intent launchActivityIntent = new Intent(this, ResultsListActivity.class);
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      //doMySearch(query);
	      startActivity(launchActivityIntent);
	    }
	}

	
	
	//Do something with the search query. This block is not reached because StartActivity is being called
	public void doMySearch(String query) {
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(query);
		
		setContentView(textView);
		
	}
	
	
	
}
