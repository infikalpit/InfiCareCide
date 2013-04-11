package com.example.museum3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Museum_InternalVideo extends Activity  {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.museum_internalvideo);
		 
		 
		 ImageView Back=(ImageView)findViewById(R.id.Back);
	     Back .setOnClickListener(new View.OnClickListener() 
			{ 
			public void onClick(View v) 
			{ 
				

				finish();	
				
//				 Intent myIntent = new Intent(Museum_InternalVideo.this,Museum_Images.class); 
//				 Museum_InternalVideo.this.startActivity(myIntent);   

			} 

			}); 

		 
		 
		 
		 
		 
}
	
	
	
}