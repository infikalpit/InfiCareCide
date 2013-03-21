package com.example.museum1;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public class Museum_Activity  extends TabActivity
{
	
	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	String Tab1,Tab2,Tab3;
	
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.museum_layout);
		 
		 //TabHost Code
		 
		 
		  
		 
		 context=this;
		 startSQL();
		    
		    ImageView TopHeader=(ImageView)findViewById(R.id.topHeader);
		    ImageView Back=(ImageView)findViewById(R.id.Back);
		    ImageView logo=(ImageView)findViewById(R.id.logo);
		    ImageView icon=(ImageView)findViewById(R.id.icon);
		    ImageView tab=(ImageView)findViewById(R.id.tab);
		    ImageView top=(ImageView)findViewById(R.id.top);
		    ImageView icone=(ImageView)findViewById(R.id.icone);
		    ImageView image=(ImageView)findViewById(R.id.image);
		    ImageView video=(ImageView)findViewById(R.id.video);
		    ImageView map=(ImageView)findViewById(R.id.AddressMap);
		    
		    TextView Heading1=(TextView)findViewById(R.id.Heading1);
		    TextView Heading2=(TextView)findViewById(R.id.Heading2);
		    TextView Heading3=(TextView)findViewById(R.id.Heading3);
		    TextView Heading1Desc=(TextView)findViewById(R.id.HeadingDescription1);
		    TextView Heading2Desc=(TextView)findViewById(R.id.HeadingDescription2);
		    TextView Heading3Desc=(TextView)findViewById(R.id.HeadingDescription3);
		    
		    TextView AddressHeading=(TextView)findViewById(R.id.AddressHeading);
		    TextView AddressDes=(TextView)findViewById(R.id.AddressDes);
		 
		//Image Activity Opens
		    
		
		   
		//Video Activity Opens
		
		video.setOnClickListener(new View.OnClickListener() 
		{ 
			public void onClick(View v) 
			{ 

			Intent myIntent = new Intent(Museum_Activity.this,MuseumVideos.class); 
		   
		    
			
			 //  myIntent.putExtra("CategoryID",cat1.getText().toString() );
		
			Museum_Activity.this.startActivity(myIntent); 
			  startSQL();
			  } 
			
			  }); 
		    
		    
		    db = (new DatabaseHelper(this)).getWritableDatabase();
		    
		   //For MuseumHomeImages
		    
	 final   Cursor  cur=db.query("MuseumHome", null, null, null, null,null, null);
	       
	       //For Museum Home Contents
	       
	       Cursor  curContent=db.query("MuseumContent", null, null, null, null,null, null);
	       
	       image.setOnClickListener(new View.OnClickListener() 
			{ 
			public void onClick(View v) 
			{ 

			Intent myIntent = new Intent(Museum_Activity.this,MuseumImages.class); 
		   
		    
			
			  myIntent.putExtra("Cursor",cur.toString() );
		
			 Museum_Activity.this.startActivity(myIntent); 
			  startSQL();
			  } 
			
			  }); 
	      
	       
	       //first image back.png
	       cur.moveToFirst();
	       try {
	    	    InputStream is=getAssets().open(cur.getString(1));
		    	 Drawable d=Drawable.createFromStream(is, null);
		    	 TopHeader.setImageDrawable(d);
	    	   
	          }
	    	
	    	  catch (IOException e)
	    	 {
	    	 
	    	   e.printStackTrace();
	    	 }
	       
	    	  cur.moveToNext();
		       try {
		    	    InputStream is=getAssets().open(cur.getString(1));
			    	 Drawable d=Drawable.createFromStream(is, null);
			    	 Back.setImageDrawable(d);
		    	   
		          }
		    	
		    	  catch (IOException e)
		    	 {
		    	 
		    	   e.printStackTrace();
		    	 }
	    	
	
		    	  cur.moveToNext();
			       try {
			    	    InputStream is=getAssets().open(cur.getString(1));
				    	 Drawable d=Drawable.createFromStream(is, null);
				    	 logo.setImageDrawable(d);
			    	   
			          }
			    	
			    	  catch (IOException e)
			    	 {
			    	 
			    	   e.printStackTrace();
			    	 } 	  

		    
		    
		    
			    	  cur.moveToNext();
				       try {
				    	    InputStream is=getAssets().open(cur.getString(1));
					    	 Drawable d=Drawable.createFromStream(is, null);
					    	 icon.setImageDrawable(d);
				    	   
				          }
				    	
				    	  catch (IOException e)
				    	 {
				    	 
				    	   e.printStackTrace();
				    	 } 	      

				    	  //ffffff
				    	  
				    	  cur.moveToNext();
					       try {
					    	    InputStream is=getAssets().open(cur.getString(1));
						    	 Drawable d=Drawable.createFromStream(is, null);
						    	 tab.setImageDrawable(d);
					    	   
					          }
					    	
					    	  catch (IOException e)
					    	 {
					    	 
					    	   e.printStackTrace();
					    	 } 	      

				    	  cur.moveToNext();
					       try {
					    	    InputStream is=getAssets().open(cur.getString(1));
						    	 Drawable d=Drawable.createFromStream(is, null);
						    	 top.setImageDrawable(d);
					    	   
					          }
					    	
					    	  catch (IOException e)
					    	 {
					    	 
					    	   e.printStackTrace();
					    	 } 	      

		   

					    	  cur.moveToNext();
						       try {
						    	    InputStream is=getAssets().open(cur.getString(1));
							    	 Drawable d=Drawable.createFromStream(is, null);
							    	 icone.setImageDrawable(d);
						    	   
						          }
						    	
						    	  catch (IOException e)
						    	 {
						    	 
						    	   e.printStackTrace();
						    	 } 	      
		
						    	  cur.moveToNext();
							       try {
							    	    InputStream is=getAssets().open(cur.getString(1));
								    	 Drawable d=Drawable.createFromStream(is, null);
								    	 image.setImageDrawable(d);
							    	   
							          }
							    	
							    	  catch (IOException e)
							    	 {
							    	 
							    	   e.printStackTrace();
							    	 } 	      
		    
							    	  

							    	  cur.moveToNext();
								       try {
								    	    InputStream is=getAssets().open(cur.getString(1));
									    	 Drawable d=Drawable.createFromStream(is, null);
									    	 video.setImageDrawable(d);
								    	   
								          }
								    	
								    	  catch (IOException e)
								    	 {
								    	 
								    	   e.printStackTrace();
								    	 } 	      
			    					    
								    	  cur.moveToNext();
									       try {
									    	    InputStream is=getAssets().open(cur.getString(1));
										    	 Drawable d=Drawable.createFromStream(is, null);
										    	 map.setImageDrawable(d);
									    	   
									          }
									    	
									    	  catch (IOException e)
									    	 {
									    	 
									    	   e.printStackTrace();
									    	 } 	      
	    
		 
								    	
				    					    	  
					//For MuseumContent
  
	  curContent.moveToFirst();
	  
	  Heading1.setText(curContent.getString(1));
	  Heading2.setText(curContent.getString(2));
	  Heading3.setText(curContent.getString(3));
	  Heading1Desc.setText(curContent.getString(4));
	  Heading2Desc.setText(curContent.getString(5));
	  Heading3Desc.setText(curContent.getString(6));
	  AddressHeading.setText(curContent.getString(12));
	  AddressDes.setText(curContent.getString(7));
	  
	  TextView tab1 = new TextView(this);
	  TextView tab2 = new TextView(this);
	  TextView tab3 = new TextView(this);
	
	  
	  tab1.setText(curContent.getString(9).toString());
	  tab2.setText(curContent.getString(10).toString());
	  tab3.setText(curContent.getString(11).toString());
	  
	  tab1.setTextColor(Color.WHITE);
	  tab2.setTextColor(Color.WHITE);
	  tab3.setTextColor(Color.WHITE);
	  
	  Resources res = getResources();
      Intent i = new Intent(this,Activitymain.class);
      TabHost mTabHst = getTabHost();
  
      
      
      mTabHst.addTab(mTabHst.newTabSpec("tab_test1").setIndicator(tab1.getText()).setContent(i));
         
          
      mTabHst.addTab(mTabHst.newTabSpec("tab_test2")
      .setIndicator(tab2.getText())
          .setContent(i));
    mTabHst.addTab(mTabHst.newTabSpec("tab_test3")
          .setIndicator(tab3.getText())
          .setContent(i));
      mTabHst.setCurrentTab(0);
   
   mTabHst.getTabWidget().getChildAt(0).getLayoutParams().height = 45; 
   mTabHst.getTabWidget().getChildAt(1).getLayoutParams().height = 45; 
   mTabHst.getTabWidget().getChildAt(2).getLayoutParams().height = 45; 
   
   mTabHst.getTabWidget().getChildAt(0).setBackgroundColor(Color.BLACK);
   mTabHst.getTabWidget().getChildAt(1).setBackgroundColor(Color.BLACK);
   mTabHst.getTabWidget().getChildAt(2).setBackgroundColor(Color.BLACK);
   
   
								    	  
	 							    	  
								    	  
								    	  
								    	  
		
		
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






