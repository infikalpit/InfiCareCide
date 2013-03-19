package com.example.static_museum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Events extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        
        ImageView a=(ImageView)findViewById(R.id.imageView1);
        a.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(Events.this,Event_Description.class);
				Events.this.startActivity(i);
				
			}
		});
        
        ImageView a1=(ImageView)findViewById(R.id.imageView2);
        a1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i1= new Intent(Events.this,Event_Description1.class);
				Events.this.startActivity(i1);
				
			}
		});
 }

}
