package com.example.museum3;



import java.io.IOException;
import java.io.InputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Exhibition_Description extends Activity {
	
	SQLiteDatabase db;
	Context context;
	DatabaseHelper  myDbHelper;
	int longBreak;
	String id;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
        
		
		
		
		
		 SharedPreferences app_preferences = getSharedPreferences("app_preferences", MODE_WORLD_READABLE);
          longBreak   = app_preferences.getInt("key", 0);
      	id =getIntent().getExtras().getString("selected");
		
		
		
			setContentView(R.layout.exhibition_description1);
		
		
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
		 
		 Cursor  cur1=db.rawQuery("SELECT * FROM exhibitions WHERE idexhibitions='"+ id+"'", null);
		 TableLayout	Table=(TableLayout)findViewById(R.id.tableLayout);
		   TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
		   	    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT, 1.0f);
		   RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		   RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);


		 //TypeFace for setting the text's font
		   Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
		   Typeface tf2=Typeface.createFromAsset(getAssets(), "fonts/font2.otf");
		// passing the Cursor's cur1 object to the first row
		   cur1.moveToFirst();
		   

		   
		   TableRow TR1= new TableRow(this);
		   	TR1.setBackgroundColor(0xff57bb5f);
		       TR1.setLayoutParams(tableRowParams);
		       Table.addView(TR1);
		       
		       
		       ImageView exbImg=new ImageView(this);
		       if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
		    			Configuration.SCREENLAYOUT_SIZE_NORMAL)
		        {
		    	   TR1.addView(exbImg,241,241);		        }
		        
		        else if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
			    			Configuration.SCREENLAYOUT_SIZE_SMALL)
		        {
		        	TR1.addView(exbImg,90,90);		        }
		        
		        else if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
			    			Configuration.SCREENLAYOUT_SIZE_LARGE)
		        {
		        	TR1.addView(exbImg,241,241);		        }
		        
		        else if((getResources().getConfiguration().screenLayout &
		    			Configuration.SCREENLAYOUT_SIZE_MASK) ==
			    			Configuration.SCREENLAYOUT_SIZE_XLARGE)
		        {
		        	TR1.addView(exbImg,241,241);		        }
		       
//		 	    TR1.addView(exbImg,241,241);
		 	    
		 	 //Setting the image from the database
			      try {
			   	    InputStream is1=getAssets().open(cur1.getString(3));
				    	 Drawable d1=Drawable.createFromStream(is1, null);
				    	 exbImg.setImageDrawable(d1);
			   	   
			         }
			   	
			   	  catch (IOException e)
			   	 {
			   		  e.printStackTrace();
			   	 }
			       exbImg.setScaleType(ScaleType.FIT_XY);
			   	
			     //Relative Layout for displaying the Name, ObrasIcon, SoundIcon
			       RelativeLayout rL= new RelativeLayout(this);
			       rL.setId(1);
			       rL.setLayoutParams(tableRowParams);
			       rL.setPadding(20,20,20,20);
			       TR1.addView(rL);
			       

			       
			       TextView exbName=new TextView(this);			       
			       rL.addView(exbName,200,150);
			       exbName.setTextSize(12);
			       exbName.setId(2);
			       exbName.setTypeface(tf);
			       exbName.setText(cur1.getString(1));
			       exbName.setTextColor(Color.BLACK);
			       exbName.setTextColor(0xff123214);

			       
			       lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);			       
			       ImageView obrasIcon= new ImageView(this);
			       obrasIcon.setId(3);
			       rL.addView(obrasIcon);
			       int id;
			       id=getResources().getIdentifier("com.example.museum3:drawable/obras_icon", null, null);
			       obrasIcon.setImageResource(id);
			       obrasIcon.setLayoutParams(lp);
//			       obrasIcon.setPadding(0, 0, 19, 0);
			       obrasIcon.setBackgroundColor(0xee2b5d2f);
			       
			       final String idExh=cur1.getString(7);
			       
			       obrasIcon.setOnClickListener(new OnClickListener()
				    {
				    public void onClick(View v)
				    {
				    	int id=Integer.valueOf(idExh);
				    	  Intent intent = new Intent(Exhibition_Description.this, Obra.class);
				    	  intent.putExtra("ExhibitionId",id );
		                  startActivity(intent);
				    }   
				    });
			       
			       
			       
			       lp1.addRule(RelativeLayout.END_OF,obrasIcon.getId());
			       lp1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			       lp1.setMargins(19, 0, 0, 0);
			       
			       ImageView soundIcon=new ImageView(this);
			       soundIcon.setId(4);
			       rL.addView(soundIcon);
			       int id1;
			       id1=getResources().getIdentifier("com.example.museum3:drawable/sound_icon", null, null);
			       soundIcon.setImageResource(id1);
			       soundIcon.setLayoutParams(lp1);
			       soundIcon.setBackgroundColor(0xee2b5d2f);

			       Cursor cur2= db.rawQuery("SELECT exhibition_heading1, exhibition_heading2, exhibition_heading3 FROM Language WHERE language_id='"+ longBreak+"'", null);
			       
			       cur2.moveToFirst();
			       TextView aExpos=(TextView)findViewById(R.id.Heading1);
			       aExpos.setText(cur2.getString(0));
			       aExpos.setTypeface(tf);
			       
			       TextView artistas=(TextView)findViewById(R.id.heading2);
			       artistas.setText(cur2.getString(1));
			       artistas.setTypeface(tf);
			       
			       TextView info=(TextView)findViewById(R.id.heading3);
			       info.setText(cur2.getString(2));
			       info.setTypeface(tf);
			       
			       TextView aExpos_expand=(TextView)findViewById(R.id.Description);
			       aExpos_expand.setText(cur1.getString(5));
			       aExpos_expand.setTypeface(tf2);
			       
			       TextView artistas_expand=(TextView)findViewById(R.id.Description2);
			       artistas_expand.setText(cur1.getString(9));
			       artistas_expand.setTypeface(tf2);
			       
			       TextView info_expand=(TextView)findViewById(R.id.Description3);
			       info_expand.setText(cur1.getString(6));
			       info_expand.setTypeface(tf2);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_exhibition_description, menu);
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