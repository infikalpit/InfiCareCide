package com.example.museum3;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Exhibition extends Activity  {
	
	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.exhibition);
		
		     
		 context=this;
		 startSQL(); 
		 SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
         int longBreak   = app_preferences.getInt("key", 0);
	 
		 db = (new DatabaseHelper(this)).getWritableDatabase();
		 Cursor  cur=db.rawQuery("SELECT * FROM exhibitions WHERE language_id='"+ longBreak+"' ORDER BY ordem ASC", null);
		    
		    //TableLayout to show the records in the form of Table-Rows
		    TableLayout	Table=(TableLayout)findViewById(R.id.tableLayout);
		    TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
		    	    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f);
		    
		    
	        
	        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
	        
	        //TypeFace for setting the text's font
	        Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
	        
	     // passing the Cursor object to the first row
	        cur.moveToFirst();
		    
	        //Loop for showing exhibitions in the form of list
	        int a=cur.getCount();
		    int clr;
		    
		    for(int i=0;i<a;i++)
		    {
		    	
		    	if(i%2==0)
		    	{
		    		//clr= 0xffffffff;
		    		clr=0xeeeeeeee;
		    	}
		    	
		    	else
		    	{
		    		//clr= 0xffcccccc;
		    		clr=0xdddddddd;
		    	}
		    	TableRow TR1= new TableRow(this);
		    	TR1.setBackgroundColor(clr);
		        TR1.setLayoutParams(tableRowParams);
		        Table.addView(TR1);
		        
		        
		        ImageView exbImg=new ImageView(this);
		  	    TR1.addView(exbImg,241,241);
		  	    
		  	 //Setting the image from the database
		       try {
		    	    InputStream is1=getAssets().open(cur.getString(3));
			    	 Drawable d1=Drawable.createFromStream(is1, null);
			    	 exbImg.setImageDrawable(d1);
		    	   
		          }
		    	
		    	  catch (IOException e)
		    	 {
		    		  e.printStackTrace();
		    	 }
		        exbImg.setScaleType(ScaleType.FIT_XY);
		    	  
		        //First relative layout for displaying the Name
		        RelativeLayout rL= new RelativeLayout(this);
//		        rL.setBackgroundColor(Color.BLACK);
		        rL.setId(1);
		        rL.setLayoutParams(tableRowParams);
		        rL.setPadding(20,20,0,20);
		        TR1.addView(rL);
		        

		        
		        TextView exbName=new TextView(this);
//		        exbName.setBackgroundColor(Color.CYAN);
		        rL.addView(exbName,200,200);
		        exbName.setTextSize(12);
		        exbName.setId(2);
		        exbName.setTypeface(tf);
		        exbName.setText(cur.getString(1));
		        exbName.setTextColor(Color.BLACK);
		        
		        //Second relative layout for displaying the forward(arrow) image
		        RelativeLayout rL1= new RelativeLayout(this);   
		        rL1.setId(5);
//		        rL1.setBackgroundColor(Color.RED);
		        rL1.setLayoutParams(tableRowParams);
		        TR1.addView(rL1);
		        rL1.setPadding(0, 0, 14, 0);
		        
		        ImageView arrow= new ImageView(this);
		        rL1.addView(arrow);
		        int id;
		        if(i%2==0)
		        {
		        	  id=getResources().getIdentifier("com.example.museum3:drawable/arrw", null, null);
		        }
		        else
		        {
		        	  id=getResources().getIdentifier("com.example.museum3:drawable/arrw1", null, null);
		        }
		        arrow.setImageResource(id);
		        lp2.addRule(RelativeLayout.CENTER_VERTICAL);
		        arrow.setLayoutParams(lp2);

		      cur.moveToNext(); 
		    }// passing the Cursor object to the next row

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


