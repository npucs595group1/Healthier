package group1.cs595.npu.healthier;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import java.util.*;

import group1.cs595.npu.healthier.service.GetDirectionsAsyncTask;

/*
public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mappage);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        LinearLayout layout = (LinearLayout) findViewById(R.id.map_context);
        layout.addView(textView);
    }

}*/


public class MapActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mappage);
        /*SupportMapFragment fragment = new SupportMapFragment();
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.mapcontent, fragment).commit();*/
        setContentView(R.layout.mappage);
    }

    public void handleGetDirectionsResult(ArrayList directionPoints)
    {
        Polyline newPolyline;
        GoogleMap mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.BLUE);
        for(int i = 0 ; i < directionPoints.size() ; i++)
        {
            rectLine.add((LatLng) directionPoints.get(i));
        }
        newPolyline = mMap.addPolyline(rectLine);
    }

    public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

        GetDirectionsAsyncTask asyncTask = new GetDirectionsAsyncTask(this);
        asyncTask.execute(map);
    }
}