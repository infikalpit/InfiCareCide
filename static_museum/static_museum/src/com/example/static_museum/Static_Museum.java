package com.example.static_museum;


import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public class Static_Museum extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_museum);
        
        Resources res = getResources();
        Intent i = new Intent(this,Exhibition.class);
        Intent i1 = new Intent(this,Events.class);
        Intent i2 = new Intent(this,Museu.class);
               
        TabHost mTabHst = getTabHost();
      
               
        mTabHst.addTab(mTabHst.newTabSpec("tab_test1")
        		.setIndicator("MUSEU")
        		.setContent(i2)
        		);
                    
              mTabHst.addTab(mTabHst.newTabSpec("tab_test2")
                    .setIndicator("EXPOSICOES")
                    .setContent(i));
            
         mTabHst.addTab(mTabHst.newTabSpec("tab_test3")
                    .setIndicator("EVENTOS")
                    .setContent(i1));
             
        mTabHst.setCurrentTab(0);
        
        ImageView a=(ImageView)findViewById(R.id.imageView2);
        a.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(Static_Museum.this,Language_Selector.class);
				Static_Museum.this.startActivity(i);
				
			}
		});
         
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_static_museum, menu);
        return true;
    }
    
}
