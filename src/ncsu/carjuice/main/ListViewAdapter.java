package ncsu.carjuice.main;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> dataArrayList;
    private static LayoutInflater inflater = null; 
    
    public ListViewAdapter(Activity activity1, ArrayList<HashMap<String, String>> arrayList) {
        activity = activity1;
        dataArrayList = arrayList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return dataArrayList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null)
            view = inflater.inflate(R.layout.list_row, null);

        TextView name = (TextView) view.findViewById(R.id.name); // name
        TextView address = (TextView) view.findViewById(R.id.address); // address
        TextView distance = (TextView) view.findViewById(R.id.distance); // distance
        ImageView navigateIcon = (ImageView) view.findViewById(R.id.list_image); // thumb image
        
        HashMap<String, String> stationMap = new HashMap<String, String>();
        stationMap = dataArrayList.get(position);
        
        // Setting all values in list view
        name.setText(stationMap.get(StationsListActivity.KEY_NAME));
        address.setText(stationMap.get(StationsListActivity.KEY_ADDRESS) + "\n" + stationMap.get(StationsListActivity.KEY_CITY)+ ", "+ stationMap.get(StationsListActivity.KEY_STATE)+"  "+ stationMap.get(StationsListActivity.KEY_ZIP));
        distance.setText(stationMap.get(StationsListActivity.KEY_DISTANCE).substring(0, 4) + "  Miles");
        
		
        
        //imageLoader.DisplayImage(stationMap.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return view;
    }
    public void navigate(View view){
		
	}
}