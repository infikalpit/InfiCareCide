package com.example.museum3;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ImageView.ScaleType;
import android.widget.VideoView;

public class Museum_Videos extends Activity {
	
	String text,a;
	int row_count;
	SQLiteDatabase db;
	Context context1;
	DatabaseHelper  myDbHelper1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.museum_video);
		
		  SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
	         int longBreak   = app_preferences.getInt("key", 0);
	         
	         ImageView Back=(ImageView)findViewById(R.id.Back);
		     Back .setOnClickListener(new View.OnClickListener() 
				{ 
				public void onClick(View v) 
				{ 
					
					finish();		

				} 

				}); 
		     context1=this;
			 startSQL();
			 
			 db = (new DatabaseHelper(this)).getWritableDatabase();
			 final    Cursor cur1=db.rawQuery("SELECT external_Videoimage FROM museum_Videos WHERE language_id='"+ longBreak+"'" ,null );
	
			   row_count=cur1.getCount();
			    cur1.moveToFirst();
				
			    
			 
			    
			    
			    TableLayout	Table=(TableLayout)findViewById(R.id.Table);
			    TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
			    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f);
			    
			    for(int i=0;i<row_count;i++)
				    
			    {

		        //creating  Row
			        TableRow TR= new TableRow(this);
			        TR.setLayoutParams(tableRowParams);
			        Table.addView(TR);
			        
			        ImageView image1=new ImageView(this);
			        TR.addView(image1, new TableRow.LayoutParams (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
				    try
				    {
				    	InputStream is=getAssets().open(cur1.getString(0));
				    	Drawable d1=Drawable.createFromStream(is, null);
				    	image1.setImageDrawable(d1);
				    
				    }
				    catch(IOException e)
				    {
				    	e.printStackTrace();
				    }
				    
				    
				    image1.setMinimumHeight(241);
				    image1.setMinimumWidth(241);
				    
				    image1.setScaleType(ScaleType.FIT_XY);
				    
				    image1 .setOnClickListener(new View.OnClickListener() 
					{ 
					public void onClick(View v) 
					{ 
						
						/*Intent myIntent = new Intent(Museum_Videos.this,Museum_InternalVideo.class); 
						
							
						Museum_Videos.this.startActivity(myIntent);*/
					
						 try {
							   splashPlayer();
							  } catch (Exception ex) {
							  
							  }
							 
						
						
						
						
						
					
					} 

					}); 
				    
				    
				   // cur1.moveToNext();
				    
				    if( cur1.moveToNext()|| row_count%2==0)
				    {
			        
			        ImageView image2=new ImageView(this);
			        TR.addView(image2, new TableRow.LayoutParams (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
				
			        try
				    {
				    	InputStream is=getAssets().open(cur1.getString(0));
				    	Drawable d1=Drawable.createFromStream(is, null);
				    	image2.setImageDrawable(d1);
				    }
				    catch(IOException e)
				    {
				    	e.printStackTrace();
				    }
		            image2.setMinimumHeight(241);
				    image2.setMinimumWidth(241);
				    image2.setScaleType(ScaleType.FIT_XY);
				    
				    image2 .setOnClickListener(new View.OnClickListener() 
					{ 
					public void onClick(View v) 
					{ 
				
						/*Intent myIntent = new Intent(Museum_Videos.this,Museum_InternalVideo.class); 
						
						
						Museum_Videos.this.startActivity(myIntent);*/
						 try {
							   splashPlayer();
							  } catch (Exception ex) {
							  
							  }

					} 

					}); 
			    
				  
				 cur1.moveToNext();
				 
			    }
				    
				    else
				    {
				    	
				    	
				    	ImageView hii=new ImageView(this);
				    	TR.addView(hii, new TableRow.LayoutParams (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
					    	
				    	int id=getResources().getIdentifier("com.example.museum3:drawable/logo", null, null);
				    	hii.setImageResource(id);
				    }
			
				    i++;
				 
			    }
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		
	}
	
	private void startSQL() {
        myDbHelper1 = new DatabaseHelper(context1);
        try {

            myDbHelper1.createDataBase();

        } catch (IOException ioe) {

            throw new Error(ioe);

        }

        open();
    }
    public void open()throws SQLException{
        myDbHelper1.openDataBase();
    }
    
    
    public void splashPlayer() {
    	
    	
    /*	try {
			InputStream is=getAssets().open("Museum/Video/video1");
			is.
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    	
    	 VideoView videoHolder = new VideoView(this);
    	  //videoHolder
    	  
    	  setContentView(videoHolder);
    	  Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
    	    + R.raw.video1);
    	  
    	
    	  videoHolder.setVideoURI(video);
    	  videoHolder.setOnClickListener(new OnClickListener() {
    	   public void onCompletion(MediaPlayer mp) {
    	
    	   }

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}

    	  });
    	  videoHolder.start();
    	  videoHolder.setOnClickListener(new OnClickListener() {

    	  /* @Override
    	   public boolean onTouch(View v, MotionEvent event) {
    	    ((VideoView) v).stopPlayback();
    	    jumpMain();
    	    return true;
    	   }*/

		@Override
		public void onClick(View v) {
			 ((VideoView) v).stopPlayback();
	    	  
	    	  
	    	   }
			
		
    	  });
    	 }


    	/* private synchronized void jumpMain() {
    	  Intent intent = new Intent(ActivitySplash.this, ActivityMain.class);
    	  startActivity(intent);
    	  finish();
    	 }
*/
	

}
