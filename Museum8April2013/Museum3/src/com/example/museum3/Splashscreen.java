package com.example.museum3;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Splashscreen extends Activity 

{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        
    final Spinner spinner = (Spinner) findViewById(R.id.spinner);
    
  
        
    
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
             R.array.Languages, android.R.layout.simple_spinner_item);
   
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  
     
     spinner.setAdapter(adapter);
     
    
     
     
     
     spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
    
 }
 public class MyOnItemSelectedListener implements OnItemSelectedListener 
 
 
 {
     public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) 
     {
    	 
    	 Toast.makeText(getApplicationContext(), "You selected "+(CharSequence) parent.getItemAtPosition(pos), Toast.LENGTH_SHORT).show();
    	 
    	  if (pos != 0) 
    	  {
 
              int a=parent.getSelectedItemPosition();
              
              
              
             
    		  //name=parent.getItemAtPosition(pos).toString();
    		  Intent intent = new Intent(getApplicationContext(),Museum_Activity.class);
    		  intent.putExtra("selected", a);
    		  startActivity(intent);  
    		 
    		  
    		  
    	     
    	      
    	     
    	      
    	      
    		  
          }
    	  
    	  
    	  
    	 
     }
     @Override
     public void onNothingSelected(AdapterView<?> arg0) {}
 }
     
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_splashscreen, menu);
        return true;
    }

	


	
}

