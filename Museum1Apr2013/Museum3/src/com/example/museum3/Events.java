package com.example.museum3;


import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;



public class Events extends Activity 
{

	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.event);
		   
		 
		 
		   context=this;
			 startSQL(); 
			 SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
	         int longBreak   = app_preferences.getInt("key", 0);
		 
			 db = (new DatabaseHelper(this)).getWritableDatabase();
  // Cursor cur1=db.rawQuery("SELECT name,external_thumbnail,date,time FROM events WHERE language_id='"+ longBreak+"'" ,null );

  Cursor  cur1=db.rawQuery("SELECT * FROM events WHERE language_id='"+ longBreak+"'ORDER BY ordem ASC", null);	
   //TableLayout to show the records in the form of Table-Rows
   TableLayout	Table=(TableLayout)findViewById(R.id.tableLayout);
   TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
   	    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT, 1.0f);
   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
   RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
   RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

 //TypeFace for setting the text's font
   Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
   
// passing the Cursor object to the first row
   cur1.moveToFirst();
 //Loop for showing events in the form of list
   int a=cur1.getCount();
   int clr;
   
   for(int i=0;i<a;i++)
   {
   	
   	if(i%2==0)
   	{
   		clr= 0xffffffff;
   	}
   	
   	else
   	{
   		clr= 0xffcccccc;
   	}
   	TableRow TR1= new TableRow(this);
   	TR1.setBackgroundColor(clr);
       TR1.setLayoutParams(tableRowParams);
       Table.addView(TR1);
       
       
       ImageView evImg=new ImageView(this);
 	    TR1.addView(evImg,241,241);
 	    
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
   	
     //First relative layout for displaying the Name, Date, Time
       RelativeLayout rL= new RelativeLayout(this);
//       rL.setBackgroundColor(Color.BLACK);
       rL.setId(1);
       rL.setLayoutParams(tableRowParams);
       rL.setPadding(20,20,0,20);
       TR1.addView(rL);
       

       
       TextView evName=new TextView(this);
//       evName.setBackgroundColor(Color.CYAN);
       rL.addView(evName,200,150);
       evName.setTextSize(12);
       evName.setId(2);
       evName.setTypeface(tf);
       evName.setText(cur1.getString(1));
       evName.setTextColor(Color.BLACK);

       
       lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
       
       TextView evTime=new TextView(this);
//       evTime.setBackgroundColor(Color.BLUE);
       evTime.setTextSize(8);
       evTime.setId(3);
       evTime.setTypeface(tf);
       rL.addView(evTime);
       evTime.setTextColor(Color.GRAY);
       evTime.setTextSize(10);
       evTime.setText("AS "+cur1.getString(9));
       evTime.setLayoutParams(lp);
       
       lp1.addRule(RelativeLayout.ABOVE,evTime.getId());
       
       TextView evDate=new TextView(this);
//       evDate.setBackgroundColor(Color.YELLOW);
       evDate.setTypeface(tf);
       evDate.setTextSize(12);
       evDate.setId(4);
       rL.addView(evDate);
       evDate.setTextColor(Color.GRAY);
       evDate.setText(cur1.getString(8));
       evDate.setLayoutParams(lp1);
       
     //Second relative layout for displaying the forward(arrow) image
       RelativeLayout rL1= new RelativeLayout(this);   
       rL1.setId(5);
//       rL1.setBackgroundColor(Color.RED);
       rL1.setLayoutParams(tableRowParams);
       TR1.addView(rL1);
       rL1.setPadding(0, 0, 14, 0);
       
       ImageView arrow= new ImageView(this);
       rL1.addView(arrow);
       int id=getResources().getIdentifier("com.example.museum3:drawable/arrw", null, null);
       arrow.setImageResource(id);
       lp2.addRule(RelativeLayout.CENTER_VERTICAL);
       arrow.setLayoutParams(lp2);
       

     cur1.moveToNext();   // passing the Cursor object to the next row  
  
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

	
	
	
	
	
	
	
}
	
	