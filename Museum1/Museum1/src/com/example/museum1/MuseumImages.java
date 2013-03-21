package com.example.museum1;

import android.app.Activity;
import android.os.Bundle;

public class MuseumImages extends Activity {
	
	String CursorObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.museumimages);
		
		CursorObject=getIntent().getExtras().getString("Cursor");
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
