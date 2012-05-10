package ncsu.carjuice.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;  // TODO Text number entry with check if i can't get spinner to work right
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.content.SharedPreferences;

public class SettingsActivity extends Activity {
	
	//used to store/handle checkbox states
	private CheckBox 	stage1, 
						stage2, 
						dcfast;  

    /** Called when the activity is first created. */
   	
    @Override
    //@param savedInstanceState
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        updateCheckBoxes();
        addListenerOnStage1();
        addListenerOnStage2();
        addListenerOnDCFast();
    }
    
    
    //Called initially to set the checkboxes to the saved values in preferences
    public void updateCheckBoxes(){
    	stage1 = (CheckBox) findViewById(R.id.stage1);
    	stage2 = (CheckBox) findViewById(R.id.stage2);
    	dcfast = (CheckBox) findViewById(R.id.dcfast);
    
    	SharedPreferences appSettings = getSharedPreferences("AppSettings", MODE_PRIVATE);
    	
    	boolean storedStage1 = true;
    	boolean storedStage2 = true;
    	boolean storedDCFast = true;
    	int storedDistance = 20;
    	
    	boolean sometest = false;
    	appSettings.getBoolean("sometest", sometest);
    	appSettings.getBoolean("stage1", storedStage1);
    	appSettings.getBoolean("stage2", storedStage2);
    	appSettings.getBoolean("dcfast", storedDCFast);
    	//TODO Something is wonky here. Value doesn't seem to be overwritten
    	
    	//test to make sure something doesn't exist stays the same (so first launch will have all options enabled)
    	if (sometest == false)
    	{
			Toast.makeText(SettingsActivity.this,
	    		 	   "No data = false", Toast.LENGTH_LONG).show();
    	}
    	
    	if (storedStage1){
			Toast.makeText(SettingsActivity.this,
	    		 	   "Good it was false", Toast.LENGTH_LONG).show();
    	}
    	
    	//setting the check boxes
    	//stage1.setChecked(storedStage1);
    	stage2.setChecked(storedStage2);
    	dcfast.setChecked(storedDCFast);
    			
		//TODO FIX DISTANCE SPINNER SETTING/GRABBING STUFF
    	
    }//of updateCheckBoxes
    
    public void commitPreferences(){
    	
    	stage1 = (CheckBox) findViewById(R.id.stage1);
    	stage2 = (CheckBox) findViewById(R.id.stage2);
    	dcfast = (CheckBox) findViewById(R.id.dcfast);
    	
    	SharedPreferences appSettings = getSharedPreferences("AppSettings", MODE_PRIVATE);  
    	SharedPreferences.Editor prefEditor = appSettings.edit();
    	
    	if (stage1.isChecked())	{
			prefEditor.putBoolean("stage1", true);
		}
    	else {
    		prefEditor.putBoolean("stage1", false);
    	}
    	
    	if (stage2.isChecked()) {
			prefEditor.putBoolean("stage2", true);
		}
    	else {
    		prefEditor.putBoolean("stage2", false);
    	}
    	
    	if (dcfast.isChecked())	{
			prefEditor.putBoolean("dcfast", true);
		}
    	else {
    		prefEditor.putBoolean("dcfast", false);
    	}
    		
    	prefEditor.putInt("distance", 20);
    	prefEditor.commit();  
    	
    }//of commitPreferences
    
    
    public void addListenerOnStage1() {
    	 
    	stage1 = (CheckBox) findViewById(R.id.stage1);
    	
    	stage1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				commitPreferences();
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(SettingsActivity.this,
		    		 	   "Stage 1 Chargers Enabled", Toast.LENGTH_LONG).show();
		    	}
		
	    	}
		});
     
      }// of addListenerOnStage1
    
    public void addListenerOnStage2() {

    	stage2 = (CheckBox) findViewById(R.id.stage2);
    	
    	stage2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				commitPreferences();
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(SettingsActivity.this,
		    		 	   "Stage 2 Chargers Enabled", Toast.LENGTH_LONG).show();
		    	}
		
	    	}
		});
     
      }// of addListenerOnStage2
    
    public void addListenerOnDCFast() {
   	 
    	dcfast = (CheckBox) findViewById(R.id.dcfast);
    	
    	dcfast.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				commitPreferences();
				if (((CheckBox) v).isChecked()) {
					Toast.makeText(SettingsActivity.this,
		    		 	   "DC Fast Chargers Enabled", Toast.LENGTH_LONG).show();
		    	}
		
	    	}
		});
     
      }// of addListenerOnDCFast
    
    
    //Should I set up a manual back button? Or make the top logo clickable?
    public void gotoMain(View view) {
    	
        // Do something in response to button
    	Intent intent = new Intent(this, MainActivity.class);	
    	startActivity(intent);
    }    

}