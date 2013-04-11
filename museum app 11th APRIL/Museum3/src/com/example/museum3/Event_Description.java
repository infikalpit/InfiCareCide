package com.example.museum3;


import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Event_Description extends Activity {
	
	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	String id;
	int longBreak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		id =getIntent().getExtras().getString("selected");
		 SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
         int longBreak   = app_preferences.getInt("key", 0);
		
         
         
       
         
		
		
			setContentView(R.layout.event_description1);
		
		
		
		  ImageView Back=(ImageView)findViewById(R.id.Back);
		     Back .setOnClickListener(new View.OnClickListener() 
				{ 
				public void onClick(View v) 
				{ 
					
					finish();		
					
			 

				} 

				}); 
	         
		
		
		
		context=this;
		 startSQL(); 
		 db = (new DatabaseHelper(this)).getWritableDatabase();
		 
		 Cursor  cur1=db.rawQuery("SELECT * FROM events WHERE ordem='"+ id+"'", null);	
		   //TableLayout to show the records in the form of Table-Rows
		   TableLayout	Table=(TableLayout)findViewById(R.id.tableLayout);
		   TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
		   	    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT, 1.0f);
		   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		   RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

		 //TypeFace for setting the text's font
		   Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
		   Typeface tf2=Typeface.createFromAsset(getAssets(), "fonts/font2.otf");
		// passing the Cursor object to the first row
		   cur1.moveToFirst();
		 //Loop for showing events in the form of list

		   int clr;
		   clr=0xeedddddd;
		   
		   TableRow TR1= new TableRow(this);
		   	TR1.setBackgroundColor(clr);
		       TR1.setLayoutParams(tableRowParams);
		       Table.addView(TR1);
		       
		       
		       ImageView evImg=new ImageView(this);
		       
		       if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
		    			Configuration.SCREENLAYOUT_SIZE_NORMAL)
		        {
		    	   TR1.addView(evImg,241,241);		        }
		        
		        else if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
			    			Configuration.SCREENLAYOUT_SIZE_SMALL)
		        {
		        	TR1.addView(evImg,120,120);		        }
		        
		        else if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
			    			Configuration.SCREENLAYOUT_SIZE_LARGE)
		        {
		        	TR1.addView(evImg,241,241);		        }
		        
		        else if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
			    			Configuration.SCREENLAYOUT_SIZE_XLARGE)
		        {
		        	TR1.addView(evImg,241,241);		        }
		       
//		 	    TR1.addView(evImg,241,241);
		 	    
		 	//Setting the image from the database
		      try {
		   	    InputStream is1=getAssets().open(cur1.getString(3));
			    	 Drawable d1=Drawable.createFromStream(is1, null);
			    	 evImg.setImageDrawable(d1);
		   	   
		         }
		   	
		   	  catch (IOException e)
		   	 {
		   		  e.printStackTrace();
		   	 }
		       evImg.setScaleType(ScaleType.FIT_XY);
		   	
		     //Relative layout for displaying the Name, Date, Time
		       RelativeLayout rL= new RelativeLayout(this);
//		       rL.setBackgroundColor(Color.BLACK);
		       rL.setId(1);
		       rL.setLayoutParams(tableRowParams);
		       rL.setPadding(20,20,0,20);
		       TR1.addView(rL);
		       

		       
		       TextView evName=new TextView(this);
//		       evName.setBackgroundColor(Color.CYAN);
		       rL.addView(evName,200,150);
		       evName.setTextSize(12);
		       evName.setId(2);
		       evName.setTypeface(tf);
		       evName.setText(cur1.getString(1));
		       evName.setTextColor(0xee3c3c3c);

		       
		       lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		       
		       TextView evTime=new TextView(this);
//		       evTime.setBackgroundColor(Color.BLUE);
		       evTime.setTextSize(8);
		       evTime.setId(3);
		       evTime.setTypeface(tf);
		       rL.addView(evTime);
		       evTime.setTextColor(0xee8c8c8c);
		       evTime.setTextSize(10);
		       evTime.setText("AS "+cur1.getString(9));
		       evTime.setLayoutParams(lp);
		       
		       lp1.addRule(RelativeLayout.ABOVE,evTime.getId());
		       
		       TextView evDate=new TextView(this);
//		       evDate.setBackgroundColor(Color.YELLOW);
		       evDate.setTypeface(tf);
		       evDate.setTextSize(12);
		       evDate.setId(4);
		       rL.addView(evDate);
		       evDate.setTextColor(0xee8c8c8c);
		       evDate.setText(cur1.getString(8));
		       evDate.setLayoutParams(lp1);
		       
		     //Second relative layout for displaying the forward(arrow) image
		       RelativeLayout rL1= new RelativeLayout(this);   
		       rL1.setId(5);
//		       rL1.setBackgroundColor(Color.RED);
		       rL1.setLayoutParams(tableRowParams);
		       TR1.addView(rL1);
		       rL1.setPadding(0, 0, 14, 0);

		       Cursor cur2= db.rawQuery("SELECT event_heading1, event_heading2 FROM Language WHERE language_id='"+ longBreak+"'", null);
		       
		       cur2.moveToFirst();
		       TextView desc=(TextView)findViewById(R.id.Heading1);
		       desc.setText(cur2.getString(0));
		       desc.setTypeface(tf);
		       desc.setTextColor(0xeedddddd);
		       
		       TextView info=(TextView)findViewById(R.id.heading2);
		       info.setText(cur2.getString(1));
		       info.setTypeface(tf);
		       info.setTextColor(0xeedddddd);
		       
		       TextView desc_expand=(TextView)findViewById(R.id.Description);
		       desc_expand.setText(cur1.getString(5));
		       desc_expand.setTypeface(tf2);
		       
		       TextView info_expand=(TextView)findViewById(R.id.Description2);
		       info_expand.setText(cur1.getString(6));
		       info_expand.setTypeface(tf2);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_event_description, menu);
		return true;
	}*/

	
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
