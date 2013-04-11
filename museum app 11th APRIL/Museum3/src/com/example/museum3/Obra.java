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
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class Obra  extends Activity {
	
	String text;
	int row_count, j;
	SQLiteDatabase db;
	Context context1;
	DatabaseHelper  myDbHelper1;
	int ExhibitionId;


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.obra);
		
		 ExhibitionId  =getIntent().getExtras().getInt("ExhibitionId");
		   Typeface tf=Typeface.createFromAsset(getAssets(), "fonts/font1.otf");
		  // Typeface tf2=Typeface.createFromAsset(getAssets(), "fonts/font2.otf");
		 
		 //to get language id 
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
			 final    Cursor cur1=db.rawQuery("SELECT internal_image,external_image,ordem FROM works WHERE language_id='"+ longBreak+"' AND idexhibitions='"+ ExhibitionId+"'"  ,null );
			 row_count=cur1.getCount();
			    cur1.moveToFirst();
			    
			 
			 
			    TableLayout	Table=(TableLayout)findViewById(R.id.Table);
			    TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
			    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT,1.0f);
			    TableRow.LayoutParams tableRowParams1=new TableRow.LayoutParams
			    (TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT,1.0f);
			    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
				RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
			    
			    for(int i=0;i<row_count;i++)
				    
			    {
			    	TableRow TR= new TableRow(this);
			    	TR.setLayoutParams(tableRowParams);
			    	Table.addView(TR);
			    	
			    	RelativeLayout rL=new RelativeLayout(this);
			    	rL.setLayoutParams(tableRowParams);
			    	rL.setMinimumHeight(241);
			    	rL.setMinimumWidth(241);
			    	
			    	TR.addView(rL);
			    	
			    	final ImageView image1=new ImageView(this);
			    	try
				    {
			    		final InputStream is=getAssets().open(cur1.getString(0));
				    	Drawable d1=Drawable.createFromStream(is, null);
				    	image1.setImageDrawable(d1);
				    }
			    	catch(IOException e)
				    {
				    	e.printStackTrace();
				    }
			    	
			    	rL.addView(image1);
			    	image1.setMinimumHeight(241);
			    	image1.setMinimumWidth(241);
			    	image1.setLayoutParams(lp1);
			    	image1.setScaleType(ScaleType.FIT_XY);
			    	
			    	final String	   a= cur1.getString(2);
					
						    image1 .setOnClickListener(new View.OnClickListener() 
							{ 
							public void onClick(View v) 
							{ 
								//int ordem=Integer.valueOf(a);
								Intent myIntent = new Intent(Obra.this,Obras_description.class);
							
								myIntent.putExtra("Ordem",a);
						
								
								Obra.this.startActivity(myIntent);
							
							} 
			
							}); 
						    
						    lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
						    
						    TextView Name=new TextView(this);
					        Name.setText(cur1.getString(1));
					        
					        rL.addView(Name);
					        Name.setLayoutParams(lp);
					        Name.setTextColor(0xffffffff);
					        
					        Name.setTextSize(15);
					        Name.setHeight(64);
					        Name.setPadding(14,14,14,14);
					        Name.setBackgroundColor(0xff000000);
					        Name.setAlpha((float) 0.7);
					        Name.setTypeface(tf);
					        
					        if( cur1.moveToNext()|| row_count%2==0)
					        {	
						    	RelativeLayout rL1=new RelativeLayout(this);
						    	rL1.setLayoutParams(tableRowParams);
//						    	rL1.setMinimumHeight(241);
//						    	rL1.setMinimumWidth(241);
						    	TR.addView(rL1);
						    	
						    	final ImageView image2=new ImageView(this);
						    	try
							    {
						    		final InputStream is1=getAssets().open(cur1.getString(0));
							    	Drawable d2=Drawable.createFromStream(is1, null);
							    	image2.setImageDrawable(d2);
							    }
						    	catch(IOException e)
							    {
							    	e.printStackTrace();
							    }
						    	
						    	rL1.addView(image2);
						    	image2.setMinimumHeight(241);
						    	image2.setMinimumWidth(241);
						    	image2.setLayoutParams(lp1);
						    	image2.setScaleType(ScaleType.FIT_XY);
						    	
						    	final String	   b= cur1.getString(2);
								
									    image2 .setOnClickListener(new View.OnClickListener() 
										{ 
										public void onClick(View v) 
										{ 
											//int ordem=Integer.valueOf(a);
											Intent myIntent = new Intent(Obra.this,Obras_description.class);
										
											myIntent.putExtra("Ordem",b);
									
											
											Obra.this.startActivity(myIntent);
										
										} 
						
										}); 
									    
//									    lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
									    
									    TextView Name2=new TextView(this);
								        Name2.setText(cur1.getString(1));
								        
								        rL1.addView(Name2);
								        Name2.setLayoutParams(lp);
								        Name2.setTextColor(0xffffffff);
								        Name2.setTextSize(15);
								        Name2.setHeight(64);
								        Name.setPadding(14,14,14,14);
								        Name2.setBackgroundColor(0xff000000);
								        Name2.setAlpha((float) 0.7);
								        Name2.setTypeface(tf);
								        
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