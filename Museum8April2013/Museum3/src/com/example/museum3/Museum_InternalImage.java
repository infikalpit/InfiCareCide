package com.example.museum3;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Museum_InternalImage extends Activity  {

	String b;
	SQLiteDatabase db;
	Context context1;
	DatabaseHelper  myDbHelper1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.museum_internalimage);
		 
		 
		 
		 
		 b =getIntent().getExtras().getString("selected");
		 
		 ImageView Internal=(ImageView)findViewById(R.id.imageView1);
		 
		 
	      
	     context1=this;
		 startSQL();
		 
		 db = (new DatabaseHelper(this)).getWritableDatabase();
	    Cursor cur1=db.rawQuery("SELECT internal_image FROM museum_images WHERE language_id='"+ b+"'" ,null );
		
	    cur1.moveToFirst();
	    try
	    {
	    	InputStream is=getAssets().open(cur1.getString(0));
	    	Drawable d1=Drawable.createFromStream(is, null);
	    	Internal.setImageDrawable(d1);
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
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
