package com.example.museum3;
import java.io.IOException;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.museum3.DatabaseHelper;
import com.example.museum3.R;
import com.example.museum3.Activitymain.MapOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;


public class Map extends MapActivity {

	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	String  lat,longi,text;
	MapView mapView; 
	MapController mc;
    GeoPoint p;
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.map);
		 
	
		 
		 ImageView Back=(ImageView)findViewById(R.id.Back);
	     Back .setOnClickListener(new View.OnClickListener() 
			{ 
			public void onClick(View v) 
			{ 
				
				finish();		

			} 

			}); 
	      
	      
		
		  TextView Lat=new TextView(this);
		  TextView Lon=new TextView(this);
		  
		    mapView = (MapView) findViewById(R.id.mapView);
		    
		    context=this;
			 startSQL(); 
   
		    
		    
	        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);
	        
	         SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
	         int longBreak   = app_preferences.getInt("key", 0);
		  
	         
	         
	         db = (new DatabaseHelper(this)).getWritableDatabase();
			    
			    Cursor  cur=db.rawQuery("Select * from museum WHERE language_id='"+ longBreak+"'" ,null);
			    
			    cur.moveToFirst();
			    
			    
			    Lat.setText(cur.getString(12));
				 Lon.setText(cur.getString(13));
				 
				   String h=Lat.getText().toString();
				   String b=Lon.getText().toString();
				   
				   
				   

				    View zoomView = mapView.getZoomControls(); 
					 
			        zoomLayout.addView(zoomView, 
			            new LinearLayout.LayoutParams(
			                LayoutParams.WRAP_CONTENT, 
			                LayoutParams.WRAP_CONTENT)); 
			        mapView.displayZoomControls(true);
			 
			        mc = mapView.getController();
			        String coordinates[] = {h,b};
			        double lat = Double.parseDouble(coordinates[0]);
			        double lng = Double.parseDouble(coordinates[1]);
			 
			        p = new GeoPoint(
			            (int) (lat * 1E6), 
			            (int) (lng * 1E6));
			 
			        mc.animateTo(p);
			        mc.setZoom(10); 
			        
			         MapOverlay mapOverlay = new MapOverlay();
			         List<Overlay> listOfOverlays = mapView.getOverlays();
			         listOfOverlays.clear();
			         listOfOverlays.add(mapOverlay);    

			         mapView.invalidate();
			         mapView.invalidate();
	        
		     
		     
		   
			 db.close();
			 
			 
			 
	}
    
	public class MapOverlay extends com.google.android.maps.Overlay
	{

	    @Override
	    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
	    {
	       super.draw(canvas, mapView, shadow);       


	           //---translate the GeoPoint to screen pixels---
	               Point screenPts = new Point();
	               mapView.getProjection().toPixels(p, screenPts);

	              //---add the marker---
	              Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.marker);            
	              canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);     


	              return true;



	    }
	}
	
	
	
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	
	private void startSQL() {
        myDbHelper = new DatabaseHelper(context);
        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error(ioe);

        }

        open();
    }
    public void open()throws SQLException{
        myDbHelper.openDataBase();
    }
	
}
