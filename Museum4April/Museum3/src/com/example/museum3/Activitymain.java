package com.example.museum3;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.maps.*;



 
import java.util.List;



public class Activitymain extends MapActivity 
{
	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	String  lat,longi,text;
	MapView mapView; 
	MapController mc;
    GeoPoint p;
  
    /*ProgressBar pd;
    ProgressDialog ps;*/
    
    private ProgressDialog progressDialog;
   
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activitymain);
		 
	
	      
	      
		
		 
		  
		    mapView = (MapView) findViewById(R.id.mapView);
		    
		    
		    TextView Link=(TextView)findViewById(R.id.link);
		    
		    Link.setOnClickListener(new OnClickListener()
		    {
		    public void onClick(View v)
		    {
		    	  Intent intent = new Intent(Activitymain.this, Map.class);
                  startActivity(intent);
		    }   
		    });
		    
		    
		                    

		    
		    
		    
	       // LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);
	        
	         SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
	         int longBreak   = app_preferences.getInt("key", 0);
		  
		  
	        
		     
		     
		     context=this;
			 startSQL(); 
			 
			 ImageView Image=(ImageView)findViewById(R.id.image);
		        Image .setOnClickListener(new View.OnClickListener() 
				{ 
				public void onClick(View v) 
				{ 
				
					
					 new AsyncTask<Integer, Integer, Boolean>()
				        {
				            ProgressDialog progressDialog;
				            
				            

				            @Override
				            protected void onPreExecute()
				            {
				                /*
				                 * This is executed on UI thread before doInBackground(). It is
				                 * the perfect place to show the progress dialog.
				                 */
				                progressDialog = ProgressDialog.show(Activitymain.this, "",
				                        "Loading...");
				            }

				            @Override
				            protected Boolean doInBackground(Integer... params)
				            {
				                if (params == null)
				                {
				                    return false;
				                }
				                try
				                {
				                   
				                    Thread.sleep(params[0]);
				                    progressDialog.dismiss();
					                Intent openMainList = new Intent(Activitymain.this, Museum_Images.class);
				                       startActivity(openMainList);
				                  
				                }
				                catch (Exception e)
				                {
				                    Log.e("tag", e.getMessage());
				                    
				                    return false;
				                }

				                
				                return true;
				            }

				            @Override
				            protected void onPostExecute(Boolean result)
				            {
				                progressDialog.dismiss();
				                /*
				                 * Update here your view objects with content from download. It
				                 * is save to dismiss dialogs, update views, etc., since we are
				                 * working on UI thread.
				                 */
				               
				            }
				        }.execute(2000);

				        new Thread()
				        {
				            @Override
				            public void run()
				            {

				                
				              
				            }
				        }.start();
				    }
				});
					
					
					
					
					  /* pd=(ProgressBar)findViewById(R.id.ProgressBar01);
			           
			           
			           pd.setVisibility(View.VISIBLE);
			           
			           Thread timer = new Thread(){
			               public void run(){
			                   try{
			                       sleep(3000);
			                       while(progressBarStatus < 3000){
			                           pd.setProgress(progressBarStatus);
			                           progressBarStatus += 1000;
			                          
			                       }
			                   }catch(InterruptedException e){
			                       e.printStackTrace();
			                   }finally
			                   {
			                	   Intent openMainList = new Intent(Activitymain.this, Museum_Images.class);
			                       startActivity(openMainList);
			                    
			                   }
			               }  
			           };
			           timer.start();*/
			         
			           
				
				
				
				
			
		        
		   
			           
			           
			          /* new AsyncTask<Void,Void,Void>()
			           
			           {

			        	

			         	     @Override
			         	     protected Void doInBackground(final Void... params){
			        	    	 
			         	    	 Intent myIntent = new Intent(Activitymain.this,Museum_Images.class); 
			      				 Activitymain.this.startActivity(myIntent);  
			        	          return null;
			        	     }


			        	     @Override
			        	     protected void onPostExecute(final Void result){
			        	    	
			         	         
			        	      
			        	          pd.setVisibility(View.GONE);
			         	     }
			        	 }.execute();
				  }
				
				 });*/
		 
	   	    
		 
		
		 
     	 
		 
		
	       
			
				

       
			
	               

				
	    
			
	        
	        ImageView Video=(ImageView)findViewById(R.id.video);
	        Video .setOnClickListener(new View.OnClickListener() 
			{ 
			public void onClick(View v) 
			{ 
				/* ps = new ProgressDialog(this,R.style.CustomDialog);
				 ps.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				 final ProgressDialog pd = ProgressDialog.show(Activitymain.this, 
                         "", "Loading Video Gallery..", true);
				pd.getWindow ().setBackgroundDrawableResource (android.R.color.transparent);

				 ps = new ProgressDialog(this,R.style.NewDialog);
				 ps.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				
            new Thread(new Runnable()
            {
            	
             public void run()
             {
            	
            	 Intent myIntent = new Intent(Activitymain.this,Museum_Images.class); 
 				Activitymain.this.startActivity(myIntent);             
              ps.dismiss();
                }
           }).start();  */

          }
			
			
			}); 
			
	      

	        
	        
		 
		    
		     TextView Description1=(TextView)findViewById(R.id.Description);
			 TextView Description2=(TextView)findViewById(R.id.Description2);
			 TextView Description3=(TextView)findViewById(R.id.Description3);
			 
			  TextView Heading1=(TextView)findViewById(R.id.Heading1);
			  TextView Heading2=(TextView)findViewById(R.id.heading2);
			  TextView Heading3=(TextView)findViewById(R.id.heading3);
			  TextView Heading4=(TextView)findViewById(R.id.Heading4);
			  
			  TextView address=(TextView)findViewById(R.id.address);
			  TextView district=(TextView)findViewById(R.id.district);
			  TextView city=(TextView)findViewById(R.id.city);
			  TextView state=(TextView)findViewById(R.id.state);
			  TextView zipcode=(TextView)findViewById(R.id.zipcode);
			  TextView phoneno=(TextView)findViewById(R.id.phoneno);
			  
			  
			  
			  TextView Lat=new TextView(this);
			  TextView Lon=new TextView(this);
			  
			 
			  
			
			  
			   db = (new DatabaseHelper(this)).getWritableDatabase();
	          Cursor cur1=db.rawQuery("SELECT Heading1,Heading2,Heading3,Heading4,Image_path,VideoImage_path,Map FROM Language WHERE language_id='"+ longBreak+"'" ,null );
			 
	          cur1.moveToFirst();	
		      Heading1.setText(cur1.getString(0));
			  Heading2.setText(cur1.getString(1));
			  Heading3.setText(cur1.getString(2));
			  Heading4.setText(cur1.getString(3));
			  Link.setText(cur1.getString(6));
			  
			
			  
			 
			    db = (new DatabaseHelper(this)).getWritableDatabase();
			    
			    Cursor  cur=db.rawQuery("Select * from museum WHERE language_id='"+ longBreak+"'" ,null);
			    
			    cur.moveToFirst();
			    
			    
			    Description1.setText(cur.getString(1));
			    Description2.setText(cur.getString(2));
			    Description3.setText(cur.getString(3));
			    address.setText(cur.getString(6));
			    district.setText(cur.getString(7));
			    city.setText(cur.getString(8));
			    state.setText(cur.getString(9));
			    zipcode.setText(cur.getString(5));
			    phoneno.setText(cur.getString(10));
			    
			 
			 Lat.setText(cur.getString(12));
			 Lon.setText(cur.getString(13));
			 
			   String h=Lat.getText().toString();
			   String b=Lon.getText().toString();
			//lat=Lat.toString();
			// longi=Lon.toString();
			 
			 
			 
			    
			 //  View zoomView = mapView.getZoomControls(); 
				 
		      //  zoomLayout.addView(zoomView, 
		        //   new LinearLayout.LayoutParams(
		          //      LayoutParams.WRAP_CONTENT, 
		          //      LayoutParams.WRAP_CONTENT)); 
		      //  mapView.displayZoomControls(true);
		 
		        mc = mapView.getController();
		        String coordinates[] = {h,b};
		        double lat = Double.parseDouble(coordinates[0]);
		        double lng = Double.parseDouble(coordinates[1]);
		 
		        p = new GeoPoint(
		            (int) (lat * 1E6), 
		            (int) (lng * 1E6));
		 
		        mc.animateTo(p);
		        mc.setZoom(17); 
		        
		         MapOverlay mapOverlay = new MapOverlay();
		         List<Overlay> listOfOverlays = mapView.getOverlays();
		         listOfOverlays.clear();
		         listOfOverlays.add(mapOverlay);    

		         mapView.invalidate();
		         mapView.invalidate();
		         
		         
		         
		         
		     /*    Marker marker = mapView.addMarker(new MarkerOptions()
		         .position(new LatLng(37.7750, 122.4183))
		         .title("San Francisco")
		         .snippet("Population: 776733"));*/
		        
		        
		
		         
		         
		      
			    
			    
				
			    Typeface t2 = Typeface.createFromAsset(getAssets(),
			    "fonts/font2.otf");
			    
			    Typeface t1 = Typeface.createFromAsset(getAssets(),
			    "fonts/font1.otf");
			    
			    Description1.setTypeface(t2);
			    Description2.setTypeface(t2);
			    Description3.setTypeface(t2);
			    
			    address.setTypeface(t2);
			    district.setTypeface(t2);
			    city.setTypeface(t2);
			    state.setTypeface(t2);
			    zipcode.setTypeface(t2);
			    phoneno.setTypeface(t2);
			    
			    
			 
			    
			    
			    
		 
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

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
/*	protected void onPause(){
       super.onPause();
        pd.setVisibility(View.INVISIBLE);
       
        
   }*/
	
	
	
}


	
	

	 
	 
	


 


