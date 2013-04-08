package com.example.museum3;


import android.app.Activity;
import android.os.Bundle;
import java.io.IOException;
import java.io.InputStream;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;



public class Events extends Activity  {
	
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
		    
		    db = (new DatabaseHelper(this)).getWritableDatabase();
		    
		    Cursor  cur=db.query("events", null, null, null, null,null, null);
		    
		    TableLayout	Table=(TableLayout)findViewById(R.id.tableLayout);
		    TableRow.LayoutParams tableRowParams=new TableRow.LayoutParams
		    	    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT, 1.0f);
		    TableRow.LayoutParams tableRowParams1=new TableRow.LayoutParams
		    (TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
	        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

	        Typeface tf=Typeface.createFromAsset(getAssets(), "Fonts/Heading_Font.otf");
		    cur.moveToFirst();
		    int a=cur.getCount();
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
//		        TR1.setMinimumHeight(20);
		        Table.addView(TR1);
		        
		        
		        ImageView exbImg1=new ImageView(this);
		  	    TR1.addView(exbImg1,241,241);
		  	    
		  	 
		       try {
		    	    InputStream is1=getAssets().open(cur.getString(3));
			    	 Drawable d1=Drawable.createFromStream(is1, null);
			    	 exbImg1.setImageDrawable(d1);
		    	   
		          }
		    	
		    	  catch (IOException e)
		    	 {
		    		  e.printStackTrace();
		    	 }
//		        exbImg1.setScaleType(ScaleType.FIT_XY);
		    	  
		        RelativeLayout rL= new RelativeLayout(this);
//		        rL.setBackgroundColor(Color.BLACK);
//		        rL.setMinimumWidth(200);
		        rL.setId(10);
//		        rL.setBackgroundColor(Color.GREEN);
		        rL.setLayoutParams(tableRowParams);
		        rL.setPadding(20,20,0,20);
		        TR1.addView(rL);
		        

		        
		        TextView exbName1=new TextView(this);
//		        exbName1.setBackgroundColor(Color.CYAN);
		        rL.addView(exbName1);
//		        rL.addView(exbName1, 200, 25);
		        exbName1.setId(1);
		        exbName1.setTypeface(tf);
		        exbName1.setText(cur.getString(1));
		        exbName1.setTextColor(Color.BLACK);

		        
//		        lp.addRule(RelativeLayout.BELOW,exbName1.getId());
		        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		        
		        TextView exbTime1=new TextView(this);
//		        exbTime1.setBackgroundColor(Color.BLUE);
		        exbTime1.setId(4);
		        exbTime1.setTypeface(tf);
//		        rL.addView(exbTime1, 150, 150);
		        rL.addView(exbTime1);
		        exbTime1.setTextColor(Color.GRAY);
		        exbTime1.setTextSize(10);
//		        exbTime1.setPadding(0,0,0,20);
		        exbTime1.setText("AS "+cur.getString(9));
		        exbTime1.setLayoutParams(lp);
		        
		        lp1.addRule(RelativeLayout.ABOVE,exbTime1.getId());
		        
		        TextView exbDate1=new TextView(this);
//		        exbDate1.setBackgroundColor(Color.YELLOW);
		        exbDate1.setTypeface(tf);
		        exbDate1.setId(2);
//		        rL.addView(exbDate1, 150, 150);
		        rL.addView(exbDate1);
		        exbDate1.setTextColor(Color.GRAY);
		        exbDate1.setText(cur.getString(8));
		        exbDate1.setLayoutParams(lp1);
		        
		        
		        RelativeLayout rL1= new RelativeLayout(this);   
		        rL1.setId(12);
//		        rL1.setBackgroundColor(Color.RED);
		        rL1.setLayoutParams(tableRowParams);
		        TR1.addView(rL1);
		        rL1.setPadding(0, 0, 14, 0);
		        
		        ImageView arrow= new ImageView(this);
		        rL1.addView(arrow);
		        int id=getResources().getIdentifier("com.example.events_screen:drawable/arrw", null, null);
		        arrow.setImageResource(id);
		        lp2.addRule(RelativeLayout.CENTER_VERTICAL);
		        arrow.setLayoutParams(lp2);
//		        arrow.setMinimumWidth(60);
		        
//		        ImageView arrow= new ImageView(this);
//		        TR1.addView(arrow);
//		        
//		       int id=getResources().getIdentifier("com.example.events_screen:drawable/arrw", null, null);
//		    	arrow.setImageResource(id);
////		    	arrow.setAdjustViewBounds(true);
//		    	arrow.setPadding(0, 0, 14, 0);
//		    	
//		    	arrow.setLayoutParams(tableRowParams1);
//		    	
		   //Moving cursor to the next record
//		        LinearLayout LL=new LinearLayout(this);
//		        LL.setId(5)
		      cur.moveToNext();     
		   
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
