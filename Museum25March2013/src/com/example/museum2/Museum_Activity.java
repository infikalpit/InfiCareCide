package com.example.museum2;

import java.io.IOException;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TabHost;
import android.widget.TextView;


public class Museum_Activity extends TabActivity
{
	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	String languagename;
	

    
	
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.museum_layout);
		 
		  
		 
		 
		 
		  TextView tab1 = new TextView(this);
		  TextView tab2 = new TextView(this);
		  TextView tab3 = new TextView(this);
		  
		  languagename =getIntent().getExtras().getString("selected");
		  
		  
		  
		  
		  
		  
	      
	      context=this;
		  startSQL(); 
			 
			 
		        db = (new DatabaseHelper(this)).getWritableDatabase();
		        Cursor cur1=db.rawQuery("SELECT Tab1,Tab2,Tab3 FROM Language WHERE language='"+ languagename+"'" ,null );
				  cur1.moveToFirst();
				  
				  tab1.setText(cur1.getString(0));
				  tab2.setText(cur1.getString(1));
				  tab3.setText(cur1.getString(2));
				  
				  tab1.setPadding(25, 8, 5, 5);
				  tab2.setPadding(25, 8, 5, 5);
				  tab3.setPadding(25, 8, 5, 5);
				  
				  
				  tab1.setTextColor(Color.WHITE);
				  tab2.setTextColor(Color.WHITE);
				  tab3.setTextColor(Color.WHITE);
				  
				  Resources res = getResources();
			      Intent museum = new Intent(this,Activitymain.class);
			      
			      SharedPreferences app_preferences =  PreferenceManager.getDefaultSharedPreferences(Museum_Activity.this);
				  SharedPreferences.Editor editor = app_preferences.edit();
		         String text =languagename;
			      editor.putString("key", text);
			         editor.commit();
		         Intent myIntent = new Intent(Museum_Activity.this,Activitymain.class);
			      startActivity(myIntent);
			      
			     
			      Intent event = new Intent(this,Events.class);
			      Intent exhibiton = new Intent(this,Exhibition.class);
			      TabHost mTabHst = getTabHost();
			  
			      
			      
			      mTabHst.addTab(mTabHst.newTabSpec("tab_test1").setIndicator(tab1).setContent(museum));
			         
			          
			      mTabHst.addTab(mTabHst.newTabSpec("tab_test2")
			      .setIndicator(tab2)
			          .setContent(event));
			     mTabHst.addTab(mTabHst.newTabSpec("tab_test3")
			          .setIndicator(tab3)
			          .setContent(exhibiton));
			      mTabHst.setCurrentTab(0);
			   
			   mTabHst.getTabWidget().getChildAt(0).getLayoutParams().height = 45; 
			   mTabHst.getTabWidget().getChildAt(1).getLayoutParams().height = 45; 
			   mTabHst.getTabWidget().getChildAt(2).getLayoutParams().height = 45; 
			   
			   mTabHst.getTabWidget().getChildAt(0).setBackgroundColor(Color.BLACK);
			   mTabHst.getTabWidget().getChildAt(1).setBackgroundColor(Color.BLACK);
			   mTabHst.getTabWidget().getChildAt(2).setBackgroundColor(Color.BLACK);
			   
			   Typeface t1 = Typeface.createFromAsset(getAssets(),
			   "fonts/font1.otf");
			   
			   
			  
			   
			  
			   tab1.setTypeface(t1); 
			   tab2.setTypeface(t1); 
			   tab3.setTypeface(t1); 
			   
			   
			   db.close();
			   
			   
			   
			  
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
			   