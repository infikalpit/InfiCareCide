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
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Museum_Images extends Activity {
	
	String text,a;
	int row_count, j;
	SQLiteDatabase db;
	Context context1;
	DatabaseHelper  myDbHelper1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.museum_images);
		
		 
		 //to get language id 
		  SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
	         int longBreak   = app_preferences.getInt("key", 0);
	         
	      
//	    final   ImageView internal1=new ImageView(this);
//	    final   ImageView internal2=new ImageView(this);
		  
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
	 final    Cursor cur1=db.rawQuery("SELECT external_image,idmuseum_images,internal_image FROM museum_images WHERE language_id='"+ longBreak+"'" ,null );
	
/*final	Cursor cur2=db.rawQuery("SELECT internal_image FROM museum_images WHERE language_id='"+ longBreak+"'" ,null );
cur2.moveToFirst();*/
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
	        
	final  ImageView image1=new ImageView(this);
	        TR.addView(image1, new TableRow.LayoutParams (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f));
		    try
		    {
		    final	InputStream is=getAssets().open(cur1.getString(0));
		    	Drawable d1=Drawable.createFromStream(is, null);
		    	image1.setImageDrawable(d1);
		    	
		    	
		    
		    }
		    catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
		    /*final	    TextView internal=new TextView(this);   
		    internal.setText(cur1.getString(2));*/
		    image1.setMinimumHeight(241);
		    image1.setMinimumWidth(241);
		    
		    image1.setScaleType(ScaleType.FIT_XY);
		    
	
		  final String a=cur1.getString(2);
	
	
		    
	
		    image1 .setOnClickListener(new View.OnClickListener() 
			{ 
			public void onClick(View v) 
			{ 
			  
			
				Intent myIntent = new Intent(Museum_Images.this,Museum_InternalImage.class);
				//myIntent.putExtra("selected",internal.getText().toString());
				myIntent.putExtra("selected",a);
			//	myIntent.putExtra("selected",a);
				
				Museum_Images.this.startActivity(myIntent);
			} 

			}); 
		    //cur1.moveToNext();
		    
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
		    final String b=cur1.getString(2);
		
		       
		    image2 .setOnClickListener(new View.OnClickListener() 
			{ 
			public void onClick(View v) 
			{ 
			
			   
				Intent myIntent = new Intent(Museum_Images.this,Museum_InternalImage.class); 
			//	myIntent.putExtra("selected",a);
				myIntent.putExtra("selected",b);
				
				Museum_Images.this.startActivity(myIntent); 
				
				

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

	
	
}
