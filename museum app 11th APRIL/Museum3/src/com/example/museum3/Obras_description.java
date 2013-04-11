package com.example.museum3;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Obras_description extends Activity {
	
	String text,a;
	int row_count, j;
	SQLiteDatabase db;
	Context context1;
	DatabaseHelper  myDbHelper1;
	String OrdemId;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.obras_description);
		 
		 
		   Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
		   Typeface tf2=Typeface.createFromAsset(getAssets(), "fonts/font2.otf");
		 
		 ImageView ObraImage=(ImageView)findViewById(R.id.internal_image);
		 TextView ObraName=(TextView) findViewById(R.id.ObraName);
		 TextView ArtistName=(TextView) findViewById(R.id.ArtistName);
		 TextView Size=(TextView) findViewById(R.id.Size);
		 TextView Description=(TextView) findViewById(R.id.Obra_description);
		 
		 ImageView close=(ImageView)findViewById(R.id.imageClose);
		 ImageView Audio=(ImageView)findViewById(R.id.imageSound);
		 ImageView Desc=(ImageView)findViewById(R.id.imageDescription);
		 ImageView Like=(ImageView)findViewById(R.id.imageLike);
	final	 LinearLayout Show=(LinearLayout)findViewById(R.id.DescriptionLayout);
		 
		
		 close.setOnClickListener(new OnClickListener()
		    {
		    public void onClick(View v)
		    {
		    	  finish();
		    }   
		    });
		    
		 
		 Desc.setOnClickListener(new OnClickListener()
		    {
		    public void onClick(View v)
		    {
		    	if(Show.getVisibility()==View.GONE)
		    	{
		    		Show.setVisibility(View.VISIBLE);
		    	}
		    	else if(Show.getVisibility()==View.VISIBLE)
		    	{
		    		Show.setVisibility(View.GONE);
		    	}
		    	
		    }   
		    });
		    
		
		 
		  SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
	         int longBreak   = app_preferences.getInt("key", 0);
             OrdemId  =getIntent().getExtras().getString("Ordem");
        
        context1=this;
		 startSQL();
		 db = (new DatabaseHelper(this)).getWritableDatabase();
		 final    Cursor cur1=db.rawQuery("SELECT * FROM works WHERE language_id='"+ longBreak+"'AND ordem='"+ OrdemId+"'" ,null );
        
		 cur1.moveToFirst();
		 
		 try
		    {
		    final	InputStream is=getAssets().open(cur1.getString(3));
		    	Drawable d1=Drawable.createFromStream(is, null);
		    	ObraImage.setImageDrawable(d1);
		    	
		    	
		    
		    }
		    catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
		 
		 
		 
		 
		 ObraName.setText(cur1.getString(4));
		 ArtistName.setText(cur1.getString(4));
		 Size.setText(cur1.getString(6));
		 Description.setText(cur1.getString(5));
		 ObraName.setTypeface(tf);
		 ArtistName.setTypeface(tf2);
		 Size.setTypeface(tf2);
		 Description.setTypeface(tf2);
		 
		 
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
