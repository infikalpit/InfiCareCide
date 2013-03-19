package com.example.static_museum;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class Language_Selector extends Activity 

{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selector);
        
    final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
    
  
        
     // Create an ArrayAdapter using the string array and a default spinner layout
     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
             R.array.Languages, android.R.layout.simple_spinner_item);
     // Specify the layout to use when the list of choices appears
     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     // Apply the adapter to the spinner
     
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

    		  Intent intent = new Intent(getApplicationContext(),Static_Museum.class);
    		  intent.putExtra("selected", parent.getItemAtPosition(pos).toString());
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
        getMenuInflater().inflate(R.menu.activity_static_museum, menu);
        return true;
    }

	


	
}

